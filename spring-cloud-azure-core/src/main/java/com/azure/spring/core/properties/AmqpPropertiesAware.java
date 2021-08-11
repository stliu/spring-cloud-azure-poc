package com.azure.spring.core.properties;

import com.azure.spring.core.properties.amqp.AmqpProperties;

public interface AmqpPropertiesAware {

    void setAmqp(AmqpProperties amqp);
}
