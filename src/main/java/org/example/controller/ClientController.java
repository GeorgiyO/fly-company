package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.example.session.NeedModerRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
class ClientController {

    final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Client> all() {
        return repository.all();
    }

    @GetMapping("/search/brand/{brandId}")
    List<Client> searchByBrandId(@PathVariable("brandId") int brandId) {
        return repository.searchByBrandId(brandId);
    }

    @GetMapping("/search/payment-type/{paymentTypeId}")
    List<Client> searchByPaymentType(@PathVariable("paymentTypeId") int paymentTypeId) {
        return repository.searchByPaymentType(paymentTypeId);
    }

    @GetMapping("/{id}")
    Client get(@PathVariable("id") String passportNumber) {
        return repository.get(passportNumber);
    }

    @NeedModerRole
    @PostMapping
    Client add(@RequestBody Client entity) {
        return repository.add(entity);
    }

    @NeedModerRole
    @PutMapping("/{id}")
    Client update(@PathVariable("id") String passportNumber, @RequestBody Client entity) {
        return repository.update(passportNumber, entity);
    }

    @NeedModerRole
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") String passportNumber) {
        repository.remove(passportNumber);
        return ResponseContainer.ok();
    }

}