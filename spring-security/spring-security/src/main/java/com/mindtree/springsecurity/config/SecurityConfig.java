package com.mindtree.springsecurity.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mindtree.springsecurity.filter.AuthoritiesLoggingAfterFilter;
import com.mindtree.springsecurity.filter.AuthoritiesLoggingAtFilter;
import com.mindtree.springsecurity.filter.JWTTokenGeneratorFilter;
import com.mindtree.springsecurity.filter.JWTTokenValidatorFilter;
import com.mindtree.springsecurity.filter.RequestValidationBeforeFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  // By enabling the csrf(some error or issue in code please don't use it)
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub

				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().ignoringAntMatchers("/contacts").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().authorizeRequests().antMatchers("/account").authenticated().antMatchers("/balance")
				.authenticated().antMatchers("/cards").authenticated().antMatchers("/loans").authenticated()
				.antMatchers("/contacts").permitAll().antMatchers("/user").permitAll().antMatchers("/notices")
				.permitAll().antMatchers("/api/signup").permitAll().antMatchers("/api/unsecure").permitAll().and()
				.formLogin().and().httpBasic();

	}*/
	// with csrf disable
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub

				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable().authorizeRequests().antMatchers("/account").authenticated().antMatchers("/balance")
				.authenticated().antMatchers("/cards").authenticated().antMatchers("/loans").authenticated()
				.antMatchers("/contacts").permitAll().antMatchers("/user").permitAll().antMatchers("/notices")
				.permitAll().antMatchers("/api/signup").permitAll().antMatchers("/api/unsecure").permitAll().antMatchers("/contacts").permitAll().and().httpBasic();

	}
	*/
	// Authority based Authorization
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub

				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable().authorizeRequests().antMatchers("/account").hasAuthority("READ").antMatchers("/balance")
				.hasAuthority("WRITE").antMatchers("/cards").authenticated().antMatchers("/loans").hasAuthority("DELETE")
				.antMatchers("/contacts").permitAll().antMatchers("/user").permitAll().antMatchers("/notices")
				.permitAll().antMatchers("/api/signup").permitAll().antMatchers("/api/unsecure").permitAll().antMatchers("/contacts").permitAll().and().httpBasic();

	}*/
	// Role based Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				// TODO Auto-generated method stub

				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setExposedHeaders(Arrays.asList("Authorization"));
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable().addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
		.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.authorizeRequests().antMatchers("/account").hasRole("USER").antMatchers("/balance")
				.hasAnyRole("USER","ADMIN").antMatchers("/cards").authenticated().antMatchers("/loans").hasRole("ROOT")
				.antMatchers("/contacts").permitAll().antMatchers("/user").permitAll().antMatchers("/notices")
				.permitAll().antMatchers("/api/signup").permitAll().antMatchers("/api/unsecure").permitAll().antMatchers("/contacts").permitAll().and().httpBasic();

	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // TODO Auto-generated method stub
	 * auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
	 * ("admin").and()
	 * .withUser("user").password("hello").authorities("read").and().passwordEncoder
	 * (NoOpPasswordEncoder.getInstance());
	 * 
	 * }
	 */

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // TODO Auto-generated method stub InMemoryUserDetailsManager
	 * userDetailsService=new InMemoryUserDetailsManager(); UserDetails
	 * user=User.withUsername("admin").password("12345").authorities("admin").build(
	 * ); UserDetails
	 * user1=User.withUsername("user").password("hello").authorities("read").build()
	 * ; userDetailsService.createUser(user); userDetailsService.createUser(user1);
	 * auth.userDetailsService(userDetailsService);
	 * 
	 * }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
