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
(7, 'dermatologist1@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', false, '2020-01-01 10:10:15', 1);

insert into user_authority (user_id, authority_id) values
(1, 5),
(2, 4),
(3, 3),
(4, 3),
(5, 1),
(6, 2);

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
(1, 'Apoteka Jankovic', 'Jovana Pejcica 3', 'This pharmacy was opened 13.02.2008. It is the first pharmacy in Novi Sad');

insert into system_admin (id, first_name, last_name, user_id) values
(1, 'Nikolina', 'Ivankovic', 5);

insert into pharmacy_admin (id, first_name, last_name, user_id, pharmacy_id) values
(1, 'Marina', 'Ivankovic', 6, 1);

insert into pharmacist (id, first_name, last_name, user_id, number, address, pharmacy_id) values
(1, 'Dusan', 'Sisarica', 1, '123456789', 'Marka Kraljevica 28', 1);

insert into dermatologist (id, first_name, last_name, user_id, number, address) values
(1, 'Uros', 'Sisarica', 2, '3543', 'Novaka Pejcica 128'),
(2, 'Stefan', 'Stupar', 7, '43432432', 'Micurinova 23');

insert into patient (id, first_name, last_name, user_id, number, address, city, country, request_status, deleted) values
(1, 'Zorana', 'Vlaskalic', 3, '43242324', 'Majevicka 8','Novi Sad','Srbija', 'CONFIRMED',false),
(2, 'Dajana', 'Erceg', 4, '3432423543', 'Svetislava Kasapinovica 4','Novi Sad','Srbija','CONFIRMED', false);

insert into patients_medicaments (patient_id, medicament_id) values
(1, 1), (1, 2), (2, 4);

insert into pharmacy_medicament(id, quantity, pharmacy_id, medicament_id) values
(1, 100, 1, 1), (2, 150, 1, 2), (3, 160, 1, 3), (4, 200, 1, 4);

insert into shift(id, start_shift, end_shift, pharmacy_id, dermatologist_id) values
(1, '08:00', '12:00', 1, 1);

insert into shift_pharmacist(id, start_shift, end_shift, pharmacy_id, pharmacist_id) values
(1, '08:00', '12:00', 1, 1);