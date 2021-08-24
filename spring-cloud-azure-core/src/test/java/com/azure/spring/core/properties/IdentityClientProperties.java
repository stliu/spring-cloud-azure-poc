package com.azure.spring.core.properties;

import com.azure.identity.AuthenticationRecord;
import com.azure.identity.TokenCachePersistenceOptions;

public class IdentityClientProperties extends AzureProperties {

    private boolean allowUnencryptedCache;
    private boolean cp1Disabled;
    private boolean sharedTokenCacheCred;
    private boolean includeX5c;
    private String keePassDatabasePath;
    private AuthenticationRecord authenticationRecord;
    private TokenCachePersistenceOptions tokenCachePersistenceOptions;

    public boolean isAllowUnencryptedCache() {
        return allowUnencryptedCache;
    }

    public void setAllowUnencryptedCache(boolean allowUnencryptedCache) {
        this.allowUnencryptedCache = allowUnencryptedCache;
    }

    public boolean isCp1Disabled() {
        return cp1Disabled;
    }

    public void setCp1Disabled(boolean cp1Disabled) {
        this.cp1Disabled = cp1Disabled;
    }

    public boolean isSharedTokenCacheCred() {
        return sharedTokenCacheCred;
    }

    public void setSharedTokenCacheCred(boolean sharedTokenCacheCred) {
        this.sharedTokenCacheCred = sharedTokenCacheCred;
    }

    public boolean isIncludeX5c() {
        return includeX5c;
    }

    public void setIncludeX5c(boolean includeX5c) {
        this.includeX5c = includeX5c;
    }

    public String getKeePassDatabasePath() {
        return keePassDatabasePath;
    }

    public void setKeePassDatabasePath(String keePassDatabasePath) {
        this.keePassDatabasePath = keePassDatabasePath;
    }

    public AuthenticationRecord getAuthenticationRecord() {
        return authenticationRecord;
    }

    public void setAuthenticationRecord(AuthenticationRecord authenticationRecord) {
        this.authenticationRecord = authenticationRecord;
    }

    public TokenCachePersistenceOptions getTokenCachePersistenceOptions() {
        return tokenCachePersistenceOptions;
    }

    public void setTokenCachePersistenceOptions(TokenCachePersistenceOptions tokenCachePersistenceOptions) {
        this.tokenCachePersistenceOptions = tokenCachePersistenceOptions;
    }
}
