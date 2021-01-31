insert into permission (name) values
('REGISTER'), ('REGISTER_PHARMACIST'), ('REGISTER_DERMATOLOGIST'), ('REGISTER_PHARMACY_ADMIN'), ('CREATE_EXAMINATION');

insert into authority (name) values ('ROLE_SYSTEM_ADMIN'), ('ROLE_PHARMACY_ADMIN'), ('ROLE_PATIENT'),
                                    ('ROLE_DERMATOLOGIST'), ('ROLE_PHARMACIST'), ('ROLE_SUPPLIER');

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
(6, 'pharmacyadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4),
(7, 'dermatologist1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1),
(8, 'pharmacyadmin1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4),
(9, 'pharmacyadmin2@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4),
(10, 'pharmacyadmin3@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4),
(11, 'pharmacyadmin4@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 4),
(12, 'pharmacist1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 0),
(13, 'pharmacist2@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 0),
(14, 'pharmacist3@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 0),
(15, 'pharmacist4@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 0),
(16, 'pharmacist5@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 0),
(17, 'dermatologist2@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1),
(18, 'dermatologist3@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1),
(19, 'dermatologist4@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1),
(20, 'dermatologist5@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1);

insert into user_authority (user_id, authority_id) values
(1, 5), (2, 4), (3, 3), (4, 3), (5, 1), (6, 2), (7, 4),(8, 2),(9, 2), (10, 2),
(11, 2) , (12, 5), (13, 5), (14, 5), (15, 5), (16, 5),(17, 4),(18, 4), (19, 4), (20, 4);


insert into medicament(id, name, type, contraindications,ingredients,code, shape, replacement, manufacturer, issuance, notes ) values
(1, 'Aciclovir', 'Antivirotic', 'Headache, fainting, fever', 'Macrogol 400, Disodium edetate', 'F3232' , 'Capsule' ,'G3HU95' ,'ABBOTT', 'Recipe', '/'),
(2, 'Aminophylline', 'Bronchodilator', 'Nausea, headache, irregular heartbeat, attacks', 'Magnesium-stearate, cellulose' ,'432JD' , 'Capsule' ,'G53G5' ,'ABBOTT', 'Recipe', '/'),
(3, 'Amikacin', 'Antibiotic', 'Buzzing in ears, dizziness, anemia, deafness', 'Sodium-citrate, sulfuric-acid','BGF3U8' , 'Tablet' ,'G53G3' ,'ABBOTT', 'Recipe', '/'),
(4, 'Amoxicillin', 'Antibiotic', 'Allergic reactions, rash, bleeding', 'Sucrose, sodium-citrate','F42HDO' , 'Capsule' ,'G53G35' ,'ABBOTT', 'Recipe', '/'),
(5, 'Bactrim', 'Antibiotic', 'Skin rash, fever, blisters, kidney problems', 'Povidone, magnesium-stearate','FNH3U' , 'Capsule' ,'G35G3' ,'ABBOTT', 'Recipe', '/'),
(6, 'Cefadroxil', 'Antibiotic', 'Swelling of the eyelids, face or lips, fainting', 'Cellulose,magnesium-stearate ', 'GH39OIJ' , 'Bang' ,'G35G35' ,'DELPHARM', 'Recipe', '/'),
(7, 'Doxycycline', 'Antibiotic', 'Fever, headache, deafness', 'Lactose, Azo colors' ,'GNU5TO' , 'Capsule' ,'FEWFE' ,'DELPHARM', 'Recipe', '/'),
(8, 'Erythromycin', 'Antibiotic', 'Bleeding, blisters, anemia', 'Hypromellose, Macrogol 8000','GJN59' , 'Tablet' ,'G53RG' ,'DELPHARM', 'No recipe', '/'),
(9, 'Chloramphenicol', 'Antibiotic', 'Rash, allergic reactions', 'Castor oil, white paraffin','NCEIOW' , 'Gel' ,'G53G' ,'DELPHARM', 'No recipe', '/'),
(10, 'Ibuprophen', 'Analgetic', 'Irregular heartbeat, nausea', 'Povidone, cellulose','FMNI43P0' , 'Capsule' ,'F34F3G' ,'HEMOPHARM', 'No recipe', '/'),
(11, 'Nystatin', 'Antifungal', 'Fever, attacks', 'White wax, Cetyl alcohol', 'FNMI4O' , 'Bang' ,'DE24' ,'HEMOPHARM', 'Recipe', '/'),
(12, 'Paracetamol', 'Antipiretic', 'Buzzing in ears, swelling od the face', 'Povidone, magnesium-stearate' ,'NVG3ROJ' , 'Capsule' ,'H65H6' ,'HEMOPHARM', 'No recipe', '/'),
(13, 'Panclav', 'Antibiotic', 'Skin rash, fever, blisters, kidney problems', 'Amoxicillin, Clavulanic acid','NF344F' , 'Tablet' ,'K87JK' ,'HEMOPHARM', 'Recipe', '/'),
(14, 'Roximisan', 'Antibiotic', 'Allergic reactions, rash, bleeding', 'Sucrose,magnesium-stearate ','HFU34' , 'Tablet' ,'H5RBV' ,'HEMOPHARM', 'Recipe', '/'),
(15, 'Caffetin', 'Antipiretic', 'Rash, allergic reactions', 'Povidone,dyhidrate, cellulose ','RNF34' , 'Tablet' ,'G45ERF' ,'HEMOPHARM', 'No recipe', '/');

insert into pharmacy (id, name, address, about) values
(1, 'Laurus', 'Jovana Pejcica 3', 'Creation date: 14.04.2021.'),
(2, 'Galen pharm', 'Laze Kostica 32', 'Creation date: 14.04.2018.'),
(3, 'Dr. Max', 'Svetojovanska 5', 'Creation date: 29.04.2020.'),
(4, 'Benu', 'Sarajevska 18', 'Creation date: 14.10.2018.');

insert into system_admin (id, first_name, last_name, user_id) values
(1, 'Nikolina', 'Ivankovic', 5);

insert into pharmacy_admin (id, first_name, last_name, user_id, pharmacy_id) values
(1, 'Marina', 'Ivankovic', 6, 1),
(2, 'Jovana', 'Jovanovic', 8, 1),
(3, 'Marija', 'Rikic', 9, 2),
(4, 'Pavle', 'Ivic', 10, 3),
(5, 'Anja', 'Vlaskalic', 11, 4);

insert into pharmacist (id, first_name, last_name, user_id, number, address, pharmacy_id) values
(1, 'Dusan', 'Sisarica', 1, '543443435', 'Marka Kraljevica 28', 1),
(2, 'Jovana', 'Bajic', 1, '435435435', 'Panonska 54', 1),
(3, 'Nemanja', 'Djogic', 1, '123456789', 'Marsala Tita 234', 2),
(4, 'Strahinja', 'Rodic', 1, '423424', 'Partizanska 128', 2),
(5, 'Bojana', 'Kelic', 1, '6543224', 'Temerinska 32', 3),
(6, 'Milena', 'Babic', 1, '32523334', 'Maksima Gorkog 55', 4);

insert into dermatologist (id, first_name, last_name, user_id, number, address) values
(1, 'Uros', 'Sisarica', 2, '3543', 'Novaka Pejcica 128'),
(2, 'Stefan', 'Stupar', 7, '43432432', 'Micurinova 23'),
(3, 'Srdjan', 'Markovic', 17, '432423', 'Svetislava Kasapinovica 18'),
(4, 'Momcilo', 'Stankovic', 18, '5444', 'Vojvodjanska 22'),
(5, 'Biljana', 'Jojic', 19, '38392543', 'Vuka Karadzica 44'),
(6, 'Tamara', 'Stupar', 20, '932749', 'Slavska 94');

insert into patient (id, first_name, last_name, user_id, number, address, city, country, request_status, deleted) values
(1, 'Zorana', 'Vlaskalic', 3, '43242324', 'Majevicka 8','Novi Sad','Srbija', 'CONFIRMED',false),
(2, 'Dajana', 'Erceg', 4, '3432423543', 'Svetislava Kasapinovica 4','Novi Sad','Srbija','CONFIRMED', false);

insert into patients_medicaments (patient_id, medicament_id) values
(1, 1), (1, 2), (2, 4);

insert into pharmacy_medicament(id, quantity, pharmacy_id, medicament_id) values
(1, 100, 1, 1), (2, 150, 1, 5), (3, 160, 1, 9), (4, 200, 1, 13), (5, 200, 1, 2),
(6, 200, 2, 2), (7, 150, 2, 6), (8, 160, 2, 10), (9, 200, 2, 14), (10, 200, 2, 3),
(11, 140, 3, 3), (12, 150, 3, 7), (13, 160, 3, 11), (14, 200, 3, 15), (15, 200, 3, 4),
(16, 160, 4, 4), (17, 150, 4, 8), (18, 160, 4, 12), (19, 200, 4, 1), (20, 200, 4, 5);

insert into shift(id, start_shift, end_shift, pharmacy_id, dermatologist_id) values
(1, '08:00', '14:00', 1, 1),
(2, '08:00', '14:00', 2, 2),
(3, '08:00', '14:00', 3, 3),
(4, '08:00', '14:00', 4, 4),
(5, '14:00', '22:00', 1, 5),
(6, '14:00', '18:00', 2, 6),
(7, '15:00', '20:00', 3, 1),
(8, '15:00', '20:00', 4, 2);

insert into shift_pharmacist(id, start_shift, end_shift, pharmacy_id, pharmacist_id) values
(1, '08:00', '12:00', 1, 1),
(2, '08:00', '12:00', 2, 2),
(3, '08:00', '12:00', 3, 3),
(4, '08:00', '12:00', 4, 4),
(5, '12:00', '18:00', 1, 5),
(6, '12:00', '18:00', 2, 6);

insert into dermatologist_examination(id, start_time_examination, end_time_examination,date_examination, price, dermatologist_id,pharmacy_id, examination_status ) values
(1, '08:00', '09:00', '10.03.2021.', '10', 2, 2, 0),
(2, '10:00', '11:00', '10.03.2021.', '10', 2, 2, 0),
(3, '16:00', '17:00', '10.03.2021.', '15', 2, 4, 0),
(4, '18:00', '19:00', '10.03.2021.', '15', 2, 4, 0),
(5, '16:00', '17:00', '10.03.2021.', '10', 1, 3, 0);

insert into medicament_reservation(id, date_to_pick, pharmacy_medicament_id, patient_id, medicament_reservation_status) values
(1, '2021-01-20', 7,1, 2),
(2, '2020-12-12', 10,1, 2),
(3, '2020-12-20', 11,2, 2),
(4, '2020-08-14', 8,2, 2),
(5, '2021-03-25', 16,1, 1),
(6, '2021-02-01', 11,1, 1);

insert into rating(id,patient_id, grade, pharmacy_id, dermatologist_id, pharmacist_id, medicament_id) values
(1, 1, 5, 2, null, null, null),
(2, 1, 3, null, null, null, 6),
(3, 1, 1, 2, null, null, null),
(4, 1, 1, null, null, null, 3),
(5, 2, 3, 3, null, null, null),
(6, 2, 4, null, null, null, 3),
(7, 2, 5, 2, null, null, null),
(8, 2, 3,null, null, null, 10),
(9, 1, 2, 4,null, null, null),
(10, 1, 1, null, null, null, 4),
(11, 1, 4, 3, null, null, null),
(12, 1, 3, null, null, null, 3);