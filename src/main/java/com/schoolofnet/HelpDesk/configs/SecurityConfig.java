package com.schoolofnet.HelpDesk.configs;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvcSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bcrypterPasswordEncoder;
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/login")
				.permitAll()
			.antMatchers("/registration")
				.permitAll()
			.antMatchers("/**")
				.hasAnyAuthority("ADMIN", "USER")
				.anyRequest()
			.authenticated()
				.and()
				.csrf()
				.disable()
				.formLogin()
			.loginPage("/login")
				.failureUrl("/login?errors=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
					.and()
					.exceptionHandling() 
					.accessDeniedPage("/denied");
	}
		
	@Override
	public void configure(WebSecurity webSecurity){
		webSecurity
		.ignoring()
		.antMatchers(
				"/static/**",
			    "/js/**",
			    "/css/**",
			    "/videos/**",
			    "/images/**",
				"/resources/**"
				 );
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	
		auth.
		jdbcAuthentication()
		.usersByUsernameQuery("select user.email,user.password,user.active from users user where user.email= ? and user.active= 1")
		.authoritiesByUsernameQuery("select user.email, role.name from users user"
				+ " inner join users_roles usuario on (user.id = usuario.user_id)"
				+ " inner join roles role on (usuario.role_id = role.id)"
				+ "where user.email = ? "
				+ "and user.active= 1")
		.dataSource(datasource)
		.passwordEncoder(bcrypterPasswordEncoder);	
	}	
}
