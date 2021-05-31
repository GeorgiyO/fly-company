use flycompany;
delimiter ;

drop procedure if exists client_add;
drop procedure if exists client_update;
drop procedure if exists client_remove;
drop procedure if exists client_get;
drop procedure if exists client_get_all;

drop procedure if exists pilot_add;
drop procedure if exists pilot_update;
drop procedure if exists pilot_remove;
drop procedure if exists pilot_get;
drop procedure if exists pilot_get_all;

drop procedure if exists worker_add;
drop procedure if exists worker_update;
drop procedure if exists worker_remove;
drop procedure if exists worker_get;
drop procedure if exists worker_get_all;

drop procedure if exists arrive_add;
drop procedure if exists arrive_update;
drop procedure if exists arrive_remove;
drop procedure if exists arrive_get;
drop procedure if exists arrive_get_all;

drop procedure if exists cash_add;
drop procedure if exists cash_update;
drop procedure if exists cash_remove;
drop procedure if exists cash_get;
drop procedure if exists cash_get_all;

drop procedure if exists fly_add;
drop procedure if exists fly_update;
drop procedure if exists fly_remove;
drop procedure if exists fly_get;
drop procedure if exists fly_get_all;

drop procedure if exists class_add;
drop procedure if exists class_update;
drop procedure if exists class_remove;
drop procedure if exists class_get;
drop procedure if exists class_get_all;


drop procedure if exists live_add;
drop procedure if exists live_update;
drop procedure if exists live_remove;
drop procedure if exists live_get;
drop procedure if exists live_get_all;

drop procedure if exists place_add;
drop procedure if exists place_update;
drop procedure if exists place_remove;
drop procedure if exists place_get;
drop procedure if exists place_get_all;

drop procedure if exists plan_add;
drop procedure if exists plan_update;
drop procedure if exists plan_remove;
drop procedure if exists plan_get;
drop procedure if exists plan_get_all;

drop procedure if exists ticket_add;
drop procedure if exists ticket_update;
drop procedure if exists ticket_remove;
drop procedure if exists ticket_get;
drop procedure if exists ticket_get_all;

delimiter $$

# clients

create procedure client_add(
    in fio varchar(45),
    in password varchar(45),
    in ticket int,
    in cash int
)
begin
    start transaction;
    insert into client (fio_client, password_client, id_ticket, id_cash)
    values (fio, password, ticket, cash);
    select * from client where id_client = last_insert_id();
    commit;
end $$

create procedure client_update(
    in fio varchar(45),
    in password varchar(45),
    in ticket int,
    in cash int,
    in id int
)
begin
    start transaction;
    update client
    set fio_client      = fio,
        password_client = password,
        id_ticket       = ticket,
        id_cash         = cash
    where id_client = id;
    select * from client where id_client = id;
    commit;
end $$

create procedure client_remove(
    in id int
)
begin
    start transaction;
    delete from client where id_client = id;
    commit;
end $$

create procedure client_get(
    in id int
)
select *
from client
where id_client = id;
$$

create procedure client_get_all()
select *
from client;
$$

#######################
# pilots

create procedure pilot_add(
    in fio varchar(45),
    in tel varchar(10),
    in plan int
)
begin
    start transaction;
    insert into pilot (fio_pilot, tel_pilot, id_plan)
    values (fio, tel, plan);
    select * from pilot where id_pilot = last_insert_id();
    commit;
end $$

create procedure pilot_update(
    in fio varchar(45),
    in tel varchar(10),
    in plan int,
    in id int
)
begin
    start transaction;
    update pilot
    set fio_pilot = fio,
        tel_pilot = tel,
        id_plan   = plan
    where id_pilot = id;
    select * from pilot where id_pilot = id;
    commit;
end $$

create procedure pilot_remove(
    in id int
)
begin
    start transaction;
    delete from pilot where id_pilot = id;
    commit;
end $$

