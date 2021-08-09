package com.azure.spring.autoconfigure.demo;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ChainedTokenCredentialBuilder;
import com.azure.security.keyvault.certificates.CertificateClientBuilder;
import com.azure.spring.autoconfigure.keyvault.KeyVaultProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateProperties;
import com.azure.spring.autoconfigure.keyvault.certificate.KeyVaultCertificateServiceClientBuilderFactory;
import com.azure.spring.core.AzureProperties;
import com.azure.spring.core.http.HttpClientSupplier;
import com.azure.spring.core.http.HttpClientSupplierImpl;
import com.azure.spring.core.http.HttpPipelinePoliciesSupplier;
import com.azure.spring.core.properties.HttpProperties;


public class DemoKeyVault {

    public static void main(String[] args) {
        KeyVaultProperties kp = new KeyVaultProperties();
        KeyVaultCertificateProperties kcp = new KeyVaultCertificateProperties();
        AzureProperties rootProperties = new AzureProperties();
        TokenCredential defaultTokenCredential = new ChainedTokenCredentialBuilder().build();
        KeyVaultCertificateServiceClientBuilderFactory builderFactory =
            new KeyVaultCertificateServiceClientBuilderFactory(defaultTokenCredential, kp, kcp, rootProperties.getHttp());

        //
//        List<AzureServiceFeature> azureServiceFeatures = builderFactory.supportFeatures();
//        List<HttpPipelinePolicy> httpPipelinePolicies = builderFactory.getHttpPipelinePolicies();
        HttpPipelinePoliciesSupplier httpPipelinePoliciesSupplier = builderFactory.getHttpPipelinePolicySupplier();

        HttpClientSupplier httpClientSupplier =
            new HttpClientSupplierImpl(rootProperties.getHttp(), kcp.getHttp());

        // feature, -> resolve -> policies
        // client id & cert > policy -> pipeline
        //

//        HttpPipeline hp = new HttpPipelineBuilder()
//            .httpClient(httpClientSupplier.get())
//            .policies(httpPipelinePoliciesSupplier.get().toArray(new HttpPipelinePolicy[0]))
//            .build();
//        builderFactory.setPipeline(hp);
        CertificateClientBuilder build = builderFactory.build();

    }
}
