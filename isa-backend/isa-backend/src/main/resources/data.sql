insert into permission (name) values
('REGISTER'), ('REGISTER_PHARMACIST'), ('REGISTER_DERMATOLOGIST'), ('REGISTER_PHARMACY_ADMIN'), ('CREATE_EXAMINATION');

insert into authority (name) values ('ROLE_SYSTEM_ADMIN'), ('ROLE_PHARMACY_ADMIN'), ('ROLE_PATIENT'),
                                    ('ROLE_DERMATOLOGIST'), ('ROLE_PHARMACIST');

insert into authorities_permissions (authority_id, permission_id) values
(1, 2), (1, 3), (1, 4),
(2, 2),
(3, 1), (3, 5),
(4, 5),
(5, 5);

-- password-> Admin123!!!

insert into user_entity (id, username, password, has_signed_in, last_password_reset_date, user_role) values
(1, 'pharmacist@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:11', 0),
(2, 'dermatologist@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:12', 1),
(3, 'patient1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:13', 2),
(4, 'patient2@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:14', 2),
(5, 'systemadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 3),
(6, 'pharmacyadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4);

insert into user_authority (user_id, authority_id) values
(1, 5),
(2, 4),
(3, 3),
(4, 3),
(5, 1),
(6, 2);

insert into system_admin (id, first_name, last_name, user_id) values
(1, 'Nikolina', 'Ivankovic', 5);

insert into pharmacy_admin (id, first_name, last_name, user_id) values
(1, 'Marina', 'Ivankovic', 6);

insert into pharmacist (id, first_name, last_name, user_id, number, address) values
(1, 'Dusan', 'Sisarica', 1, '123456789', 'Marka Kraljevica 28');

insert into dermatologist (id, first_name, last_name, user_id, number, address) values
(1, 'Uros', 'Sisarica', 2, '3543', 'Novaka Pejcica 128');

insert into patient (id, first_name, last_name, user_id, number, address, city, country, request_status, deleted) values
(1, 'Zorana', 'Vlaskalic', 3, '43242324', 'Majevicka 8','Novi Sad','Srbija', 'CONFIRMED',false),
(2, 'Dajana', 'Erceg', 4, '3432423543', 'Svetislava Kasapinovica 4','Novi Sad','Srbija','CONFIRMED', false);
