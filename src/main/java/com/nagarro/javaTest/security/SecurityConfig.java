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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	  protected void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
		  
	  }
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http
        .httpBasic().and()
        .authorizeRequests()
        .antMatchers("/").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .formLogin().and().logout().and()
        .httpBasic();
        ;
		http.sessionManagement().maximumSessions(1).expiredUrl("/login").maxSessionsPreventsLogin(true);
		http.sessionManagement().invalidSessionUrl("/invalidSession");
	}
  
  
  
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}