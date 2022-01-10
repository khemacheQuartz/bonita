insert into role (name) values ('ROLE_ADM');
insert into role (name) values ('ROLE_CHF');
insert into role (name) values ('ROLE_USR');

insert into user (login, password, email, role_id) values ('admin', 'admin', 'admin@yopmail.com', 1);
insert into user (login, password, email, role_id) values ('chef', 'chef', 'chef@yopmail.com', 2);
insert into user (login, password, email, role_id) values ('user', 'user', 'chef@yopmail.com', 3);