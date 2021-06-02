drop database if exists flycompany;
create database flycompany;

use flycompany;

create table roles
(
    id int primary key auto_increment,
    type varchar(50) not null unique
);

create table users
(
    id int primary key auto_increment,
    login varchar(250) not null unique,
    password varbinary(250) not null,
    role_id int,
    foreign key (role_id) references roles (id) on delete cascade on update cascade
);

create table planes
(
    id int primary key auto_increment,
    description varchar(250) not null
);

create table address
(
    id int primary key auto_increment,
    value varchar(250) not null
);

create table schedule
(
    id int primary key auto_increment,
    date datetime not null,
    price decimal not null,
    plane_id int,
    from_addr_id int,
    to_addr_id int,
    foreign key (plane_id) references planes (id) on delete cascade on update cascade,
    foreign key (from_addr_id) references address (id) on delete cascade on update cascade,
    foreign key (to_addr_id) references address (id) on delete cascade on update cascade

);

create table places
(
    id int primary key auto_increment,
    total int not null,
    free int not null,
    schedule_id int,
    foreign key (schedule_id) references schedule (id) on delete cascade on update cascade
);

create table passengers
(
    id int primary key auto_increment,
    first_name varchar(250) not null,
    second_name varchar(250) not null,
    patronymic varchar(250) not null,
    doc_type varchar(250) not null,
    doc_number varchar(250) not null
);

create table offers
(
    id int primary key auto_increment,
    place_id int,
    passenger_id int,
    foreign key (place_id) references places (id) on delete cascade on update cascade,
    foreign key (passenger_id) references passengers (id) on delete cascade on update cascade
);

insert into roles (type)
values ('CASHIER');
insert into roles (type)
values ('ADMIN');

insert into users (login, password, role_id)
values ('admin', 'admin', (select id from roles where type = 'ADMIN'));

insert into users (login, password, role_id)
values ('cashier', 'cashier', (select id from roles where type = 'CASHIER'));

delimiter $

create procedure user_find(in _login varchar(250))
select u.id, u.login, u.password, r.type as role
from users u
         join roles r on r.id = u.role_id
where u.login = _login;
$

create procedure user_get(in _id int)
select u.id, u.login, u.password, r.type as role
from users u
         join roles r on r.id = u.role_id
where u.id = _id;
$

create procedure cashiers_all()
select u.id, u.login, u.password, r.type as role
from users u
         join roles r on r.id = u.role_id
where r.type = 'CASHIER';
$

create procedure cashiers_add(in _login varchar(250), in _password varchar(250))
begin
    start transaction;
    insert into users (login, password, role_id)
    values (_login, _password, (select id from roles where type = 'CASHIER'));
    call user_get(last_insert_id());
    commit;
end;
$

create procedure users_remove(in _id int)
delete
from users
where id = _id;
$

######################

create procedure planes_all()
select *
from planes;
$

create procedure planes_get(in _id int)
select *
from planes
where id = _id;
$

create procedure planes_add(in _description varchar(250))
begin
    start transaction;
    insert into planes (description)
    values (_description);
    select * from planes where id = last_insert_id();
    commit;
end $

create procedure planes_update(in _id int, in _description varchar(250))
begin
    start transaction;
    update planes set description = _description where id = _id;
    select * from planes where id = _id;
    commit;
end $

create procedure planes_remove(in _id int)
delete
from planes
where id = _id;
$

################################

create procedure address_all()
select *
from address;
$

create procedure address_get(in _id int)
select *
from address
where id = _id;
$

create procedure address_add(in _value varchar(250))
begin
    start transaction;
    insert into address (value)
    values (_value);
    select * from address where id = last_insert_id();
    commit;
end $

create procedure address_update(in _id int, in _value varchar(250))
begin
    start transaction;
    update address
    set value = _value
    where id = _id;
    select * from address where id = _id;
    commit;
end $

create procedure address_remove(in _id int)
delete
from address
where id = _id;
$

####################################

create procedure schedule_all()
select *
from schedule;
$

create procedure schedule_get(in _id int)
select *
from schedule
where id = _id;
$

create procedure schedule_add(
    in _date datetime,
    in _price decimal,
    in _plane_id int,
    in _from_addr_id int,
    in _to_addr_id int
)
begin
    start transaction;
    insert into schedule (date, price, plane_id, from_addr_id, to_addr_id)
    values (_date, _price, _plane_id, _from_addr_id, _to_addr_id);
    select * from schedule where id = last_insert_id();
    commit;
end $

create procedure schedule_update(
    in _id int,
    in _date datetime,
    in _price decimal,
    in _plane_id int,
    in _from_addr_id int,
    in _to_addr_id int
)
begin
    start transaction;
    update schedule
    set date         = _date,
        price        = _price,
        plane_id     = _plane_id,
        from_addr_id = _from_addr_id,
        to_addr_id   = _to_addr_id
    where id = _id;
    select * from schedule where id = _id;
    commit;
end $

create procedure schedule_remove(in _id int)
delete
from schedule
where id = _id;
$

#####################################

create procedure places_all()
select *
from places;
$

create procedure places_get(in _id int)
select *
from places
where id = _id;
$

create procedure places_add(in _total int, _schedule_id int)
begin
    start transaction;
    insert into places (total, free, schedule_id)
    values (_total, _total, _schedule_id);
    select * from places where id = last_insert_id();
    commit;
end $

create procedure places_free()
select *
from places
where free > 0;
$

create procedure places_remove(in _id int)
delete
from places
where id = _id;
$

#####################################

create procedure passengers_all()
select *
from passengers;
$

create procedure passengers_get(in _id int)
select *
from passengers
where id = _id;
$

create procedure passengers_add(
    in _first_name varchar(250),
    in _second_name varchar(250),
    in _patronymic varchar(250),
    in _doc_type varchar(250),
    in _doc_number varchar(250)
)
begin
    start transaction;
    insert into passengers (first_name, second_name, patronymic, doc_type, doc_number)
    values (_first_name, _second_name, _patronymic, _doc_type, _doc_number);
    select * from passengers where id = last_insert_id();
    commit;
end $

create procedure passengers_update(
    in _id int,
    in _first_name varchar(250),
    in _second_name varchar(250),
    in _patronymic varchar(250),
    in _doc_type varchar(250),
    in _doc_number varchar(250)
)
begin
    start transaction;
    update passengers
    set first_name  = _first_name,
        second_name = _second_name,
        patronymic  = _patronymic,
        doc_type    = _doc_type,
        doc_number  = _doc_number
    where id = _id;
    select * from passengers where id = _id;
    commit;
end $

create procedure passengers_remove(in _id int)
delete
from passengers
where id = _id;
$

#####################################

create procedure offers_all()
select *
from offers;
$

create procedure offers_get(in _id int)
select *
from offers
where id = _id;
$

create procedure offers_add(in _place_id int, in _passenger_id int)
begin
    start transaction;
    set @id = -1;
    if ((select free from places where id = _place_id) > 0) then
        insert into offers (place_id, passenger_id)
        values (_place_id, _passenger_id);
        update places
        set free = free - 1
        where id = _place_id;
        set @id = last_insert_id();
    end if;
    select * from offers where id = @id;
    commit;
end;
$

create procedure offers_remove(in _id int)
delete
from offers
where id = _id;
$

delimiter ;
