package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.Address;
import org.example.repository.AddressRepository;
import org.example.session.NeedAdminRole;
import org.example.session.NeedCashierRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    final AddressRepository repository;

    public AddressController(AddressRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Address> all() {
        return repository.all();
    }

    @GetMapping("/{id}")
    Address get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @PostMapping
    @NeedCashierRole
    Address add(@RequestBody Address a) {
        return repository.add(a);
    }

    @PutMapping("/{id}")
    @NeedCashierRole
    Address update(@PathVariable("id") int id, @RequestBody Address a) {
        return repository.update(id, a);
    }

    @DeleteMapping("/{id}")
    @NeedCashierRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

}
