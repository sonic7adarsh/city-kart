package com.citykart.auth.filter;

import com.citykart.auth.context.UserContextHolder;
import com.citykart.auth.util.JwtUtil;
import com.citykart.user.entity.User;
import com.citykart.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        try {
            // ✅ Allow public endpoints without auth
            if (isPublicEndpoint(path)) {
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            log.info("Request URI: {}", path);
            log.info("Authorization Header: {}", authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);

                if (!jwtUtil.validateToken(token)) {
                    log.warn("Invalid JWT token");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                    return;
                }

                String phone = jwtUtil.extractPhone(token);
                Optional<User> userOpt = userRepository.findByPhone(phone);

                if (userOpt.isEmpty()) {
                    log.warn("User not found for phone: {}", phone);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
                    return;
                }

                User user = userOpt.get();
                UserContextHolder.set(user);

                // ✅ Set Spring Security Authentication context
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("✅ User authenticated: {} with role {}", user.getPhone(), user.getRole());

            } else {
                log.warn("Authorization header missing");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header missing");
                return;
            }

            filterChain.doFilter(request, response);

        } finally {
            // 🧹 Always clear context to avoid leaks
            UserContextHolder.clear();
            SecurityContextHolder.clearContext();
        }
    }

    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/public")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }
}
