package com.nearsoft.referrals.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {
    private String signingKey;

    private Integer encodingStrength;

    private String securityRealm;

    public String getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    public Integer getEncodingStrength() {
        return encodingStrength;
    }

    public void setEncodingStrength(Integer encodingStrength) {
        this.encodingStrength = encodingStrength;
    }

    public String getSecurityRealm() {
        return securityRealm;
    }

    public void setSecurityRealm(String securityRealm) {
        this.securityRealm = securityRealm;
    }
}
