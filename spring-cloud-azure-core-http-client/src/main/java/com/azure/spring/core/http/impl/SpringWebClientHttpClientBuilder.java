package com.azure.spring.core.http.impl;

import com.azure.core.http.ProxyOptions;
import com.azure.core.util.Configuration;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

public class SpringWebClientHttpClientBuilder {

    private static final long MINIMUM_TIMEOUT = TimeUnit.MILLISECONDS.toMillis(1);
    private static final long DEFAULT_TIMEOUT = TimeUnit.SECONDS.toMillis(60);

    private ProxyOptions proxyOptions;
    private ConnectionProvider connectionProvider;
    private ClientHttpConnector clientHttpConnector;
    private boolean enableWiretap;
    private int port = 80;
    private EventLoopGroup eventLoopGroup;
    private Configuration configuration;
    private boolean disableBufferCopy;
    private Duration writeTimeout;
    private Duration responseTimeout;
    private Duration readTimeout;

    public com.azure.core.http.HttpClient build() {

        HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));


        ExchangeFunction exchangeFunction =
            ExchangeFunctions.create(new ReactorClientHttpConnector(httpClient));
        return new ExchangeFunctionHttpClient(exchangeFunction);

    }

    public SpringWebClientHttpClientBuilder clientHttpConnector(ClientHttpConnector clientHttpConnector){
        this.clientHttpConnector = clientHttpConnector;
        return this;
    }

    public SpringWebClientHttpClientBuilder proxy(ProxyOptions proxyOptions) {
        this.proxyOptions = proxyOptions;
        return this;
    }

    public SpringWebClientHttpClientBuilder configuration(Configuration configuration) {
        this.configuration = configuration;
        return this;
    }

    public SpringWebClientHttpClientBuilder writeTimeout(Duration writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public SpringWebClientHttpClientBuilder responseTimeout(Duration responseTimeout) {
        this.responseTimeout = responseTimeout;
        return this;
    }

    public SpringWebClientHttpClientBuilder readTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /*
     * Returns the timeout in milliseconds to use based on the passed {@link Duration}.
     * <p>
     * If the timeout is {@code null} a default of 60 seconds will be used. If the timeout is less than or equal to zero
     * no timeout will be used. If the timeout is less than one millisecond a timeout of one millisecond will be used.
     *
     * @param timeout The {@link Duration} to convert to timeout in milliseconds.
     * @return The timeout period in milliseconds, zero if no timeout.
     */
    static long getTimeoutMillis(Duration timeout) {
        // Timeout is null, use the 60 second default.
        if (timeout == null) {
            return DEFAULT_TIMEOUT;
        }

        // Timeout is less than or equal to zero, return no timeout.
        if (timeout.isZero() || timeout.isNegative()) {
            return 0;
        }

        // Return the maximum of the timeout period and the minimum allowed timeout period.
        return Math.max(timeout.toMillis(), MINIMUM_TIMEOUT);
    }
}
