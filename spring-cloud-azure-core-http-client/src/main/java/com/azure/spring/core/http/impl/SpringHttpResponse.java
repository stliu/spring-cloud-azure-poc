package com.azure.spring.core.http.impl;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringHttpResponse extends HttpResponse {
    private final ClientResponse clientResponse;
    private final HttpHeaders headers;
    public SpringHttpResponse(HttpRequest request,
        ClientResponse clientResponse) {
        super(request);
        this.clientResponse = clientResponse;
        this.headers = ExchangeFunctionHttpClient.toAzureHttpHeaders.apply(clientResponse.headers().asHttpHeaders());
    }

    @Override
    public int getStatusCode() {
        return clientResponse.rawStatusCode();
    }

    @Override
    public String getHeaderValue(String name) {
        return headers.getValue(name);
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    @Override
    public Flux<ByteBuffer> getBody() {
        return clientResponse.bodyToFlux(ByteBuffer.class);
    }

    @Override
    public Mono<byte[]> getBodyAsByteArray() {
        return clientResponse.bodyToMono(byte[].class);
    }

    @Override
    public Mono<String> getBodyAsString() {
        return clientResponse.bodyToMono(String.class);
    }

    @Override
    public Mono<String> getBodyAsString(Charset charset) {
        return getBodyAsString();
    }

    @Override
    public void close() {
        clientResponse.releaseBody().subscribe();
    }
}
