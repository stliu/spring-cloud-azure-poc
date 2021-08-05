package com.azure.spring.core.http;

import com.azure.core.http.HttpClient;

import java.util.function.Supplier;

public interface HttpClientSupplier extends Supplier<HttpClient> {

}
