package ar.com.ada.sb.security.jwt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Value("${auth.jwt.type}")
    private String authJwtType;

    @PostMapping({"/login","/login/"})
    public ResponseEntity createAuthToken(){
        return ResponseEntity.ok("ok");
    }

}
