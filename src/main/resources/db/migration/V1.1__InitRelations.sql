alter table envelope_pricing
add foreign key(courier_id) references courier(id);

alter table pack_pricing
add foreign key(courier_id) references courier(id);

alter table pallet_pricing
add foreign key(courier_id) references courier(id);

alter table gift_order
add foreign key(user_id) references user(id),
add foreign key(gift_id) references gift(id);

alter table user
add foreign key(role_id) references role(id),
add foreign key(adress_id) references adress(id);

alter table user_order
add foreign key(user_id) references user(id),
add foreign key(courier_id) references courier(id),
add foreign key(sender_adress_id) references sender_adress(id),
add foreign key(recipient_adress_id) references recipient_adress(id);

alter table recipient_adress
add foreign key(adress_id) references adress(id);

alter table sender_adress
add foreign key(adress_id) references adress(id);

