package ar.com.ada.sb.security.jwt.services.security;

import ar.com.ada.sb.security.jwt.component.security.JwtAuthProvider;
import ar.com.ada.sb.security.jwt.model.dto.security.JwtAuthRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("authServices")
public class AuthServices {

    @Autowired @Qualifier("jwtAuthProvider")
    private JwtAuthProvider jwtAuthProvider;

    @Autowired @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    public String jwtNewToken(JwtAuthRequestBody body) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                body.getUsername(),body.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtAuthProvider.doGeneratedToken(userDetails);
    }
}
