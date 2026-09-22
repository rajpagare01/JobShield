package com.jobshield.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain
    ) throws IOException, ServletException {

        String authHeader = req.getHeader("Authorization");

        System.out.println("===== JWT FILTER =====");
        System.out.println("Request: " + req.getRequestURI());
        System.out.println("Authorization: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No Bearer token");
            chain.doFilter(req, res);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtService.extractEmail(token);

            System.out.println("JWT email: " + email);

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            System.out.println("UserDetails username: "
                    + userDetails.getUsername());

            boolean valid =
                    jwtService.isValidToken(
                            token,
                            userDetails.getUsername()
                    );

            System.out.println("JWT valid: " + valid);

            if (valid) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

                System.out.println("Authentication SET");
            }

        }catch (Exception e) {

            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");

            res.getWriter().write("""
            {
                "status": 401,
                "message": "Invalid or expired token"
            }
            """);

            return;
        }

        System.out.println(
                "Authentication before controller: "
                        + SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        chain.doFilter(req, res);
    }
}
