package com.artemiysaltsin.restaurants.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days

    static final String SECRET = "ThisIsASecret";

    static final String TOKEN_PREFIX = "Bearer";

    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        //
        // Формирует JWT токен для пользователя
        // И прикрепляет его к заголовку ответа Authorization,
        // чтобы пользователь мог его запомнить и использовать для доступа к ресурсу
        //
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request, MySQLUserDetailsService userDetailsService) {
        //
        // Проверяет валидность токена
        // Если токен не валиден возвращает null, что соответствует отклонению запроса
        // Если валиден передаёт инфомацию о текушем пользователе (его почту)
        // для дальнейшей обработки в контроллере
        //
        String token = request.getHeader(HEADER_STRING);
        String user;
        if (token != null) {
            // parse the token.
            try {
                user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                        .getSubject();
            } catch (IllegalArgumentException e) {
                return null;
            }
            if (user == null) return null;
            else {
                UserDetails myUser = userDetailsService.loadUserByUsername(user);
                return new UsernamePasswordAuthenticationToken(user, null, myUser.getAuthorities());
            }
        }
        return null;
    }

}
