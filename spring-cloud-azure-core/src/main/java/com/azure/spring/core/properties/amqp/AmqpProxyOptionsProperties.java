package com.azure.spring.core.properties.amqp;

import com.azure.core.amqp.ProxyAuthenticationType;

import java.net.Proxy;

public class AmqpProxyOptionsProperties {

    private ProxyAuthenticationType proxyAuthenticationType;
    private Proxy.Type type;
    private String username;
    private String password;
    private String host;
    private int port;

    public ProxyAuthenticationType getProxyAuthenticationType() {
        return proxyAuthenticationType;
    }

    public void setProxyAuthenticationType(ProxyAuthenticationType proxyAuthenticationType) {
        this.proxyAuthenticationType = proxyAuthenticationType;
    }

    public Proxy.Type getType() {
        return type;
    }

    public void setType(Proxy.Type type) {
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
