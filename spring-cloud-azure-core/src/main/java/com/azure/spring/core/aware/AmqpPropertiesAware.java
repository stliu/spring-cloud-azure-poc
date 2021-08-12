package com.azure.spring.core.aware;

import com.azure.spring.core.properties.amqp.AmqpProperties;

public interface AmqpPropertiesAware {

    void setAmqp(AmqpProperties amqp);
}
