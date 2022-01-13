/**
 * 
 */
package com.prasad.nithin.xmas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.prasad.nithin.xmas.controller.service.MyUserDetailsService;
import com.prasad.nithin.xmas.entity.UserRoles;

/**
 * @author 982168
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().disable()
			.authorizeRequests()

				.antMatchers("/*.js", "/*.css", "/fontawesome**","/assets/**","/*.png","/*.jpg","/*.jpeg","/favicon.ico","**.png")
					.permitAll()
				.antMatchers("/css/**", "/js/**", "/images/**","/fonts/**")
					.permitAll()
				.antMatchers(HttpMethod.GET)
					.permitAll()
				.antMatchers("/user/child")
					.hasAnyRole(UserRoles.USER.name())
				.antMatchers("/user/**")
					.permitAll()
				.antMatchers(HttpMethod.GET, "/polls/question")
					.permitAll()
				.antMatchers("/xmas/**")
					.hasAnyRole(UserRoles.ADMIN.name())
				.antMatchers("/polls/results")
					.hasAnyRole(UserRoles.USER.name())
				.antMatchers(HttpMethod.POST, "/polls/vote")
					.permitAll()
				.antMatchers(HttpMethod.POST, "/polls/question","/polls/questions")
					.hasAnyRole(UserRoles.ADMIN.name())
				.antMatchers(HttpMethod.DELETE, "/polls/vote")
					.hasAnyRole(UserRoles.ADMIN.name())
				.anyRequest()
					.authenticated()
					.and()
					.formLogin()
						.defaultSuccessUrl("/?error=false")
						.failureUrl("/?error=true")
						.and()
							.exceptionHandling()
							.accessDeniedPage("/403")
						.and()
							.logout()
							.logoutSuccessUrl("/?logout=true")
							.invalidateHttpSession(true);
				;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
