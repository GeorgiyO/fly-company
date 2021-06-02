package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.ScheduleItem;
import org.example.repository.ScheduleRepository;
import org.example.session.NeedCashierRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    final ScheduleRepository repository;

    public ScheduleController(ScheduleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<ScheduleItem> all() {
        return repository.all();
    }

    @GetMapping("/{id}")
    ScheduleItem get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @PostMapping
    @NeedCashierRole
    ScheduleItem add(@RequestBody ScheduleItem item) {
        return repository.add(item);
    }

    @DeleteMapping("/{id}")
    @NeedCashierRole
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }

    @PutMapping("/{id}")
    @NeedCashierRole
    ScheduleItem update(@PathVariable("id") int id, @RequestBody ScheduleItem item) {
        return repository.update(id, item);
    }

}
