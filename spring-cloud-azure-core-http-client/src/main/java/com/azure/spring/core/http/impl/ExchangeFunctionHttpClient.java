package com.azure.spring.core.http.impl;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.Context;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.function.Function;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

public class ExchangeFunctionHttpClient implements HttpClient {

    private final ExchangeFunction exchangeFunction;

    public ExchangeFunctionHttpClient(ExchangeFunction exchangeFunction) {
        this.exchangeFunction = exchangeFunction;
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request) {
        return send(request, Context.NONE);
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request, Context context) {
        return exchangeFunction.exchange(getClientRequest(request, context))
            .map(r -> new SpringHttpResponse(request, r));
    }

    private ClientRequest getClientRequest(HttpRequest request, Context context) {
        Objects.requireNonNull(request.getHttpMethod(), "'request.getHttpMethod()' cannot be null.");
        Objects.requireNonNull(request.getUrl(), "'request.getUrl()' cannot be null.");
        Objects.requireNonNull(request.getUrl().getProtocol(), "'request.getUrl().getProtocol()' cannot be null.");
        URI url = URI.create(request.getUrl().toString());
        HttpMethod method = HttpMethod.resolve(request.getHttpMethod().toString());
        Objects.requireNonNull(method, "http method " + request.getHttpMethod() + " is not supported");
        ClientRequest.Builder builder = ClientRequest.create(method, url);
        if (request.getBody() != null) {
            builder.body(BodyInserters.fromPublisher(request.getBody(), ByteBuffer.class));
        }
        request.getHeaders().toMap().forEach(builder::header);

        return builder.build();
    }

    public static final Function<HttpHeaders, com.azure.core.http.HttpHeaders> toAzureHttpHeaders = (httpHeaders) -> {
        com.azure.core.http.HttpHeaders headers = new com.azure.core.http.HttpHeaders();
        httpHeaders.forEach(headers::set);
        return headers;
    };
    public static final Function<com.azure.core.http.HttpHeaders, HttpHeaders> toSpringHttpHeaders = (httpHeaders) -> {
        HttpHeaders headers = new HttpHeaders();
        httpHeaders.toMap().forEach(headers::add);
        return headers;
    };
}
