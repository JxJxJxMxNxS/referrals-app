package com.nearsoft.referrals.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private ResourceServerTokenServices tokenServices;

    private JWTSettings jwtSettings;

    public ResourceServerConfig(ResourceServerTokenServices tokenServices, JWTSettings jwtSettings) {
        this.tokenServices = tokenServices;
        this.jwtSettings = jwtSettings;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        if (jwtSettings.getResourceIds().length != 0)
            resources.resourceId(jwtSettings.getResourceIds()[0]).tokenServices(tokenServices);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/jobs**","/refer", "/recruiters").authenticated();
    }

}