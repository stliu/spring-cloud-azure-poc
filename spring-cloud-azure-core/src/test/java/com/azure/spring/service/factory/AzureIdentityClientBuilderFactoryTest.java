package com.azure.spring.service.factory;

import com.azure.identity.implementation.IdentityClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AzureIdentityClientBuilderFactoryTest {
    @Test
    public void testBuilderConfigurableOptions() {
        IdentifyClientProperties properties = new IdentifyClientProperties();
        AzureIdentityClientBuilderFactory factory = new AzureIdentityClientBuilderFactory(properties);
        IdentityClientBuilder builder = factory.build();
//        Assertions.assertTrue();

    }
}
