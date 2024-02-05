package com.sra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
      	@Override
      	protected void configure(HttpSecurity http) throws Exception {
      		http
      			.authorizeRequests()
      		// All user access 	
      		//	.antMatchers("/home").permitAll()
      		// provide Roll 
      			.antMatchers("/Api/**").hasRole("ADMIN")
//      			.antMatchers("/Api/courses/").hasRole("Normal")
      			.anyRequest()
      			.authenticated()
      			.and()
      			.httpBasic();
      	}
      	@Override
      	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      		auth.inMemoryAuthentication().withUser("Akshay").password(this.passworkEncoder().encode("Akshay")).roles("Normal");
      		auth.inMemoryAuthentication().withUser("Aman").password(this.passworkEncoder().encode("Aman")).roles("ADMIN");
      	}
      	
      	@Bean
      	public PasswordEncoder passworkEncoder() {
      		return new BCryptPasswordEncoder(10);
      	}
      	
}
