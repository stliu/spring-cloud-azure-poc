package com.azure.spring.core.properties;

import java.util.List;

public interface HeadersAware {

    void setHeaders(List<HeaderProperties> headers);
}
