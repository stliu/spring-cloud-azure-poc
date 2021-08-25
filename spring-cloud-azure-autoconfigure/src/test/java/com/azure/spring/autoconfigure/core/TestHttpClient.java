package com.azure.spring.autoconfigure.core;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.Context;
import reactor.core.publisher.Mono;

public class TestHttpClient implements HttpClient {

    @Override
    public Mono<HttpResponse> send(HttpRequest request) {
        return null;
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request, Context context) {
        return HttpClient.super.send(request, context);
    }
}