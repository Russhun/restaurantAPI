package com.artemiysaltsin.restaurants.filter;

import com.artemiysaltsin.restaurants.services.MySQLUserDetailsService;
import com.artemiysaltsin.restaurants.services.TokenAuthenticationService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    private MySQLUserDetailsService userDetailsService;

    public JWTAuthenticationFilter(MySQLUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //
        // Устанавливает аутентификацию в контекст текущего запроса,
        // чтобы SecurityContextHolder мог решить, отклонить запрос ии нет
        // Если authentication == null, то запрос отклоняется
        //

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) servletRequest, userDetailsService);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
