package ar.com.ada.sb.security.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test-authorize")
public class AllController {

    @GetMapping({"/admin","/admin/"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity testTokenOne() {
        Map<String, String > body = new HashMap<>();
        body.put("admin", "pong");
        return ResponseEntity.ok(body);
    }

    @GetMapping({"/admin-manager","/admin-manager/"})
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity testTokenTwo() {
        Map<String, String > body = new HashMap<>();
        body.put("admin", "pong");
        return ResponseEntity.ok(body);
    }

    @GetMapping({"/all","/all/"})
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('USER')")
    public ResponseEntity testTokenThree() {
        Map<String, String > body = new HashMap<>();
        body.put("admin", "pong");
        return ResponseEntity.ok(body);
    }


}
