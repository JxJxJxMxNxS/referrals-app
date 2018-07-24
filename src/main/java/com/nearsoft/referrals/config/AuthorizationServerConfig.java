package com.nearsoft.referrals.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private JWTSettings jwtSettings;

    private TokenStore tokenStore;

    private JwtAccessTokenConverter accessTokenConverter;

    private AuthenticationManager authenticationManager;

    public AuthorizationServerConfig(JWTSettings jwtSettings, TokenStore tokenStore, JwtAccessTokenConverter accessTokenConverter, AuthenticationManager authenticationManager) {
        this.jwtSettings = jwtSettings;
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(jwtSettings.getClientId())
                .secret("{noop}" + jwtSettings.getClientSecret())
                .authorizedGrantTypes("client_credentials", "password")
                .scopes(jwtSettings.getScopeRead(), jwtSettings.getScopeWrite())
                .resourceIds(jwtSettings.getResourceIds());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);

    }

}