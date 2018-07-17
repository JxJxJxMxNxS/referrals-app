package com.nearsoft.referrals.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JWTSettings {


    private String clientId;


    private String clientSecret;


    private String grantType;


    private String scopeRead;


    private String scopeWrite;


    private String[] resourceIds;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScopeRead() {
        return scopeRead;
    }

    public void setScopeRead(String scopeRead) {
        this.scopeRead = scopeRead;
    }

    public String getScopeWrite() {
        return scopeWrite;
    }

    public void setScopeWrite(String scopeWrite) {
        this.scopeWrite = scopeWrite;
    }

    public String[] getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String[] resourceIds) {
        this.resourceIds = resourceIds;
    }
}
