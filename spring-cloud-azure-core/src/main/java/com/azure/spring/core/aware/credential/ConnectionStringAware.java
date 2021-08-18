package com.azure.spring.core.aware.credential;

public interface ConnectionStringAware {

    void setConnectionString(String connectionString);
    String getConnectionString();
}
