package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.entity.*;
import org.example.entity.Client;
import org.example.entity.Pilot;
import org.example.entity.Worker;
import org.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
@Log4j2
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    ArriveRepository arriveRepository;

    @Autowired
    CashRepository cashRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FlyClassRepository flyClassRepository;

    @Autowired
    FlyRepository flyRepository;

    @Autowired
    LiveRepository liveRepository;

    @Autowired
    PilotRepository pilotRepository;

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    WorkerRepository workerRepository;

    @PostConstruct
    void init() {

        Arrive.repo = arriveRepository;
        Cash.repo = cashRepository;
        Client.repo = clientRepository;
        FlyClass.repo = flyClassRepository;
        Fly.repo = flyRepository;
        Live.repo = liveRepository;
        Pilot.repo = pilotRepository;
        Place.repo = placeRepository;
        Plan.repo = planRepository;
        Ticket.repo = ticketRepository;
        Worker.repo = workerRepository;

        for (var repo : new JpaRepository[]{
            arriveRepository,
            cashRepository,
            clientRepository,
            flyClassRepository,
            flyRepository,
            liveRepository,
            pilotRepository,
            placeRepository,
            planRepository,
            ticketRepository,
            workerRepository
        }) {
            repo.deleteAll();
        }

        var arrive = new Arrive(); //
        var cash = new Cash(); //
        var client = new Client(); //
        var flyClass = new FlyClass(); //
        var fly = new Fly(); //
        var live = new Live(); //
        var pilot = new Pilot(); //
        var place = new Place();
        var plan = new Plan(); //
        var ticket = new Ticket(); //
        var worker = new Worker(); //

        arrive.setAddress("arrive address");
        arrive.setName("arrive name");
        arrive = arriveRepository.add(arrive);

        cash.setNumber("cash number");
        cash = cashRepository.add(cash);

        live.setAddress("live address");
        live.setName("live name");
        live = liveRepository.add(live);

        fly.setNumber("fly number");
        fly.setDate(new Date());
        fly.setArriveId(arrive.getId());
        fly.setLiveId(live.getId());
        fly = flyRepository.add(fly);

        worker.setFio("worker fio");
        worker.setTel("9993335599");
        worker = workerRepository.add(worker);

        plan.setNumber("plan numb");
        plan.setKind("plan kind");
        plan.setFlyId(fly.getId());
        plan.setWorkerId(worker.getId());
        plan = planRepository.add(plan);

        ticket.setPrice(123.45);
        ticket.setNumber("ticket num");
        ticket.setCashId(cash.getId());
        ticket.setPlanId(plan.getId());
        ticket = ticketRepository.add(ticket);

        client.setFio("client fio");
        client.setPassword("client password");
        client.setCashId(cash.getId());
        client.setTicketId(ticket.getId());
        client = clientRepository.add(client);

        flyClass.setName("class name");
        flyClass.setPlanId(plan.getId());
        flyClass = flyClassRepository.add(flyClass);

        pilot.setFio("pilot fio");
        pilot.setTel("9993335599");
        pilot.setPlanId(plan.getId());
        pilot = pilotRepository.add(pilot);

        place.setNumber("numb");
        place.setFlyClassId(flyClass.getId());
        place = placeRepository.add(place);

        for (Object o : new Object[]{arrive, cash, client, flyClass, fly, live, pilot, place, plan, ticket, worker}) {
            log.info(o);
        }

        log.info("trying to update cash: ");
        log.info("before: " + cash);
        cash.setNumber("updated");
        cash = cashRepository.update(cash, cash.getId());
        log.info("after: " + cash);
    }

}
