package com.tikbiz.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(
						"select USER_NAME,PASSWORD from TMS_USER where USER_NAME=?")
				.authoritiesByUsernameQuery(
						"select USER_NAME, ROLE from TMS_USER where USER_NAME=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/admin/**")
//				.access("hasRole('ROLE_ADMIN')").and().formLogin()
//				.loginPage("/login").failureUrl("/login?error")
//				.usernameParameter("username").passwordParameter("password")
//				.and().logout().logoutSuccessUrl("/login?logout").and()
//				.exceptionHandling().accessDeniedPage("/403").and().csrf();
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("SUPPORT");
	}
	
	/* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}