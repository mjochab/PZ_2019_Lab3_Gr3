alter table envelope_pricing
    add foreign key(courier_id) references courier(id);

alter table pack_pricing
    add foreign key(courier_id) references courier(id);

alter table pallet_pricing
    add foreign key(courier_id) references courier(id);

alter table gift_order
    add foreign key(user_id) references user(id);

alter table gift_order
    add foreign key(recipient_address_id) references recipient_address(id);
alter table gift_order
    add foreign key(gift_id) references gift(id);

alter table user
    add foreign key(role_id) references role(id);

alter table user
    add foreign key(address_id) references address(id);

alter table user_order
    add foreign key(user_id) references user(id);
alter table user_order
    add foreign key(courier_id) references courier(id);
alter table user_order
    add foreign key(sender_address_id) references sender_address(id);
alter table user_order
    add foreign key(recipient_address_id) references recipient_address(id);
alter table user_order
    add foreign key(parcel_id) references parcel(id);

alter table recipient_address
    add foreign key(address_id) references address(id);

alter table sender_address
    add foreign key(address_id) references address(id);

alter table opinion
    add foreign key(user_order_id) references user_order(id);
