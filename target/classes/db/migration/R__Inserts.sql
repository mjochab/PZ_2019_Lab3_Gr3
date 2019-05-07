insert into role(id,role_name) values(1,'ADMIN_ROLE');
insert into role(id,role_name) values(2,'WORKER_ROLE');
insert into role(id,role_name) values(3,'USER_ROLE');

insert into user(id, username, password,role_id) values (1, 'admin', '$2a$10$yHxQpx7LOjWQWdN/Pu1h.uH7.9RoPiJB3nhR/zkQsr0Eiw.5hShEi',1);
insert into user(id, username, password,role_id) values (2, 'worker', '$2a$10$U043P27lqXXsn9p6l0jRtuxlthVvV/u5HweK2vN8veJpPH.bSX7k.',2);
insert into user(id, username, password,role_id) values (3, 'user', '$2a$10$9ZoqjxVrLLAiaYDtga1T/uicHAx5umJaxWt/H3G3NDPQQFHrJz/Fe',3);