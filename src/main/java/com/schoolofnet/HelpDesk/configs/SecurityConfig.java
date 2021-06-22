package com.schoolofnet.HelpDesk.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests().antMatchers("/").permitAll();
		
		http.authorizeRequests()
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
				.failureUrl("/login?erros=true")
				.defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
					.and()
					.exceptionHandling()
					.accessDeniedPage("/danied");
					
	}
	
	@Override
	public void configure(WebSecurity webSecurity) {
		webSecurity
			.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/videos/**", "/images/**", "/resources/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
				.usersByUsernameQuery("select u.email, u.password, u.active from users u where u.email = ? and u.active = 1")
				.authoritiesByUsernameQuery(" select u.email, r.name from users u"
						+ " inner join users_roles ur on (u.id = ur.user_id)"
						+ " inner join roles r on (ur.role_id = r.id)"
						+ " where u.email = ? and u.active = 1")
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder); 
	}
	
	
}




