create procedure pilot_get(
    in id int
)
select *
from pilot
where id_pilot = id;
$$

create procedure pilot_get_all()
select *
from pilot;
$$

#####################################
# worker

create procedure worker_add(
    in fio varchar(45),
    in tel varchar(10)
)
begin
    start transaction;
    insert into worker (fio_worker, tel_worker)
    values (fio, tel);
    select * from worker where id_worker = last_insert_id();
    commit;
end $$

create procedure worker_update(
    in fio varchar(45),
    in tel varchar(10),
    in id int
)
begin
    start transaction;
    update worker
    set fio_worker = fio,
        tel_worker = tel
    where id_worker = id;
    select * from worker where id_worker = id;
    commit;
end $$

create procedure worker_remove(
    in id int
)
begin
    start transaction;
    delete from worker where id_worker = id;
    commit;
end $$

create procedure worker_get(
    in id int
)
select *
from worker
where id_worker = id;
$$

create procedure worker_get_all()
select *
from worker;
$$

#########################################
# arrive

create procedure arrive_add(
    in name varchar(30),
    in address varchar(45)
)
begin
    start transaction;
    insert into arrive (name_arrive, address_arrive)
    values (name, address);
    select * from arrive where id_arrive = last_insert_id();
    commit;
end $$

create procedure arrive_update(
    in name varchar(30),
    in address varchar(45),
    in id int
)
begin
    start transaction;
    update arrive
    set name_arrive    = name,
        address_arrive = address
    where id_arrive = id;
    select * from arrive where id_arrive = id;
    commit;
end $$

create procedure arrive_remove(
    in id int
)
begin
    start transaction;
    delete from arrive where id_arrive = id;
    commit;
end $$

create procedure arrive_get(
    in id int
)
select *
from arrive
where id_arrive = id;
$$

create procedure arrive_get_all()
select *
from arrive;
$$

#######################################
# cash

create procedure cash_add(
    in number varchar(45)
)
begin
    start transaction;
    insert into cash (number_cash)
    values (number);
    select * from cash where id_cash = last_insert_id();
    commit;
end $$

create procedure cash_update(
    in number varchar(45),
    in id int
)
begin
    start transaction;
    update cash
    set number_cash = number
    where id_cash = id;
    select * from cash where id_cash = id;
    commit;
end $$

create procedure cash_remove(
    in id int
)
begin
    start transaction;
    delete from cash where id_cash = id;
    commit;
end $$

create procedure cash_get(
    in id int
)
select *
from cash
where id_cash = id;
$$

create procedure cash_get_all()
select *
from cash;
$$

#######################################
# fly

create procedure fly_add(
    in number varchar(10),
    in _date date,
    in arrive int,
    in live int
)
begin
    start transaction;
    insert into fly (number_fly, date_fly, id_arrive, id_live)
    values (number, _date, arrive, live);
    select * from fly where id_fly = last_insert_id();
    commit;
end $$

create procedure fly_update(
    in number varchar(10),
    in _date date,
    in arrive int,
    in live int,
    in id int
)
begin
    start transaction;
    update fly
    set number_fly = number,
        date_fly   = _date,
        id_arrive  = arrive,
        id_live    = live
    where id_fly = id;
    select * from fly where id_fly = id;
    commit;
end $$

create procedure fly_remove(
    in id int
)
begin
    start transaction;
    delete from fly where id_fly = id;
    commit;
end $$

create procedure fly_get(
    in id int
)
select *
from fly
where id_fly = id;
$$

create procedure fly_get_all()
select *
from fly;
$$

#######################################
# fly_class

create procedure class_add(
    in name varchar(10),
    in plan int
)
begin
    start transaction;
    insert into class (name_class, id_plan)
    values (name, plan);
    select * from class where id_class = last_insert_id();
    commit;
end $$

create procedure class_update(
    in name varchar(10),
    in plan int,
    in id int
)
begin
    start transaction;
    update class
    set name_class = name,
        id_plan    = plan
    where id_class = id;
    select * from class where id_class = id;
    commit;
