package com.azure.spring.core.aware;

import com.azure.core.http.policy.HttpLogOptions;

public interface HttpLogOptionsAware {
    void setHttpLogOptions(HttpLogOptions logOptions);
}
