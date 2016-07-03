package com.ttnd.springSecurity.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder manager) throws Exception {
		/*
		 * In Memory User Authentication
		 */
		
		  /*manager.inMemoryAuthentication().withUser("vishal").password("maurya"
		  ).roles("NORMAL");
		  manager.inMemoryAuthentication().withUser("vis").password("047").
		  roles("ADMIN");*/
		 
		/*
		 * Authentication Through JDBC
		 */
		System.out.println(">>>>>>>>>>>>>>>>authentication<<<<<<<<<<<<<<<<<<");
		manager.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select userName,password,enabled from User where userName=?")
				.authoritiesByUsernameQuery("select userName,type as role from User,USER_ROLE,UserRoles where userName=? and "
						+ "User.user_id=USER_ROLE.user_id and UserRoles.role_id=USER_ROLE.role_id");
		System.out.println(">>>>>>>>>>>>>>>>authentication<<<<<<<<<<<<<<<<<<");
		  
		/*
		 * Authentication throught User Class using hibernate
		 */
		//  manager.userDetailsService(userDetailsService)/*.passwordEncoder(passwordEncoder())*/;

	}

	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		/*httpSec.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')").and().formLogin().loginPage("/login")
				.usernameParameter("username").passwordParameter("password").and().exceptionHandling()
				.accessDeniedPage("/login?error");*/
		
		/*
		 * Authorization of different Users to different authorities
		 */
		httpSec.authorizeRequests()
		 .antMatchers("/admin*").access("hasRole('ROLE_ADMIN')")
		 .anyRequest().permitAll()
		 .and()
		   .formLogin().loginPage("/login*")
		   .usernameParameter("username").passwordParameter("password")
		 .and()
		   .logout().logoutSuccessUrl("/login?logout") 
		  .and()
		  .exceptionHandling().accessDeniedPage("/login?error")
		 .and().csrf().and().headers().defaultsDisabled().cacheControl();
		 //.csrf().and().headers().defaultsDisabled().cacheControl();
	}
	/*@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/
}
