package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.entity.*;
import org.example.repository.*;
import org.example.session.NeedModerRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DefaultController<T> {

    final DefaultRepository<T> repository;

    public DefaultController(DefaultRepository<T> repository) {
        this.repository = repository;
    }

    @GetMapping
    List<T> all() {
        return repository.all();
    }

    @GetMapping("/{id}")
    T get(@PathVariable("id") int id) {
        return repository.get(id);
    }

    @NeedModerRole
    @PostMapping
    T add(@RequestBody T entity) {
        return repository.add(entity);
    }

    @NeedModerRole
    @PutMapping("/{id}")
    T update(@PathVariable("id") int id, @RequestBody T entity) {
        return repository.update(id, entity);
    }

    @NeedModerRole
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseContainer> remove(@PathVariable("id") int id) {
        repository.remove(id);
        return ResponseContainer.ok();
    }
}

@RestController
@RequestMapping("/body-type")
class BodyTypeController extends DefaultController<BodyType> {
    public BodyTypeController(BodyTypeRepository repository) {
        super(repository);
    }
}

@RestController
@RequestMapping("/brand")
class BrandController extends DefaultController<Brand> {

    final BrandRepository repository;

    public BrandController(BrandRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @GetMapping("/with-sales/{id}")
    public Brand withSales(@PathVariable("id") int id) {
        return repository.withSales(id);
    }

    @GetMapping("/sales-total")
    public Double salesTotal() {
        return repository.salesSum();
    }

}

@RestController
@RequestMapping("/country")
class CountryController extends DefaultController<Country> {
    public CountryController(CountryRepository repository) {
        super(repository);
    }
}

@RestController
@RequestMapping("/engine-type")
class EngineTypeController extends DefaultController<EngineType> {
    public EngineTypeController(EngineTypeRepository repository) {
        super(repository);
    }
}

@RestController
@RequestMapping("/product")
class ProductController extends DefaultController<Product> {

    final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @GetMapping("/search/{brandId}/{modelId}")
    List<Product> search(@PathVariable("brandId") int brandId,
                         @PathVariable("modelId") int modelId
    ) {
        return repository.searchByBrandAndModel(brandId, modelId);
    }

}

@RestController
@RequestMapping("/specification")
class SpecificationController extends DefaultController<Specification> {
    public SpecificationController(SpecificationRepository repository) {
        super(repository);
    }
}

@RestController
@RequestMapping("/model")
class ModelController extends DefaultController<Model> {
    public ModelController(ModelRepository repository) {
        super(repository);
    }
}