package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.session.NeedAdminRole;
import org.example.session.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moder")
public class ModerController {

    final UserRepository repository;
    final Session session;

    public ModerController(UserRepository repository, Session session) {
        this.repository = repository;
        this.session = session;
    }

    @GetMapping
    @NeedAdminRole
    List<User> all() {
        return repository.allModers();
    }

    @PostMapping()
    @NeedAdminRole
    User add(@RequestBody User u) {
        return repository.addModer(u);
    }

    @GetMapping("/{id}")
    @NeedAdminRole
    User get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @DeleteMapping("/{id}")
    @NeedAdminRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

}
