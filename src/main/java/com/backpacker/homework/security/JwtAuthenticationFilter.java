package com.backpacker.homework.security;

import com.backpacker.homework.component.JwtAuthenticationProvider;
import com.backpacker.homework.member.service.CustomMemberDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomMemberDetailService userDetailsService;

    public JwtAuthenticationFilter(JwtAuthenticationProvider provider,
                                   CustomMemberDetailService userDetailsService){
        this.jwtAuthenticationProvider = provider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwtToken = getToken(request);
            if(jwtToken != null && jwtAuthenticationProvider.validateToken(jwtToken)){
                String userName = jwtAuthenticationProvider.getUserNameFromJwtToken(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            log.error("Cannot set user authentication {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");

        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")){
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

}
