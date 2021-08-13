package com.azure.spring.core.credential;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;

/**
 * Credential type for Azure Spring service client authentication.
 */
public final class AzureCredentialType {

    // Built-in type
    public static final AzureCredentialType TOKEN_CREDENTIAL = new AzureCredentialType("token_credential");
    public static final AzureCredentialType KEY_CREDENTIAL = new AzureCredentialType("key_credential");
    public static final AzureCredentialType SAS_CREDENTIAL = new AzureCredentialType("sas_credential");
    public static final AzureCredentialType NAMED_KEY_CREDENTIAL = new AzureCredentialType("named_key_credential");

    private final String type;

    AzureCredentialType(String type) {
        Assert.hasText(type, "type cannot be empty");
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AzureCredentialType type1 = (AzureCredentialType) o;
        return Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}