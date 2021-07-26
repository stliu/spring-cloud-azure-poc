package com.azure.spring.autoconfigure.keyvault.secret;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.security.keyvault.secrets.SecretAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class KeyVaultSecretAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(KeyVaultAutoConfiguration.class));

    @Test
    void whenThereIsNoKeyVaultSDKPresent(){
        this.contextRunner.withClassLoader(new FilteredClassLoader(SecretClientBuilder.class))
            .run(context -> {
                assertThat(context).doesNotHaveBean(SecretClient.class);
            });
    }

    @Test
    void cqlSessionBuildHasScopePrototype() {
        this.contextRunner.run((context) -> {
            assertThat(context).hasSingleBean(SecretClient.class);
            assertThat(context).hasSingleBean(SecretAsyncClient.class);
            assertThat(context).hasSingleBean(SecretClientBuilder.class);
        });
    }

}
