package com.example.amadeuscase.core.jwt.filter;

import com.example.amadeuscase.core.jwt.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            System.out.println("Token: " + accessToken);
            Claims claims = jwtUtil.parseJwtClaims(accessToken);

            if (claims != null && jwtUtil.validateClaims(claims)) {
                String email = claims.get("email", String.class);
                String password = claims.get("password", String.class);
                String role = claims.get("role", String.class);
                log.info("email: " + email);
                log.info("password: " + password);
                log.info("role: " + role);

                // Rol bilgisini SimpleGrantedAuthority olarak oluştur
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);

                // Rol bilgisini içeren bir liste oluştur
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(authority);

                // Authentication nesnesini oluştur
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(email, password, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }



        } catch (Exception e) {
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            mapper.writeValue(response.getWriter(), errorDetails);

            // Hata durumunda işlemlerinizi tamamladıktan sonra metodu sonlandırın
            return;
        }

        // Filtre zincirini devam ettirin
        filterChain.doFilter(request, response);
    }

}
