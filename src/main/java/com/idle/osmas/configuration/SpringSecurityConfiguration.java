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

        // 판매 관리 페이지
        http.csrf().disable().authorizeRequests()
                .antMatchers("/seller/regist").authenticated()
                .antMatchers(HttpMethod.GET,"/seller/regist/**").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/seller/regist/**").hasRole("SELLER")
                .antMatchers("/seller/projectList").authenticated()
                .antMatchers(HttpMethod.GET,"/seller/projectList").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/seller/projectList").hasRole("SELLER")
                .antMatchers("/seller/projectQnAList").authenticated()
                .antMatchers(HttpMethod.GET,"/seller/projectQnAList").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/seller/projectQnAList").hasRole("SELLER")
                .antMatchers("/seller/orderList").authenticated()
                .antMatchers(HttpMethod.GET,"/seller/orderList").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/seller/orderList").hasRole("SELLER")
                .antMatchers("/seller/projectDetail/**").authenticated()
                .antMatchers(HttpMethod.GET,"/seller/projectDetail/**").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/seller/projectDetail/**").hasRole("SELLER");

        // 리뷰 페이지 제안페이지 결제페이지
        http.csrf().disable().authorizeRequests()
                .antMatchers("/member/review/**").authenticated()
                .antMatchers(HttpMethod.GET,"/member/review/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/member/review/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/member/review/**").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/member/review/**").hasRole("SELLER")
                .antMatchers(HttpMethod.GET,"/member/review/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/member/review/**").hasRole("ADMIN")
                .antMatchers("/member/suggest/**").authenticated()
                .antMatchers(HttpMethod.GET,"/member/suggest/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/member/suggest/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/member/suggest/**").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/member/suggest/**").hasRole("SELLER")
                .antMatchers(HttpMethod.GET,"/member/suggest/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/member/suggest/**").hasRole("ADMIN")
                .antMatchers("/member/pay/**").authenticated()
                .antMatchers(HttpMethod.GET,"/member/pay/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/member/pay/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/member/pay/**").hasRole("SELLER")
                .antMatchers(HttpMethod.POST,"/member/pay/**").hasRole("SELLER")
                .antMatchers(HttpMethod.GET,"/member/pay/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/member/pay/**").hasRole("ADMIN");

        // 관리지 페이지
        // (사용자 조회, 상품승인관리, 판매자권한관리, 제안 받기, 약관 작성, 약관 수정)
        http.csrf().disable().authorizeRequests()
                .antMatchers("/admin/adminCategory/**").authenticated()
                .antMatchers(HttpMethod.GET,"/admin/adminCategory/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/adminCategory/**").hasRole("ADMIN")
                .antMatchers("/admin/itemApproval/**").authenticated()
                .antMatchers(HttpMethod.GET,"/admin/itemApproval/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/itemApproval/**").hasRole("ADMIN")
                .antMatchers("/admin/sellerApproval/**").authenticated()
                .antMatchers(HttpMethod.GET,"/admin/sellerApproval/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/admin/sellerApproval/**").hasRole("ADMIN")
                .antMatchers("/admin/footerCategory/**").authenticated()
                .antMatchers(HttpMethod.GET,"/admin/footerCategory/termsInput").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/footerCategory/termsEdit").hasRole("ADMIN")
                .antMatchers("/admin/user_management/**").authenticated()
                .antMatchers(HttpMethod.GET,"/admin/user_management/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/admin/user_management/**").hasRole("ADMIN");









        // 사용자 관리 페이지
        http.csrf().disable().authorizeRequests()
                .antMatchers("/member/mypage/**").authenticated()
                .antMatchers(HttpMethod.GET, "/member/mypage/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/member/mypage/**").hasRole("USER");


        // 사용자 인증
        http.csrf().disable()
                .authorizeRequests()
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
                .invalidateHttpSession(true);

        return http.build();
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