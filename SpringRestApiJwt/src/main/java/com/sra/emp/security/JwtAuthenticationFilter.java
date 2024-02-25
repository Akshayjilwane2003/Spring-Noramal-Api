package com.sra.emp.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Authorization
            String requestHeader = request.getHeader("Authorization");

            logger.info("Header: {}", requestHeader);

            String username = null;
            String token = null;

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                // Looking good
                token = requestHeader.substring(7);
                try {
                    username = this.jwtHelper.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    logger.info("Illegal Argument while fetching the username!!");
                } catch (ExpiredJwtException e) {
                    logger.info("Given jwt token is expired!!");
                } catch (MalformedJwtException e) {
                    logger.info("Some changes have been made in the token!! Invalid Token");
                } catch (Exception e) {
                    logger.error("An unexpected error occurred while parsing the token", e);
                }
            } else {
                logger.info("Invalid Header Value!!");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Fetch user details from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

                if (validateToken) {
                    // Set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.info("Validation fails!!");
                }
            }

        } catch (Exception e) {
            logger.error("Exception in JwtAuthenticationFilter", e);
        }

        filterChain.doFilter(request, response);
    }
}
