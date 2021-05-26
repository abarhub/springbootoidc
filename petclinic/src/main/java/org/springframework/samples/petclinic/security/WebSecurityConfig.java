package org.springframework.samples.petclinic.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/owners/**").authenticated()
			.antMatchers("/").permitAll()
			.and()
			.oauth2Login()
			.and()
			// logout ne délogue pas dans KeyCloak
			.logout().logoutSuccessUrl("/").permitAll();
	}

}
