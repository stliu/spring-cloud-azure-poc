package com.azure.spring.core.aware.credential;

import com.azure.spring.core.properties.credential.NamedKeyProperties;

public interface NamedKeyAware {

    void setNamedKey(NamedKeyProperties namedKey);
    NamedKeyProperties getNamedKey();
}
