create table `role` (
id bigint NOT NULL AUTO_INCREMENT,
role_name varchar(50),
PRIMARY KEY(id));

create table `user` (
id bigint NOT NULl AUTO_INCREMENT,
username varchar(30),
password varchar(25),
role_id bigint,
Primary Key(id));

create table courier (
id bigint not null auto_increment,
name varchar(255),
Primary Key(id));

create table envelope_pricing (
id bigint NOT NULl AUTO_INCREMENT,
courier_id bigint,
up_to_1 float,
Primary Key(id));

create table gift (
id bigint NOT NULl AUTO_INCREMENT,
name varchar(255),
premium_points int,
Primary Key(id));

create table gift_order (
id bigint NOT NULl AUTO_INCREMENT,
user_id bigint,
gift_id bigint,
date DATETIME,
Primary Key(id));

create table pack_pricing (
id bigint NOT NULl AUTO_INCREMENT,
courier_id bigint,
up_to_1 float,
up_to_2 float,
up_to_5 float,
up_to_10 float,
up_to_15 float,
up_to_20 float,
up_to_30 float,
Primary Key(id));

create table pallet_pricing (
id bigint NOT NULl AUTO_INCREMENT,
courier_id bigint,
up_to_300 float,
up_to_500 float,
up_to_800 float,
up_to_1000 float,
Primary Key(id));

create table user_order(
id bigint NOT NULl AUTO_INCREMENT,
price float,
date DATETIME,
user_id bigint,
courier_id bigint,
status varchar(40),
Primary Key(id));

CREATE TABLE adress (
  id bigint NOT NULl AUTO_INCREMENT,
  name varchar(30),
  surname varchar(30),
  city varchar(30),
  street varchar(30),
  houseNumber int(11),
  zipCode int(11),
  telephoneNumber int(11),
  email varchar(30),
  PRIMARY KEY(id));


CREATE TABLE recipient_adress (
  id bigint NOT NULl AUTO_INCREMENT,
  adress_id int(11),
  PRIMARY KEY(id));

CREATE TABLE sender_adress (
  id bigint NOT NULl AUTO_INCREMENT,
  adress_id int(11),
  PRIMARY KEY(id));