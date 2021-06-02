package org.example.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.example.domain.ResponseContainer;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.session.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.server.WebSession;

import java.util.Map;
import java.util.function.Consumer;

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
                    session.admin(true);
                    session.cashier(true);
                    return ResponseContainer.of(200, "admin");
                case "CASHIER":
                    session.cashier(true);
                    return ResponseContainer.of(200, "cashier");
            }
        }
        return ResponseContainer.of(400, "incorrect password");
    }

    @PostMapping("/logout")
    ResponseEntity<ResponseContainer> logout() {
        log.info(session.cashier() + " " + session.admin());
        session.cashier(false);
        session.admin(false);
        return ResponseContainer.of(200, "succeed");
    }

    @GetMapping("/user-role")
    ResponseEntity<ResponseContainer> getUserRole() {
        return ResponseContainer.of(
            200, session.admin() ? "admin" :
                 session.cashier() ? "cashier" :
                 "none"
        );
    }
}
