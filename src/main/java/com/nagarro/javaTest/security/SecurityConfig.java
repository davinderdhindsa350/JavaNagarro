package com.nagarro.javaTest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
		  
	  }
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/login", "/").permitAll()
				.antMatchers("/api/**").hasAnyRole("ADMIN","USER")
			.and().exceptionHandling().accessDeniedPage("/401");

		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
  
  
  
  @Bean
  public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
  }
}