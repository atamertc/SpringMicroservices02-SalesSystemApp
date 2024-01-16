package com.atamertc.config;

import com.atamertc.exception.ElasticManagerException;
import com.atamertc.exception.ErrorType;
import com.atamertc.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Eklediğimiz JwtTokenFilter devreye girdi.");
        // /elastic/user/save URL'ine sahip istekleri filtre dışı bırakmak için kontrol ediyoruz.
        if ("/elastic/user/save".equals(request.getRequestURI()) || request.getRequestURI().contains("swagger") || request.getRequestURI().contains("/v3")) {
            System.out.println("otomatik elastic kayıt engellememek için bu filteri bypass ediyoruz...");
            filterChain.doFilter(request, response); // Filtre devre dışı bırakılır ve işlem devam eder.
            return;
        }
        // HTTP isteğinin "Authorization" başlığından "Bearer" token'ı alıyoruz.
        String bearerToken = request.getHeader("Authorization");
        // System.out.println("Bearer Token:"+bearerToken.substring(7));

        // SecurityContextHolder'da kimlik doğrulama nesnesi olup olmadığını kontrol ediyoruz.
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("authentication nesnesi boşken.");
            System.out.println("talep edilen uri:" + request.getRequestURI());
            // Bearer token'ın var olup olmadığını ve başlangıcının "Bearer " ile başlayıp başlamadığını kontrol ediyoruz.
            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                throw new ElasticManagerException(ErrorType.INVALID_TOKEN);
            }
            System.out.println("Bearer token gelmiş.");
            String token = bearerToken.substring(7);
            // Token'dan kullanıcı kimliğini çıkarmak için JwtTokenManager sınıfını kullanıyoruz.
            Optional<Long> authid = jwtTokenManager.getIdFromToken(token);
            if (authid.isEmpty()) {
                System.out.println("authid boş...");
                throw new ElasticManagerException(ErrorType.INVALID_TOKEN);
            }
            System.out.println("auth id:" + authid.get());
            // Auth ID'den kullanıcı detaylarını JwtUserDetails sınıfını kullanarak alıyoruz.
            UserDetails userDetails = jwtUserDetails.loadUserByAuthid(authid.get());
            // Spring Security kimlik doğrulama tokenini oluşturuyoruz.
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
            // SecurityContextHolder'da kimlik doğrulama tokenini ayarlıyoruz.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        System.out.println("Authentication nesnesi dolu tokeni tekrar göndermedi... .");
        filterChain.doFilter(request, response); // hatasız buraya kadar geldiyse isteğe giriş yapabilmesini sağladık.
    }

}