package com.reed.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	
	private static Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfig.class);
	
	  @Override
	    public void configure(@SuppressWarnings("deprecation") ClientDetailsServiceConfigurer clients) throws Exception {
		  
		  LOGGER.info("About to validate oauth parameter....");
		  
		  
	        clients.inMemory()
	        .withClient("frederick_testing")
	        .secret("{noop}guess_me_not")
	        .accessTokenValiditySeconds(3600)
	        .authorizedGrantTypes("authorization_code")
	        .scopes("read")
	        .authorities("CLIENT")
	        .redirectUris("http://localhost:8090/hello")
	       
	        
	        ;
	    }
	
}
