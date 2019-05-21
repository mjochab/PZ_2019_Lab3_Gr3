insert into role(id,role_name) values(1,'ADMIN_ROLE');
insert into role(id,role_name) values(2,'WORKER_ROLE');
insert into role(id,role_name) values(3,'USER_ROLE');

insert into user(id, username, password,role_id) values (1, 'admin123', '$2a$10$YrqwJz0w08j77FujYZxHIOy4z453Eb14ncQJV.dmSjcmR9QjLdvSy',1);
insert into user(id, username, password,role_id) values (2, 'worker123', '$2a$10$RbZwTT4Eks0U2sZG7H79muRcP9v2ZgC0ovECB77Dy4Q3B9UT7lRfy.',2);
insert into user(id, username, password,role_id) values (3, 'user123', '$2a$10$NYQcIjpEcK0ZggA6XW4L3u/wiQY9sRhl/QVwqj8.0oZ64nZ/WBi3e',3);