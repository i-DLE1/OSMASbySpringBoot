package com.idle.osmas.configuration;

import com.idle.osmas.member.handler.LoginFailHandler;
import com.idle.osmas.member.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SpringSecurityConfiguration {

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
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/seller/regist").authenticated()
//                .antMatchers("/seller/projectList").authenticated()
//                .antMatchers("/seller/projectQnAList").authenticated()
//                .antMatchers("/seller/projectDetail/**").authenticated()
//                .antMatchers(HttpMethod.GET,"/seller/projectDetail/**").hasRole("SELLER")
//                .antMatchers(HttpMethod.GET,"/seller/projectDetail/**").hasRole("SELLER")
//                .antMatchers(HttpMethod.POST,"/seller/projectDetail/**").hasRole("SELLER")
//                .antMatchers(HttpMethod.POST,"/seller/regist/**").hasRole("SELLER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/member/login/login")
                .usernameParameter("id")			// 아이디 파라미터명 설정
                .passwordParameter("pwd")
                .successForwardUrl("/")
                .failureHandler(loginFailHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and().build();
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