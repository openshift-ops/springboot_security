package com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;

@SpringBootApplication
@EnableOAuth2Sso //this is to tell spring security to leverage oauth2 authentication via another application
public class SpringbootOauthFacebookLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauthFacebookLoginApplication.class, args);
	}

}
