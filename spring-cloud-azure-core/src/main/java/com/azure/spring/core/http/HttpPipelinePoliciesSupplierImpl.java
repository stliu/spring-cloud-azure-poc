package com.azure.spring.core.http;

import com.azure.core.http.policy.HttpPipelinePolicy;
import com.azure.spring.core.properties.http.HttpProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation to supply the HttpPipelinePolicy list.
 * Combine the http pipeline policy builder and service client builder factory.
 */
public class HttpPipelinePoliciesSupplierImpl implements HttpPipelinePoliciesSupplier {

    private final HttpProperties rootProperties;
    private final HttpProperties inheritProperties;
//    private final List<AzureServiceFeature> features;

    private DefaultFeatureBasedHttpPipelinePolicyBuilder httpPipelinePolicyBuilder;

//    private static final List<AzureServiceFeature> REQUIRED_FEATURES = Collections.unmodifiableList(
//        Arrays.asList(AzureServiceFeature.TOKEN_CREDENTIAL));

    private List<HttpPipelinePolicy> policies;

    public HttpPipelinePoliciesSupplierImpl(HttpProperties rootProperties,
                                            HttpProperties inheritProperties
//                                            List<AzureServiceFeature> features
                                                    ) {
        this.rootProperties = rootProperties;
        this.inheritProperties = inheritProperties;
//        this.features = features;
    }

    @Override
    public List<HttpPipelinePolicy> get() {
        if (rootProperties != null || inheritProperties != null) {
            policies = new ArrayList<>();
            createCustomizerHttpPolicies();
        }
        return policies;
    }

    private void createCustomizerHttpPolicies() {
        /*if (inheritProperties != null) {
            inheritProperties.getPerCallPolicies()
                             .forEach(this::createHttpPolicyByInheritProperties);
            inheritProperties.getPerRetryPolicies()
                             .forEach(this::createHttpPolicyByInheritProperties);
        } else {
            inheritProperties.getPerCallPolicies()
                             .forEach(this::createHttpPolicyByRootProperties);
            inheritProperties.getPerRetryPolicies()
                             .forEach(this::createHttpPolicyByRootProperties);
        }*/

        applyNecessaryHttpPoliciesIfMissing();
    }

    /*private void createHttpPolicyByInheritProperties(PipelinePolicyProperties policyProperties) {
        httpPipelinePolicyBuilder = new DefaultFeatureBasedHttpPipelinePolicyBuilder(inheritProperties);
        httpPipelinePolicyBuilder.policyProperties(policyProperties);
        policies.add(httpPipelinePolicyBuilder.build());
    }

    private void createHttpPolicyByRootProperties(PipelinePolicyProperties policyProperties) {
        httpPipelinePolicyBuilder = new DefaultFeatureBasedHttpPipelinePolicyBuilder(rootProperties);
        httpPipelinePolicyBuilder.policyProperties(policyProperties);
        policies.add(httpPipelinePolicyBuilder.build());
    }*/

    /**
     * Feature && http policy need to collaborate.
     */
    private void applyNecessaryHttpPoliciesIfMissing() {
//        REQUIRED_FEATURES.stream().forEach(feature -> {
//            // TODO: check the required http policy existence
//        });
    }
}
