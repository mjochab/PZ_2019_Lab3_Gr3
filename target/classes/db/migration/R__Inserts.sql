insert into role(id,role_name) values(1,'ADMIN_ROLE');
insert into role(id,role_name) values(2,'WORKER_ROLE');
insert into role(id,role_name) values(3,'USER_ROLE');

insert into user(id, username, password,role_id) values (1, 'admin', 'admin',1);
insert into user(id, username, password,role_id) values (2, 'worker', 'worker',2);
insert into user(id, username, password,role_id) values (3, 'user', 'user',3);