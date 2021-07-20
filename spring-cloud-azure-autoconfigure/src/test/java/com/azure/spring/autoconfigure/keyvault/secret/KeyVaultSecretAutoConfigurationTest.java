package com.azure.spring.autoconfigure.keyvault.secret;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class KeyVaultSecretAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(KeyVaultSecretAutoConfigurationTest.class));

    @Test
    void cqlSessionBuildHasScopePrototype() {
//        this.contextRunner.run((context) -> {
//            CqlIdentifier keyspace = CqlIdentifier.fromCql("test");
//            CqlSessionBuilder firstBuilder = context.getBean(CqlSessionBuilder.class);
//            assertThat(firstBuilder.withKeyspace(keyspace)).hasFieldOrPropertyWithValue("keyspace", keyspace);
//            CqlSessionBuilder secondBuilder = context.getBean(CqlSessionBuilder.class);
//            assertThat(secondBuilder).hasFieldOrPropertyWithValue("keyspace", null);
//        });
    }

}
