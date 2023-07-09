package com.idle.osmas.configuration;

import com.idle.osmas.member.handler.LoginFailHandler;
import com.idle.osmas.member.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Order(1)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private LoginService loginService;

    @Autowired
    public SpringSecurityConfiguration(LoginService loginService){
        this.loginService = loginService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**");
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login/login")
                .usernameParameter("id")
                .passwordParameter("pwd")
                .successForwardUrl("/")
                .failureHandler(loginFailHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(loginService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }

}
