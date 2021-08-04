package com.azure.spring.autoconfigure.core;

import com.azure.core.http.policy.HttpLogOptions;

public interface HttpLogOptionsAware {
    void setHttpLogOptions(HttpLogOptions logOptions);
}
