package com.artemiysaltsin.restaurants.config;

import com.artemiysaltsin.restaurants.filter.JWTAuthenticationFilter;
import com.artemiysaltsin.restaurants.filter.JWTLoginFilter;
import com.artemiysaltsin.restaurants.services.MySQLUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("mySQLUserDetailService")
    MySQLUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                // No need authentication.
                .antMatchers("/").permitAll() //
                .antMatchers(HttpMethod.POST, "/login", "/reg").permitAll() //
                // Need authentication.
                .antMatchers("/restaurant", "/user", "/order", "/cart").hasAnyAuthority("USER", "ADMIN", "WAITER", "OWNER")
                .antMatchers("/orders").hasAnyAuthority("WAITER", "ADMIN")
                .antMatchers("/kitchen").hasAnyAuthority("COOK", "ADMIN")
                .antMatchers(HttpMethod.GET,"/branch").hasAnyAuthority("USER", "ADMIN", "WAITER", "OWNER")
                .antMatchers(HttpMethod.DELETE, "/branch").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/branch").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers("/seat").hasAnyAuthority("USER", "ADMIN", "WAITER", "OWNER")
                .antMatchers("/staff").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers(HttpMethod.GET,"/menu").hasAnyAuthority("USER", "ADMIN", "WAITER", "OWNER")
                .antMatchers(HttpMethod.DELETE, "/menu").hasAnyAuthority("OWNER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/menu").hasAnyAuthority("OWNER", "ADMIN")
                .anyRequest().authenticated()
                //
                .and()
                //
                // Add Filter 1 - JWTLoginFilter
                //
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //
                // Add Filter 2 - JWTAuthenticationFilter
                //
                .addFilterBefore(new JWTAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
