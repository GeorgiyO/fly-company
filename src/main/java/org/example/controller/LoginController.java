package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.example.domain.ResponseContainer;
import org.example.repository.UserRepository;
import org.example.session.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class LoginController {

    final Session session;
    final UserRepository repository;

    public LoginController(Session session, UserRepository repository) {
        this.session = session;
        this.repository = repository;
    }

    @PostMapping("/login")
    ResponseEntity<ResponseContainer> login(
        @RequestParam String login,
        @RequestParam String password
    ) {
        var u = repository.find(login);
        if (u.getPassword().equals(password)) {
            switch (u.getRole().getType()) {
                case "ADMIN":
                    session.setAdmin(true);
                    session.setModer(true);
                    return ResponseContainer.of(200, "admin");
                case "MODER":
                    session.setModer(true);
                    return ResponseContainer.of(200, "moder");
            }
        }
        return ResponseContainer.of(400, "incorrect password");
    }

    @PostMapping("/logout")
    ResponseEntity<ResponseContainer> logout() {
        session.setModer(false);
        session.setAdmin(false);
        return ResponseContainer.of(200, "succeed");
    }

    @GetMapping("/user-role")
    ResponseEntity<ResponseContainer> getUserRole() {
        return ResponseContainer.of(
            200, session.isAdmin() ? "admin" :
                 session.isModer() ? "moder" :
                 "none"
        );
    }
}
