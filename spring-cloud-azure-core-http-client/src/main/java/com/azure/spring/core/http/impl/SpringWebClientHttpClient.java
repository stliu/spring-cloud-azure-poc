package com.azure.spring.core.http.impl;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.Context;
import java.util.Objects;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class SpringWebClientHttpClient implements HttpClient {
    private final WebClient webClient;

    public SpringWebClientHttpClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request) {
        return send(request, Context.NONE);
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request, Context context) {
        Objects.requireNonNull(request.getHttpMethod(), "'request.getHttpMethod()' cannot be null.");
        Objects.requireNonNull(request.getUrl(), "'request.getUrl()' cannot be null.");
        Objects.requireNonNull(request.getUrl().getProtocol(), "'request.getUrl().getProtocol()' cannot be null.");
        boolean eagerlyReadResponse = (boolean) context.getData("azure-eagerly-read-response").orElse(false);


        webClient.method(toSpringHttpMethod(request))
            .uri(request.getUrl().toString())


        ;


        return HttpClient.super.send(request, context);
    }

    HttpMethod toSpringHttpMethod(HttpRequest request){
        return HttpMethod.resolve(request.getHttpMethod().name());

    }
}
