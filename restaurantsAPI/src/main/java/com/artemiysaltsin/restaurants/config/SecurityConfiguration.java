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
    /**
     * Конфигурация безопасности
     */


    @Autowired
    @Qualifier("mySQLUserDetailService")
    MySQLUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Отключаем csrf
        http.csrf().disable().authorizeRequests()
                //
                // Настраиваем пути для которых не требуется аутентификация
                //
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login", "/reg").permitAll()
                //
                // Пути, для которых требуется аутентификация
                //
                .antMatchers("/restaurant", "/user", "/cart").hasAnyAuthority("USER", "ADMIN", "WAITER", "OWNER")
                .antMatchers(HttpMethod.POST, "/orders").hasAnyAuthority("USER", "OWNER", "WAITER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/orders").hasAnyAuthority("WAITER", "ADMIN", "OWNER")
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
                // Добавляем первый фильтер - JWTLoginFilter
                //
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //
                // Добавляем второй фильтер - JWTAuthenticationFilter
                //
                .addFilterBefore(new JWTAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Устанавлием собственный user details service
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
