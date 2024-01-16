package com.atamertc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ElasticSecurityConfig {

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         * Gelen bütün istekler için authenticate olma şartı getirdik.
         * 403:  you don’t have permission to access this resource
         */
       /* httpSecurity.authorizeRequests()
                .antMatchers("/mylogin.html").permitAll()
                .anyRequest().authenticated();*/
        //ERR_TOO_MANY_REDIRECTS
        //httpSecurity.formLogin().loginPage("/mylogin.html");

        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/elastic/user/save/**").permitAll()
                .antMatchers("/elastic/user/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .anyRequest().authenticated();
        // httpSecurity.formLogin();
        httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
        //.antMatchers("/**:9200/**").permitAll()
        // antMatchers(HttpMethod.GET, "/user").authenticated()
    }



}
