
insert into role(id, role_name)
values (1, 'ADMIN_ROLE');
insert into role(id, role_name)
values (2, 'WORKER_ROLE');
insert into role(id, role_name)
values (3, 'USER_ROLE');

insert into user(id, username, password, role_id)
values (1, 'admin123', '$2a$10$YrqwJz0w08j77FujYZxHIOy4z453Eb14ncQJV.dmSjcmR9QjLdvSy', 1);
insert into user(id, username, password, role_id)
values (2, 'worker123', '$2a$10$rmz5X1sWRuHCEPlSpjm7UeLtr7A/ej0c48x9D7uPWmrbGhbSxhFgW', 2);
insert into user(id, username, password, role_id)
values (3, 'user123', '$2a$10$NYQcIjpEcK0ZggA6XW4L3u/wiQY9sRhl/QVwqj8.0oZ64nZ/WBi3e', 3);
insert into about(id, content)
values (1,
        'Serwis nadawania paczek to nowoczesna platforma wysyłkowa umożliwiająca wysyłkę paczek zarówno na terenie całego kraju jak i za granicę. Wyróżnia nas szeroki wachlarz usług oraz dbałość o ich najwyższą jakość i niską cenę. Poprzez współpracę z najlepszymi firmami kurierskimi takimi jak UPS, DPD, Geis, InPost, FedEx, Paczkomaty, DHL zawsze zapewniamy naszym klientom najkorzystniejsze warunki. Odpowiadając na nieustannie rozwijający się handel międzynarodowy, ciągle poszerzamy naszą ofertę o możliwość wysyłki paczek nawet w najbardziej odległe zakątki świata. Nasi kurierzy codziennie przemierzają tysiące kilometrów aby na czas dostarczyć nawet najbardziej niestandardowe przesyłki. Oprócz platformy wysyłkowej serwis paczek to również ponad 150 stacjonarnych placówek w całej Polsce, które każdego dnia obsługują tysiące zadowolonych klientów.');