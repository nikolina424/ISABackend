insert into permission (name) values
('REGISTER'), ('REGISTER_PHARMACIST'), ('REGISTER_DERMATOLOGIST'), ('REGISTER_PHARMACY_ADMIN'), ('CREATE_EXAMINATION');

insert into authority (name) values ('ROLE_SYSTEM_ADMIN'), ('ROLE_PHARMACY_ADMIN'), ('ROLE_PATIENT'),
                                    ('ROLE_DERMATOLOGIST'), ('ROLE_PHARMACIST'), ('ROLE_SUPPLIER');

insert into authorities_permissions (authority_id, permission_id) values
(1, 2), (1, 3), (1, 4),
(2, 2),
(3, 1), (3, 5),
(4, 5),
(5, 5),
(6, 1);


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
(20, 'dermatologist5@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1),
(21, 'supplier1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 5),
(22, 'supplier2@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 5),
(23, 'supplier3@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 5),
(24, 'supplier4@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 5);

insert into user_authority (user_id, authority_id) values
(1, 5), (2, 4), (3, 3), (4, 3), (5, 1), (6, 2), (7, 4),(8, 2),(9, 2), (10, 2),
(11, 2) , (12, 5), (13, 5), (14, 5), (15, 5), (16, 5),(17, 4),(18, 4), (19, 4), (20, 4), (21, 6),(22, 6),(23, 6),(24, 6);


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

insert into pricelist(id, active, from_date, to_date, pharmacy_id ) values
(1, false, '2020-10-01', '2020-11-01', 1 ),
(2, false, '2020-11-01', '2020-12-01', 1 ),
(3, true, '2020-12-01', '2021-03-01', 1 ),
(4, false, '2020-10-01', '2020-11-01', 2 ),
(5, false, '2020-11-01', '2020-12-01', 2 ),
(6, true, '2020-12-01', '2021-03-01', 2 ),
(7, false, '2020-10-01', '2020-11-01', 3 ),
(8, false, '2020-11-01', '2020-12-01', 3 ),
(9, false, '2020-12-01', '2021-01-01', 3 ),
(10, true, '2021-01-01', '2021-03-01', 3 ),
(11, true, '2021-01-01', '2021-03-01', 4 );

insert into pharmacist (id, first_name, last_name, user_id, number, address, pharmacy_id,pricelist_id, price) values
(1, 'Dusan', 'Sisarica', 1, '543443435', 'Marka Kraljevica 28', 1, 3, 10),
(2, 'Jovana', 'Bajic', 12, '435435435', 'Panonska 54', 2, 6, 15),
(3, 'Nemanja', 'Djogic', 13, '123456789', 'Marsala Tita 234', 3, 10, 13),
(4, 'Strahinja', 'Rodic', 14, '423424', 'Partizanska 128', 4, 11, 17),
(5, 'Bojana', 'Kelic', 15, '6543224', 'Temerinska 32', 1, 3, 15),
(6, 'Milena', 'Babic', 16, '32523334', 'Maksima Gorkog 55', 2, 6, 20);

insert into dermatologist (id, first_name, last_name, user_id, number, address) values
(1, 'Uros', 'Sisarica', 2, '3543', 'Novaka Pejcica 128'),
(2, 'Stefan', 'Stupar', 7, '43432432', 'Micurinova 23'),
(3, 'Srdjan', 'Markovic', 17, '432423', 'Svetislava Kasapinovica 18'),
(4, 'Momcilo', 'Stankovic', 18, '5444', 'Vojvodjanska 22'),
(5, 'Biljana', 'Jojic', 19, '38392543', 'Vuka Karadzica 44'),
(6, 'Tamara', 'Stupar', 20, '932749', 'Slavska 94');

insert into supplier (id, first_name, last_name, user_id, number, address) values
(1, 'Vladimir', 'Sec', 21, '5433443435', 'Marka Kraljevica 28'),
(2, 'Katarina', 'Maksimovic', 22, '43243232', 'Panonska 54'),
(3, 'Srdjan', 'Jovanovic', 23, '123453216789', 'Marsala Tita 234'),
(4, 'Kristina', 'Rodic', 24, '423422324', 'Partizanska 128');

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

insert into dermatologist_examination(id, start_time_examination, end_time_examination,date_examination, price, dermatologist_id,pharmacy_id, examination_status, patient_id ) values
(1, '08:00', '09:00', '2021-01-20', '10', 2, 2, 0, null),
(2, '10:00', '11:00', '2021-01-20', '10', 2, 2, 0, null),
(3, '16:00', '17:00', '2021-01-20', '15', 2, 4, 0, null),
(4, '18:00', '19:00', '2021-01-20', '15', 2, 4, 0, null),
(5, '16:00', '17:00', '2021-01-20', '10', 1, 3, 0, null),
(6, '08:00', '09:00', '2021-02-20', '10', 2, 2, 1, 1),
(7, '10:00', '11:00', '2021-02-20', '10', 2, 2, 1, 1),
(8, '16:00', '17:00', '2020-02-20', '15', 2, 4, 2, 1),
(9, '18:00', '19:00', '2020-02-20', '15', 2, 4, 2, 1),
(10, '16:00', '17:00', '2020-02-20', '10', 1, 3, 2, 1);

insert into medicament_reservation(id, date_to_pick, pharmacy_medicament_id, patient_id, medicament_reservation_status) values
(1, '2021-01-20', 7,1, 1),
(2, '2020-12-12', 10,1, 1),
(3, '2020-12-20', 11,2, 1),
(4, '2020-08-14', 8,2, 1),
(5, '2021-03-25', 16,1, 0),
(6, '2021-02-01', 11,1, 0);

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



insert into price_medicament(id, price, pharmacy_medicament_id, pricelist_id) values
(1, 10, 1, 1),(2, 11, 2, 1),(3, 9, 3, 1),(4, 15, 4, 1),(5, 20, 5, 1),
(6, 25, 1, 2),(7, 25, 2, 2),(8, 13, 3, 2),(9, 10, 4, 2),(10, 12, 5, 2),
(11, 15, 1, 3),(12, 32, 2, 3),(13, 40, 3, 3),(14, 43, 4, 3),(15, 33, 5, 3),
(16, 22, 6, 4),(17, 33, 7, 4),(18, 23, 8, 4),(19, 25, 9, 4),(20, 34, 10, 4),
(21, 32, 6, 5),(22, 33, 7, 5),(23, 14, 8, 5),(24, 32, 9, 5),(25, 33, 10, 5),
(26, 22, 6, 6),(27, 34, 7, 6),(28, 23, 8, 6),(29, 25, 9, 6),(30, 19, 10, 6),
(31, 43, 11, 7),(32, 33, 12, 7),(33, 23, 13, 7),(34, 27, 14, 7),(35, 14, 15, 7),
(36, 19, 11, 8),(37, 32, 12, 8),(38, 29, 13, 8),(39, 39, 14, 8),(40, 13, 15, 8),
(41, 43, 11, 9),(42, 31, 12, 9),(43, 24, 13, 9),(44, 23, 14, 9),(45, 15, 15, 9),
(46, 34, 11, 10),(47, 33, 12, 10),(48, 22, 13, 10),(49, 49, 14, 10),(50, 39, 15, 10);

insert into complaint(id, text, pharmacy_id, dermatologist_id, pharmacist_id, patient_id, answered) values
(1, 'Complaint on dermatologist', 1, 1, null, 1, false),
(2, 'Complaint on dermatologist', 1, 1, null, 2, false),
(3, 'Complaint on pharmacist', 1, null, 1, 1, false),
(4, 'Complaint on pharmacist', 2, null, 2, 2, false),
(5, 'Complaint on pharmacy', 1, null, null, 2, false),
(6, 'Complaint on pharmacy', 2, null, null, 1, false),
(7, 'Complaint on pharmacy', 1, null, null, 1, false),
(8, 'Complaint on pharmacy', 3, null, null, 2, false),
(9, 'Complaint on pharmacy', 4, null, null, 2, false),
(10, 'Complaint on pharmacy', 1, null, null, 1, false);

insert into pharmacist_examination(id, start_time_examination, end_time_examination, date_examination, shift_pharmacist_id, patient_id, examination_status, price)values
(1, '08:00', '09:00','2021-03-10', 1,1,1, 43),(2, '11:00', '12:00','2021-03-11',  2,1,1, 22),(3,  '09:00', '10:00','2021-03-12', 3,1, 1, 32),
(4, '09:00', '10:00','2021-03-10', 1,2,1, 21),(5, '15:00', '16:00','2021-03-01', 5,2, 1, 54),(6,  '14:00', '15:00', '2021-10-18',6,2, 1, 23),
(7, '08:00', '09:00','2020-03-10', 1,1,2, 21),(8, '11:00', '12:00','2020-03-11',  2,1,2, 19),(9,  '09:00', '10:00','2020-03-12', 3,1, 2, 33),
(10, '09:00', '10:00','2020-03-10', 1,2,2, 55),(11, '15:00', '16:00','2020-03-01', 5,2,2, 15),(12,  '14:00', '15:00', '2020-10-18',6,2, 2, 22);

insert into promotion(id, expire_date, active, pharmacy_id, description) values
(1, '2021-03-10', true, 1, 'Prva promocija'),
(2, '2021-03-10', true, 1, 'Druga promocija'),
(3, '2021-03-10', true, 2, 'Treca promocija'),
(4, '2021-03-10', true, 2, 'Cetvrta promocija'),
(5, '2021-03-10', true, 3, 'Peta promocija'),
(6, '2021-03-10', true, 4, 'Sesta promocija');

insert into pharmacy_promotions_patients(pharmacy_id, patient_id) values
(1, 1), (2, 1), (3, 1);