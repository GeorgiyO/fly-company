# noinspection SpellCheckingInspectionForFile

drop database if exists car_company;
create database car_company;

use car_company;

create table role
(
    id int primary key auto_increment,
    type varchar(50) not null unique
);

create table payment_type
(
    id int primary key auto_increment,
    type varchar(250) not null unique
);

create table user
(
    id int primary key auto_increment,
    login varchar(250) not null unique,
    password varbinary(250) not null,
    role_id int,
    foreign key (role_id) references role (id) on delete cascade on update cascade
);

create table engine_type
(
    id int primary key auto_increment,
    type varchar(250) not null unique
);

create table body_type
(
    id int primary key auto_increment,
    type varchar(250) not null unique
);

create table specification
(
    id int primary key auto_increment,
    doors_count int not null,
    seats_count int not null,
    engine_working_volume decimal not null,
    engine_pos varchar(250) not null,
    body_type_id int,
    engine_type_id int,
    foreign key (body_type_id) references body_type (id) on delete cascade on update cascade,
    foreign key (engine_type_id) references engine_type (id) on delete cascade on update cascade
);

create table brand
(
    id int primary key auto_increment,
    name varchar(250) not null unique
);

create table country
(
    id int primary key auto_increment,
    name varchar(250) not null unique
);

create table model
(
    id int primary key auto_increment,
    name varchar(250) not null unique
);

create table product
(
    code int primary key auto_increment,
    available int(1) not null,
    availability_date date,
    price decimal not null,
    model_id int,
    country_id int,
    brand_id int,
    specification_id int,
    foreign key (model_id) references model (id) on delete cascade on update cascade,
    foreign key (country_id) references country (id) on delete cascade on update cascade,
    foreign key (brand_id) references brand (id) on delete cascade on update cascade,
    foreign key (specification_id) references specification (id) on delete cascade on update cascade
);

create table client
(
    passport_number varchar(10) primary key not null,
    name varchar(250) not null,
    phone varchar(20) not null,
    address varchar(250) not null,
    delivery int(1) not null,
    payment_is_credit int(1) not null,
    payment_type_id int,
    product_code int,
    foreign key (payment_type_id) references payment_type (id) on delete cascade on update cascade,
    foreign key (product_code) references product (code) on delete cascade on update cascade
);

#############################

insert into role (type)
values ('ADMIN');
insert into role (type)
values ('MODER');

insert into payment_type(type)
values ('CASH');
insert into payment_type(type)
values ('TRANSFER');

insert into user (login, password, role_id)
values ('admin', 'admin', (select id from role where type = 'ADMIN'));
insert into user (login, password, role_id)
values ('moder', 'moder', (select id from role where type = 'MODER'));

delimiter $

create procedure payment_type_all()
select *
from payment_type;
$

create procedure payment_type_get(in _id int)
select *
from payment_type
where id = _id;
$

#########################

create procedure user_find(in _login varchar(250))
select u.id, u.login, u.password, r.type as role
from user u
         join role r on r.id = u.role_id
where u.login = _login;
$

create procedure user_get(in _id int)
select u.id, u.login, u.password, r.type as role
from user u
         join role r on r.id = u.role_id
where u.id = _id;
$

create procedure moder_all()
select u.id, u.login, u.password, r.type as role
from user u
         join role r on r.id = u.role_id
where r.type = 'MODER';
$

create procedure moder_add(in _login varchar(250), in _password varchar(250))
begin
    start transaction;
    insert into user (login, password, role_id)
    values (_login, _password, (select id from role where type = 'MODER'));
    call user_get(last_insert_id());
    commit;
end;
$

create procedure user_delete(in _id int)
delete
from user
where id = _id;
$

######################

create procedure engine_type_all()
select *
from engine_type;
$

create procedure engine_type_get(in _id int)
select *
from engine_type
where id = _id;
$

create procedure engine_type_add(in _type varchar(250))
begin
    start transaction;
    insert into engine_type (type)
    values (_type);
    call engine_type_get(last_insert_id());
    commit;
