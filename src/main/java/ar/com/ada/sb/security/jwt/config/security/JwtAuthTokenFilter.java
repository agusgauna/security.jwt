package ar.com.ada.sb.security.jwt.config.security;

import ar.com.ada.sb.security.jwt.component.security.JwtAuthProvider;
import ar.com.ada.sb.security.jwt.services.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Value("${auth.jwt.header.key}")
    private String authHeadersKey;

    @Autowired @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    @Autowired @Qualifier("jwtUserDetailsService")
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String authToken = req.getHeader(authHeadersKey);
        String username = jwtAuthProvider.getUsernameFromToken(authToken);

        if (authToken != null && jwtAuthProvider.isBearer(authToken) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if (jwtAuthProvider.validateToken(authToken,userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource(). buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req,res);
    }
}
