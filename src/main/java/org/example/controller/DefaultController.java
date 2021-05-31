package org.example.controller;

import org.example.dto.*;
import org.example.entity.*;
import org.example.entity.Client;
import org.example.entity.Pilot;
import org.example.entity.Worker;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultController<DTO, T> {

    private final DefaultRepository<T> repository;
    private final Function<DTO, T> toEntity;
    private final Function<T, DTO> toDto;

    public DefaultController(DefaultRepository<T> repository, Function<DTO, T> toEntity, Function<T, DTO> toDto) {
        this.repository = repository;
        this.toEntity = toEntity;
        this.toDto = toDto;
    }

    @GetMapping
    List<DTO> getAll() {
        return repository
            .getAll()
            .stream()
            .map(toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    DTO getById(@PathVariable int id) {
        return toDto.apply(repository.get(id));
    }

    @PostMapping
    DTO add(@RequestBody DTO instance) {
        return toDto.apply(repository.add(toEntity.apply(instance)));
    }

    @PutMapping("/{id}")
    DTO update(@RequestBody DTO instance, @PathVariable int id) {
        return toDto.apply(repository.update(toEntity.apply(instance), id));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        repository.delete(id);
    }

}

@RestController
@RequestMapping("/clients")
class ClientController extends DefaultController<DtoClient, Client> {
    public ClientController(ClientRepository repository, CashRepository cashRepository, TicketRepository ticketRepository) {
        super(repository, DtoClient::fromDto, DtoClient::toDto);
    }
}

@RestController
@RequestMapping("/pilots")
class PilotController extends DefaultController<DtoPilot, Pilot> {
    public PilotController(PilotRepository repository) {
        super(repository, DtoPilot::fromDto, DtoPilot::toDto);
    }
}

@RestController
@RequestMapping("/workers")
class WorkerController extends DefaultController<DtoWorker, Worker> {
    public WorkerController(WorkerRepository repository) {
        super(repository, DtoWorker::fromDto, DtoWorker::toDto);
    }
}

@RestController
@RequestMapping("/arrives")
class ArriveController extends DefaultController<DtoArrive, Arrive> {
    public ArriveController(ArriveRepository repository) {
        super(repository, DtoArrive::fromDto, DtoArrive::toDto);
    }
}

@RestController
@RequestMapping("/cashes")
class CashController extends DefaultController<DtoCash, Cash> {
    public CashController(CashRepository repository) {
        super(repository, DtoCash::fromDto, DtoCash::toDto);
    }
}

@RestController
@RequestMapping("/flies")
class FlyController extends DefaultController<DtoFly, Fly> {
    public FlyController(FlyRepository repository) {
        super(repository, DtoFly::fromDto, DtoFly::toDto);
    }
}

@RestController
@RequestMapping("/fly-classes")
class FlyClassController extends DefaultController<DtoFlyClass, FlyClass> {
    public FlyClassController(FlyClassRepository repository) {
        super(repository, DtoFlyClass::fromDto, DtoFlyClass::toDto);
    }
}

@RestController
@RequestMapping("/lives")
class LiveController extends DefaultController<DtoLive, Live> {
    public LiveController(LiveRepository repository) {
        super(repository, DtoLive::fromDto, DtoLive::toDto);
    }
}

@RestController
@RequestMapping("/places")
class PlaceController extends DefaultController<DtoPlace, Place> {
    public PlaceController(PlaceRepository repository) {
        super(repository, DtoPlace::fromDto, DtoPlace::toDto);
    }
}

@RestController
@RequestMapping("/plans")
class PlanController extends DefaultController<DtoPlan, Plan> {
    public PlanController(PlanRepository repository) {
        super(repository, DtoPlan::fromDto, DtoPlan::toDto);
    }
}

@RestController
@RequestMapping("/tickets")
class TicketController extends DefaultController<DtoTicket, Ticket> {
    public TicketController(TicketRepository repository) {
        super(repository, DtoTicket::fromDto, DtoTicket::toDto);
    }
}