end $

create procedure engine_type_update(in _id int, in _type varchar(250))
begin
    start transaction;
    update engine_type
    set type = _type
    where id = _id;
    call engine_type_get(_id);
    commit;
end $

create procedure engine_type_delete(in _id int)
begin
    start transaction;
    delete
    from engine_type
    where id = _id;
    commit;
end $

######################

create procedure body_type_all()
select *
from body_type;
$

create procedure body_type_get(in _id int)
select *
from body_type
where id = _id;
$

create procedure body_type_add(in _type varchar(250))
begin
    start transaction;
    insert into body_type (type)
    values (_type);
    call body_type_get(last_insert_id());
    commit;
end $

create procedure body_type_update(in _id int, in _type varchar(250))
begin
    start transaction;
    update body_type
    set type = _type
    where id = _id;
    call body_type_get(_id);
    commit;
end $

create procedure body_type_delete(in _id int)
begin
    start transaction;
    delete
    from body_type
    where id = _id;
    commit;
end $

######################

create procedure specification_all()
select *
from specification;
$

create procedure specification_get(in _id int)
select *
from specification
where id = _id;
$

create procedure specification_add(
    in _doors_count int,
    in _seats_count int,
    in _engine_working_volume decimal,
    in _engine_pos varchar(250),
    in _body_type_id int,
    in _engine_type_id int
)
begin
    start transaction;
    insert into specification (doors_count,
                               seats_count,
                               engine_working_volume,
                               engine_pos,
                               body_type_id,
                               engine_type_id)
    values (_doors_count,
            _seats_count,
            _engine_working_volume,
            _engine_pos,
            _body_type_id,
            _engine_type_id);
    call specification_get(last_insert_id());
    commit;
end $

create procedure specification_update(
    in _id int,
    in _doors_count int,
    in _seats_count int,
    in _engine_working_volume decimal,
    in _engine_pos varchar(250),
    in _body_type_id int,
    in _engine_type_id int
)
begin
    start transaction;
    update specification
    set doors_count           = _doors_count,
        seats_count           = _seats_count,
        engine_working_volume = _engine_working_volume,
        engine_pos            = _engine_pos,
        body_type_id          = _body_type_id,
        engine_type_id        = _engine_type_id
    where id = _id;
    call specification_get(_id);
    commit;
end $

create procedure specification_delete(in _id int)
begin
    start transaction;
    delete
    from specification
    where id = _id;
    commit;
end $

######################

create procedure brand_all()
select *
from brand;
$

create procedure brand_get(in _id int)
select *
from brand
where id = _id;
$

create procedure brand_add(
    in _name varchar(250)
)
begin
    start transaction;
    insert into brand (name)
    values (_name);
    call brand_get(last_insert_id());
    commit;
end $

create procedure brand_update(
    in _id int,
    in _name varchar(250)
)
begin
    start transaction;
    update brand
    set name = _name
    where id = _id;
    call brand_get(_id);
    commit;
end $

create procedure brand_delete(in _id int)
begin
    start transaction;
    delete
    from brand
    where id = _id;
    commit;
end $

##

create procedure brand_with_sales(in _id int)
select id,
       name,
       (select sum(price) from product where brand_id = _id) as sales
from brand
where id = _id;
$

create procedure brands_sales_total()
select sum(price) sales
from product;
$

######################

create procedure country_all()
select *
from country;
$

create procedure country_get(in _id int)
select *
from country
where id = _id;
$

create procedure country_add(
    in _name varchar(250)
)
begin
    start transaction;
    insert into country (name)
    values (_name);
    call country_get(last_insert_id());
    commit;
end $

create procedure country_update(
    in _id int,
    in _name varchar(250)
)
begin
    start transaction;
    update country
    set name = _name
    where id = _id;
    call country_get(_id);
    commit;
end $

create procedure country_delete(in _id int)
begin
    start transaction;
    delete
    from country
    where id = _id;
    commit;
end $

######################

create procedure model_all()
select *
from model;
$

