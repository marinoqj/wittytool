package es.golemdr.wittytool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import es.golemdr.wittytool.service.security.CustomUserDetailsService;


@ComponentScan(basePackages = "es.golemdr.wittytool")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	   @Autowired
	   PasswordEncoder passwordEncoder;
	 
	   @Autowired
	   public CustomUserDetailsService customUserDetailsService;
	   
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	
	    	auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	    	
	    }
	   	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	        .antMatchers("/", "/login**", "/static/**")
	            .permitAll()
	        .antMatchers("/**")
	            .hasAnyRole("ADMIN", "USER")
	        .and()
	            .formLogin()
	            .loginPage("/login.do")
	            .defaultSuccessUrl("/accessook.do")
	            .failureUrl("/login-failure.do")
	            .permitAll()
	        .and()
	            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
	            .logoutSuccessUrl("/")
	            .deleteCookies("JSESSIONID")
	            .invalidateHttpSession(true)
	            .permitAll();
	    }
	    
	    
}