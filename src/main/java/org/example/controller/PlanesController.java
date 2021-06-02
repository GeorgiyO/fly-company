package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.Plane;
import org.example.repository.PlanesRepository;
import org.example.session.NeedCashierRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes")
public class PlanesController {

    final PlanesRepository repository;

    public PlanesController(PlanesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Plane> all() {
        return repository.all();
    }

    @GetMapping("/{id}")
    Plane get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @PostMapping
    @NeedCashierRole
    Plane add(@RequestBody Plane p) {
        return repository.add(p);
    }

    @DeleteMapping("/{id}")
    @NeedCashierRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

    @PutMapping("/{id}")
    @NeedCashierRole
    Plane update(@PathVariable("id") int id, @RequestBody Plane p) {
        return repository.update(id, p);
    }

}
