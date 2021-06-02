package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.Offer;
import org.example.repository.OffersRepository;
import org.example.repository.PassengersRepository;
import org.example.session.NeedAdminRole;
import org.example.session.NeedCashierRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class OfferController {

    final OffersRepository repository;
    final PassengersRepository passengersRepository;

    public OfferController(OffersRepository repository, PassengersRepository passengersRepository) {
        this.repository = repository;
        this.passengersRepository = passengersRepository;
    }

    @GetMapping
    @NeedCashierRole
    List<Offer> all() {
        return repository.all();
    }

    @GetMapping("/{id}")
    @NeedCashierRole
    Offer get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @PostMapping
    @NeedCashierRole
    Offer add(@RequestBody Offer offer) {
        return repository.add(offer);
    }

    @DeleteMapping("/{id}")
    @NeedAdminRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

}