create procedure model_get(in _id int)
select *
from model
where id = _id;
$

create procedure model_add(
    in _name varchar(250)
)
begin
    start transaction;
    insert into model (name)
    values (_name);
    call model_get(last_insert_id());
    commit;
end $

create procedure model_update(
    in _id int,
    in _name varchar(250)
)
begin
    start transaction;
    update model
    set name = _name
    where id = _id;
    call model_get(_id);
    commit;
end $

create procedure model_delete(in _id int)
begin
    start transaction;
    delete
    from model
    where id = _id;
    commit;
end $

######################

create procedure product_all()
select *
from product;
$

create procedure product_search_brand_model(
    in _brand_id int,
    in _model_id int
)
select *
from product
where brand_id = _brand_id
  and model_id = _model_id;
$

create procedure product_get(in _code int)
select *
from product
where code = _code;
$

create procedure product_add(
    in _aviliable int(1),
    in _aviliability_date date,
    in _price decimal,
    in _model_id int,
    in _country_id int,
    in _brand_id int,
    in _specification_id int
)
begin
    start transaction;
    insert into product (available,
                         availability_date,
                         price,
                         model_id,
                         country_id,
                         brand_id,
                         specification_id)
    values (_aviliable,
            _aviliability_date,
            _price,
            _model_id,
            _country_id,
            _brand_id,
            _specification_id);
    call product_get(last_insert_id());
    commit;
end $

create procedure product_update(
    in _code int,
    in _aviliable int(1),
    in _aviliability_date date,
    in _price decimal,
    in _model_id int,
    in _country_id int,
    in _brand_id int,
    in _specification_id int
)
begin
    start transaction;
    update product
    set available         = _aviliable,
        availability_date = _aviliability_date,
        price             = _price,
        model_id          = _model_id,
        country_id        = _country_id,
        brand_id          = _brand_id,
        specification_id  = _specification_id
    where code = _code;
    call product_get(_code);
    commit;
end $

create procedure product_delete(in _code int)
begin
    start transaction;
    delete
    from product
    where code = _code;
    commit;
end $

######################

create procedure client_all()
select *
from client;
$

create procedure client_get(in _passport_number varchar(10))
select *
from client
where passport_number = _passport_number;
$

create procedure client_search_brand(in _brand_id int)
select *
from client
where product_code in (
    select code
    from product
    where brand_id = _brand_id
);
$

create procedure client_search_payment_type(in _payment_type_id int)
select *
from client
where payment_type_id = _payment_type_id;
$

create procedure client_add(
    in _passport_number varchar(10),
    in _name varchar(250),
    in _phone varchar(20),
    in _address varchar(250),
    in _delivery int(1),
    in _payment_is_credit int(1),
    in _payment_type_id int,
    in _product_code int
)
begin
    start transaction;
    insert into client (passport_number,
                        name,
                        phone,
                        address,
                        delivery,
                        payment_is_credit,
                        payment_type_id,
                        product_code)
    values (_passport_number,
            _name,
            _phone,
            _address,
            _delivery,
            _payment_is_credit,
            _payment_type_id,
            _product_code);
    call client_get(_passport_number);
    commit;
end $

create procedure client_update(
    in _old_passport_number varchar(10),
    in _passport_number varchar(10),
    in _name varchar(250),
    in _phone varchar(20),
    in _address varchar(250),
    in _delivery int(1),
    in _payment_is_credit int(1),
    in _payment_type_id int,
    in _product_code int
)
begin
    start transaction;
    update client
    set passport_number   = _passport_number,
        name              = _name,
        phone             = _phone,
        address           = _address,
        delivery          = _delivery,
        payment_is_credit = _payment_is_credit,
        payment_type_id   = _payment_type_id,
        product_code      = _product_code
    where passport_number = _old_passport_number;
    call client_get(_passport_number);
    commit;
end $

create procedure client_delete(in _passport_number varchar(10))
begin
    start transaction;
    delete
    from client
    where passport_number = _passport_number;
    commit;
end $

######################

delimiter ;
