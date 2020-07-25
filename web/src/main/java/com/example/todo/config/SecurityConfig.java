package com.example.todo.config;

import com.example.security.BaseSecurityConfig;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Import(BaseSecurityConfig.class)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web
            .debug(false)
            .ignoring()
            .antMatchers("/images/**", "/js/**", "/css/**")
        ;
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            // 認可設定
            .authorizeRequests()
                .mvcMatchers("/").permitAll()
                //.mvcMatchers("/todo/**").hasRole("USER")
                //.mvcMatchers("/todo/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
            .and()
            // ログイン設定
            // ※ほぼデフォルトを利用
            .formLogin()
                .defaultSuccessUrl("/")
            .and()
            // ログアウト設定
            .logout()
                .invalidateHttpSession(true)
                .deleteCookies("SESSION", "JSESSIONID")
                .logoutSuccessUrl("/")
        ;
        // @formatter:on
    }

}