package com.azure.spring.core.properties.http;

import com.azure.core.http.ProxyOptions;

/**
 * Proxy options properties for Azure SDK service client options.
 */
public class HttpProxyOptionsProperties {

//    public static final String PREFIX = "spring.cloud.azure.proxy";

    private ProxyOptions.Type type;
    private String username;
    private String password;
    private String nonProxyHosts;
    private String host;
    private int port;

    public ProxyOptions.Type getType() {
        return type;
    }

    public void setType(ProxyOptions.Type type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNonProxyHosts() {
        return nonProxyHosts;
    }

    public void setNonProxyHosts(String nonProxyHosts) {
        this.nonProxyHosts = nonProxyHosts;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
