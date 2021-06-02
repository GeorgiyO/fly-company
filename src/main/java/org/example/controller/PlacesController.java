package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.Place;
import org.example.repository.PlacesRepository;
import org.example.session.NeedCashierRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlacesController {

    final PlacesRepository repository;

    public PlacesController(PlacesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Place> all() {
        return repository.all();
    }

    @GetMapping("/free")
    List<Place> free() {
        return repository.free();
    }

    @GetMapping("/{id}")
    Place get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @PostMapping
    @NeedCashierRole
    Place add(@RequestBody Place place) {
        return repository.add(place);
    }

    @DeleteMapping("/{id}")
    @NeedCashierRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

}
