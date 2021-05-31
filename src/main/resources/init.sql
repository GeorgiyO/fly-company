
drop database if exists flycompany;
create database flycompany;

use flycompany;

create table worker
(
    id_worker int auto_increment not null unique,
    fio_worker varchar(45) not null,
    tel_worker varchar(10),
    primary key (id_worker)
) engine = InnoDB
  character set = utf8;

create table live
(
    id_live int auto_increment not null unique,
    name_live varchar(30) not null,
    address_live varchar(45) not null,
    primary key (id_live)
) engine = InnoDB
  character set = utf8;

create table arrive
(
    id_arrive int auto_increment not null unique,
    name_arrive varchar(30) not null,
    address_arrive varchar(45) not null,
    primary key (id_arrive)
) engine = InnoDB
  character set = utf8;

create table cash
(
    id_cash int auto_increment not null unique,
    number_cash varchar(45) not null,
    primary key (id_cash)
) engine = InnoDB
  character set = utf8;

create table fly
(
    id_fly int auto_increment not null unique,
    number_fly varchar(10) not null,
    date_fly datetime not null,
    id_arrive int not null,
    id_live int not null,
    primary key (id_fly),
    foreign key (id_arrive) references arrive (id_arrive)
        on update cascade
        on delete cascade,
    foreign key (id_live) references live (id_live)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;

create table plan
(
    id_plan int auto_increment not null unique,
    number_plan varchar(10) not null,
    kind_plan varchar(10) not null,
    id_worker int not null,
    id_fly int not null,
    primary key (id_plan),
    foreign key (id_worker) references worker (id_worker)
        on update cascade
        on delete cascade,
    foreign key (id_fly) references fly (id_fly)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;

create table pilot
(
    id_pilot int auto_increment not null unique,
    fio_pilot varchar(45) not null,
    tel_pilot varchar(10),
    id_plan int not null,
    primary key (id_pilot),
    foreign key (id_plan) references plan (id_plan)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;

create table class
(
    id_class int auto_increment not null unique,
    name_class varchar(10) not null,
    id_plan int not null,
    primary key (id_class),
    foreign key (id_plan) references plan (id_plan)
        on update cascade
        on delete cascade

) engine = InnoDB
  character set = utf8;

create table place
(
    id_place int auto_increment not null unique,
    number_place varchar(4) not null,
    id_class int not null,
    primary key (id_place),
    foreign key (id_class) references class (id_class)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;

create table ticket
(
    id_ticket int auto_increment not null unique,
    number_ticket varchar(10) not null,
    price_ticket decimal (5,2) not null,
    id_plan int not null,
    id_cash int not null,
    primary key (id_ticket),
    foreign key (id_plan) references plan (id_plan)
        on update cascade
        on delete cascade,
    foreign key (id_cash) references cash (id_cash)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;

create table client
(
    id_client int auto_increment not null unique,
    fio_client varchar(45) not null,
    password_client varchar(45) not null,
    id_ticket int not null,
    id_cash int not null,
    primary key (id_client),
    foreign key (id_ticket) references ticket (id_ticket)
        on update cascade
        on delete cascade,
    foreign key (id_cash) references cash (id_cash)
        on update cascade
        on delete cascade
) engine = InnoDB
  character set = utf8;
