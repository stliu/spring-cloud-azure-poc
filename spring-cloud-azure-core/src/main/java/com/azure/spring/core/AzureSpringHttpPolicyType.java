package com.azure.spring.core;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Http policy type for Azure Spring.
 */
public final class AzureSpringHttpPolicyType implements Serializable, Comparable<AzureSpringHttpPolicyType> {

    // Built-in policies
    public static final AzureSpringHttpPolicyType USER_AGENT_POLICY = new AzureSpringHttpPolicyType("user_agent", 10);
    public static final AzureSpringHttpPolicyType REQUEST_ID_POLICY = new AzureSpringHttpPolicyType("request_id", 20);
    public static final AzureSpringHttpPolicyType REQUEST_RETRY_POLICY = new AzureSpringHttpPolicyType("request_retry", 30);
    public static final AzureSpringHttpPolicyType HTTP_LOGGING_POLICY = new AzureSpringHttpPolicyType("http_logging", 40);
    public static final AzureSpringHttpPolicyType ADD_DATE_POLICY = new AzureSpringHttpPolicyType("add_date", 50);
    public static final AzureSpringHttpPolicyType AAD_HEADERS_POLICY = new AzureSpringHttpPolicyType("aad_headers", 60);
    public static final AzureSpringHttpPolicyType METADATA_VALIDATION_POLICY = new AzureSpringHttpPolicyType("metadata_validation", 70);
    public static final AzureSpringHttpPolicyType STORAGE_SHARED_KEY_CREDENTIAL_POLICY = new AzureSpringHttpPolicyType("storage_shared_key_credential", 80);
    public static final AzureSpringHttpPolicyType BEARER_TOKEN_AUTHENTICATION_POLICY = new AzureSpringHttpPolicyType("bearer_token_authentication", 90);
    public static final AzureSpringHttpPolicyType AZURE_SAS_CREDENTIAL_POLICY = new AzureSpringHttpPolicyType("azure_sas_credential", 100);

    private final String policy;
    private final int order;

    AzureSpringHttpPolicyType(String policy, int order) {
        Assert.hasText(policy, "policy cannot be empty");
        Assert.notNull(order, "order cannot be empty");
        this.policy = policy;
        this.order = order;
    }

    public String getPolicy() {
        return this.policy;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(@NotNull AzureSpringHttpPolicyType target) {
        return this.getOrder() - target.getOrder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            AzureSpringHttpPolicyType that = (AzureSpringHttpPolicyType)obj;
            return this.getPolicy().equals(that.getPolicy());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getPolicy().hashCode();
    }
}
