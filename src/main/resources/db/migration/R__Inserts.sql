insert into role(id,role_name) values(1,'ADMIN_ROLE');
insert into role(id,role_name) values(2,'WORKER_ROLE');
insert into role(id,role_name) values(3,'USER_ROLE');

insert into user(id, username, password,role_id) values (1, 'admin', '$2a$10$dJsK9iGgLlUviYAddpIEG.erXOJMS4fTUVn9UsbzOtsjmMm0Hzcti',1);
insert into user(id, username, password,role_id) values (2, 'worker', '$2a$10$v3SxKoQl2bnnr4wxl6wLB.w8Q8JhT1qRHf7oEXu3uiLiR216oAbL2',2);
insert into user(id, username, password,role_id) values (3, 'user', 'user',3);