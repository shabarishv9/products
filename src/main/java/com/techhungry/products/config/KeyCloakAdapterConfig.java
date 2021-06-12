/**
 * 
 */
package com.techhungry.products.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * @author shabarish
 *
 */
@KeycloakConfiguration
//@Import({KeycloakSpringBootConfigResolver.class})
public class KeyCloakAdapterConfig extends KeycloakWebSecurityConfigurerAdapter {

	@Bean
	public KeycloakConfigResolver keycloakConfigResolver() {
	    return new KeycloakSpringBootConfigResolver();
	}
	/**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
    	SimpleAuthorityMapper sm = new SimpleAuthorityMapper();
    	sm.setPrefix("ROLE_");
    	provider.setGrantedAuthoritiesMapper(sm);
        auth.authenticationProvider(provider);
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected NullAuthenticatedSessionStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/products*").hasRole("user")
                .antMatchers("/products/*").hasRole("admin")
                .anyRequest().authenticated();
    }		

}