end $$

create procedure class_remove(
    in id int
)
begin
    start transaction;
    delete from class where id_class = id;
    commit;
end $$

create procedure class_get(
    in id int
)
select *
from class
where id_class = id;
$$

create procedure class_get_all()
select *
from class;
$$

#######################################
# live

create procedure live_add(
    in name varchar(30),
    in address varchar(45)
)
begin
    start transaction;
    insert into live (name_live, address_live)
    values (name, address);
    select * from live where id_live = last_insert_id();
    commit;
end $$

create procedure live_update(
    in name varchar(30),
    in address varchar(45),
    in id int
)
begin
    start transaction;
    update live
    set name_live    = name,
        address_live = address
    where id_live = id;
    select * from live where id_live = id;
    commit;
end $$

create procedure live_remove(
    in id int
)
begin
    start transaction;
    delete from live where id_live = id;
    commit;
end $$

create procedure live_get(
    in id int
)
select *
from live
where id_live = id;
$$

create procedure live_get_all()
select *
from live;
$$

#######################################
# place

create procedure place_add(
    in class int,
    in number varchar(4)
)
begin
    start transaction;
    insert into place (id_class, number_place)
    values (class, number);
    select * from place where id_place = last_insert_id();
    commit;
end $$

create procedure place_update(
    in class int,
    in number varchar(4),
    in id int
)
begin
    start transaction;
    update place
    set id_class     = class,
        number_place = number
    where id_place = id;
    select * from place where id_place = id;
    commit;
end $$

create procedure place_remove(
    in id int
)
begin
    start transaction;
    delete from place where id_place = id;
    commit;
end $$

create procedure place_get(
    in id int
)
select *
from place
where id_place = id;
$$

create procedure place_get_all()
select *
from place;
$$

###########################################
# plan

create procedure plan_add(
    in number varchar(10),
    in kind varchar(10),
    in worker int,
    in fly int
)
begin
    start transaction;
    insert into plan (number_plan, kind_plan, id_worker, id_fly)
    values (number, kind, worker, fly);
    select * from plan where id_plan = last_insert_id();
    commit;
end $$

create procedure plan_update(
    in number varchar(10),
    in kind varchar(10),
    in worker int,
    in fly int,
    in id int
)
begin
    start transaction;
    update plan
    set number_plan = number,
        kind_plan   = kind,
        id_worker   = worker,
        id_fly      = fly
    where id_plan = id;
    select * from plan where id_plan = id;
    commit;
end $$

create procedure plan_remove(
    in id int
)
begin
    start transaction;
    delete from plan where id_plan = id;
    commit;
end $$

create procedure plan_get(
    in id int
)
select *
from plan
where id_plan = id;
$$

create procedure plan_get_all()
select *
from plan;
$$

#############################################
# ticket

create procedure ticket_add(
    in number varchar(10),
    in price decimal(5, 2),
    in cash int,
    in plan int
)
begin
    start transaction;
    insert into ticket (number_ticket, price_ticket, id_cash, id_plan)
    values (number, price, cash, plan);
    select * from ticket where id_ticket = last_insert_id();
    commit;
end $$

create procedure ticket_update(
    in number varchar(10),
    in price decimal(5, 2),
    in cash int,
    in plan int,
    in id int
)
begin
    start transaction;
    update ticket
    set number_ticket = number,
        price_ticket  = price,
        id_cash       = cash,
        id_plan       = plan
    where id_ticket = id;
    select * from ticket where id_ticket = id;
    commit;
end $$

create procedure ticket_remove(
    in id int
)
begin
    start transaction;
    delete from ticket where id_ticket = id;
    commit;
end $$

create procedure ticket_get(
    in id int
)
select *
from ticket
where id_ticket = id;
$$

create procedure ticket_get_all()
select *
from ticket;
$$

delimiter ;
