package org.example.controller;

import org.example.entity.PaymentType;
import org.example.repository.PaymentTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-type")
public class PaymentTypeController {

    final PaymentTypeRepository repository;

    public PaymentTypeController(PaymentTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<PaymentType> all() {
        return repository.all();
    }

}
