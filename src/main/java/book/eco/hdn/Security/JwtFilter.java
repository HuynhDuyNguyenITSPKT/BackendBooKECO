package book.eco.hdn.Security;

import book.eco.hdn.Repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            String email = jwtService.extractEmail(token);

            userRepository.findByEmail(email).ifPresent(user -> {
                var authorities = user.getRoles()
                        .stream()
                        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(
                                "ROLE_" + role.getName()
                        ))
                        .toList();

                var auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        authorities
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }

        filterChain.doFilter(request, response);
    }
}