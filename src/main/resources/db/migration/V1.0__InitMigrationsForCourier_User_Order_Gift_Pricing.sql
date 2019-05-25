create table `role` (
id bigint NOT NULL AUTO_INCREMENT,
role_name varchar(50),
PRIMARY KEY(id));

create table `user` (
id bigint NOT NULl AUTO_INCREMENT,
username varchar(30),
password varchar(250),
account_balance double not null DEFAULT 0,
role_id bigint,
premium_points_balance int DEFAULT 500,
adress_id bigint,
Primary Key(id));

create table courier (
id bigint not null auto_increment,
name varchar(255),
is_blocked BOOLEAN not null default 0,
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
id bigint NOT NULL AUTO_INCREMENT,
price float,
date DATETIME,
user_id bigint,
courier_id bigint,
parcel_id bigint,
status varchar(40),
sender_adress_id bigint,
recipient_adress_id bigint,
Primary Key(id));

create table adress (
id bigint NOT NULL AUTO_INCREMENT,
name varchar(50),
surname varchar(50),
city varchar(50),
street varchar(50),
house_number int,
zip_code varchar(25),
telephone_number bigint,
email varchar(50),
PRIMARY KEY(id)
);

create table recipient_adress(
id bigint NOT NULL AUTO_INCREMENT,
adress_id bigint,
PRIMARY KEY(id)
);

create table sender_adress(
id bigint NOT NULL AUTO_INCREMENT,
adress_id bigint,
PRIMARY KEY(id)
);

create table parcel(
id bigint NOT NULL AUTO_INCREMENT,
length int,
width int,
height int,
type varchar(50),
weight int,
PRIMARY KEY(id)
);

create table opinion(
id bigint NOT NULL AUTO_INCREMENT,
user_order_id bigint,
date DATETIME,
content varchar(150),
rating int,
PRIMARY KEY(id)
);

create table about(
id int NOT NULL AUTO_INCREMENT,
content varchar(1500),
PRIMARY KEY(id)
);

create table coupon(
id bigint NOT NULL AUTO_INCREMENT,
name varchar(150),
discount int,
PRIMARY KEY(id)
);