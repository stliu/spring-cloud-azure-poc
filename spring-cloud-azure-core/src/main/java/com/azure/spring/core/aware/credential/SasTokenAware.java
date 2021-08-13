package com.azure.spring.core.aware.credential;

public interface SasTokenAware {

    void setSasToken(String sasToken);
    String getSasToken();
}
