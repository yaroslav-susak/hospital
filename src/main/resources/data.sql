use hospital;

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'AUTHOR');
INSERT INTO role (id, name) VALUES (3, 'DOCTOR');
INSERT INTO role (id, name) VALUES (4, 'PATIENT');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
1,
'2019-09-17 15:58:37.612000000',
'susak.slava@gmail.com',
'Ярослав',
'Сусак',
'$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy',
'yaroslav_susak.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (1,1);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
2,
'2019-09-17 15:58:37.612000000',
'melnyk.rostyslav@gmail.com',
'Ростислав',
'Мельник',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'rostyslav_melnyk.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (2,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
3,
'2019-09-17 15:58:37.612000000',
'oleg.vijga@gmail.com',
'Олег',
'Віжга',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'oleg_vijga.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (3,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
4,
'2019-09-17 15:58:37.612000000',
'ivan.tymchyshin@gmail.com',
'Іван',
'Тимчишин',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ivan_tymchyshin.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (4,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
5,
'2019-09-17 15:58:37.612000000',
'ivan.yurkevich@gmail.com',
'Іван',
'Юркевич',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ivan_yurkevich.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (5,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
6,
'2019-09-17 15:58:37.612000000',
'ivanka.babjuk@gmail.com',
'Іванка',
'Бабюк',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ivanka_babjuk.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (6,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
7,
'2019-09-17 15:58:37.612000000',
'taras.savchuk@gmail.com',
'Тарас',
'Савчук',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'taras_savchuk.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (7,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
8,
'2019-09-17 15:58:37.612000000',
'ivan.savkiv@gmail.com',
'Іван',
'Савків',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ivan_savkik.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (8,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
9,
'2019-09-17 15:58:37.612000000',
'roman.burak@gmai.com',
'Роман',
'Бурак',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'roman_burak.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (9,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
10,
'2019-09-17 15:58:37.612000000',
'marjana.brazda@gmai.com',
'Маряна',
'Бразда',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'marjana_brazda.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (10,4);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
11,
'2019-09-17 15:58:37.612000000',
'ivan.dutchak@gmai.com',
'Іван',
'Дутчак',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ivan_dutchak.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (11,4);

INSERT INTO department (id, description, name) VALUES (1, 'Алергологія', 'Алергологія');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
12,
'2019-09-17 15:58:37.612000000',
'oxana.pastuh@gmai.com',
'Оксана',
'Пастух',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'oxana_pastuh.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (12,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
1,
'Василівна',
'Оксана',
'oxana_pastuh.jpg',
'Вища',
'Алерголог',
'Пастух',
1,
12);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
13,
'2019-09-17 15:58:37.612000000',
'iryna.parandiy@gmai.com',
'Ірина',
'Парадій',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'iryna_parandiy.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (13,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
2,
'Богданівна',
'Ірина',
'iryna_parandiy.jpg',
'Перша',
'Алерголог',
'Парадій',
1,
13);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
14,
'2019-09-17 15:58:37.612000000',
'lida.lupulak@gmai.com',
'Ліда',
'Лупуляк',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'lida_lupulak.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (14,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
3,
'Василівна',
'Ліда',
'lida_lupulak.jpg',
'Вища',
'Алерголог',
'Лупуляк',
1,
14);

INSERT INTO department (id, description, name) VALUES (2, 'Анестезіологія', 'Анестезіологія');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
15,
'2019-09-17 15:58:37.612000000',
'oleg.kurtash@gmai.com',
'Олег',
'Курташ',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'oleg_kurtash.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (15,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
4,
'Олександрович',
'Олег',
'oleg_kurtash.jpg',
'Вища',
'Анестезіолог',
'Курташ',
2,
15);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
16,
'2019-09-17 15:58:37.612000000',
'volodymyr.valchyshin@gmai.com',
'Володимир',
'Вальчишин',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'volodymyr_valchyshin.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (16,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
5,
'Петрович',
'Володимир',
'volodymyr_valchyshin.jpg',
'Вища',
'Анестезіолог',
'Вальчишин',
2,
16);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
17,
'2019-09-17 15:58:37.612000000',
'yaroslav.demkiv@gmai.com',
'Ярослав',
'Демків',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'yaroslav_demkiv.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (17,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
6,
'Михайлович',
'Ярослав',
'yaroslav_demkiv.jpg',
'Вища',
'Анестезіолог',
'Демків',
2,
17);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
18,
'2019-09-17 15:58:37.612000000',
'roman.ivanochko@gmai.com',
'Роман',
'Іваночко',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'roman_ivanochko.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (18,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
7,
'Васильович',
'Роман',
'roman_ivanochko.jpg',
'Вища',
'Анестезіолог',
'Іваночко',
2,
18);

INSERT INTO department (id, description, name) VALUES (3, 'Неврологія', 'Неврологія');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
19,
'2019-09-17 15:58:37.612000000',
'yaroslav.kryshtafovich@gmai.com',
'Ярослав',
'Криштафович',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'yaroslav_kryshtafovich.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (19,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
8,
'Львович',
'Ярослав',
'yaroslav_kryshtafovich.jpg',
'Вища',
'Невролог',
'Криштафович',
3,
19);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
20,
'2019-09-17 15:58:37.612000000',
'taras.velychko@gmai.com',
'Тарас',
'Величко',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'taras_velychko.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (20,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
9,
'Михайлович',
'Тарас',
'taras_velychko.jpg',
'Перша',
'Педіатр',
'Величко',
3,
20);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
21,
'2019-09-17 15:58:37.612000000',
'svitlana.galanjiy@gmai.com',
'Світлана',
'Галанджій',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'svitlana_galanjiy.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (21,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
10,
'Василівна',
'Світлана',
'svitlana_galanjiy.jpg',
'Вища',
'Невролог',
'Галанджій',
3,
21);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
22,
'2019-09-17 15:58:37.612000000',
'ludmyla.delva@gmai.com',
'Людмила',
'Дельва',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ludmyla_delva.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (22,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
11,
'Володимирівна',
'Людмила',
'ludmyla_delva.jpg',
'Вища',
'Невролог',
'Дельва',
3,
22);

INSERT INTO department (id, description, name) VALUES (4, 'Онкогематологія', 'Онкогематологія');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
23,
'2019-09-17 15:58:37.612000000',
'alla.ivanenko@gmai.com',
'Алла',
'Іваненко',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'alla_ivanenko.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (23,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
12,
'Леонідівна',
'Алла',
'alla_ivanenko.jpg',
'Вища',
'Гематолог',
'Іваненко',
4,
23);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
24,
'2019-09-17 15:58:37.612000000',
'ludmyla.melnychuk@gmai.com',
'Людмила',
'Мельничук',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'ludmyla.melnychuk.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (24,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
13,
'Василівна',
'Людмила',
'ludmyla_melnychuk.jpg',
'Перша',
'Гематолог',
'Мельничук',
4,
24);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
25,
'2019-09-17 15:58:37.612000000',
'yurij.tomashchuk@gmai.com',
'Юрій',
'Томащук',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'yurij_tomashchuk.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (25,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
14,
'Степанович',
'Юрій',
'yurij_tomashchuk.jpg',
'Друга',
'Гематолог',
'Томащук',
4,
25);

INSERT INTO department (id, description, name) VALUES (5, 'Ендикринологія', 'Ендикринологія');

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
26,
'2019-09-17 15:58:37.612000000',
'natalija.chorna@gmai.com',
'Наталія',
'Чорна',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'natalija_chorna.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (26,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
15,
'Володимирівна',
'Наталія',
'natalija_chorna.jpg',
'Вища',
'Ендокринолог',
'Чорна',
5,
26);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
27,
'2019-09-17 15:58:37.612000000',
'nina.alekseeva@gmai.com',
'Ніна',
'Алексеєва',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'nina_alekseeva.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (27,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
16,
'Сергіївна',
'Ніна',
'nina_alekseeva.jpg',
'Вища',
'Ендокринолог',
'Алексеєва',
5,
27);

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES
(
28,
'2019-09-17 15:58:37.612000000',
'lilia.zelinska@gmai.com',
'Лілія',
'Зелінська',
'$2a$10$TEKN31igInElgXgeRaO3neHEdtzvc40ZLU2JRd/DLeKf29btVaEnC',
'lilia_zelinska.jpg',
false
);

INSERT INTO user_role (user_id, role_id) VALUES (28,3);

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (
17,
'Михайлівна',
'Лілія',
'lilia_zelinska.jpg',
'Вища',
'Ендокринолог',
'Зелінська',
5,
28);

-- INSERT INTO question(question,answer) VALUES('Як саме записатись на прийом?','Подзвоніть або заповніть заявку');
-- INSERT INTO question(question,answer) VALUES('Мій сеанс відмінили. Що робити?','Зверніться за телефоном, щоб вирішити дане питання, можливі збої, або зміни в розпорядку лікарів');
--
-- INSERT INTO disease(name, department_id) VALUES ('Гостра респіраторна вірусна інфекція (ускладнені форми)', 3);
-- INSERT INTO disease(name, department_id) VALUES ('Гострий бронхіт', 3);
-- INSERT INTO disease(name, department_id) VALUES ('Гіпертермія неясного ґенезу', 3);
--
-- INSERT INTO news(id, author_id, date, name, text, type) VALUES (1,2,'2019-10-11 23:00:00','Бронхіальна астма',
-- 'Астма – це хронічна хвороба, яка найчастіше проявляється періодичними нападами задишки та свистячими хрипами. Частота та ступінь важкості цих симптомів можуть бути різними. Наприклад, інтервал між нападами може коливатися від однієї години до одного дня. Як правило, напади відбуваються під час фізичної активності або вночі. Бронхіальною астмою можуть хворіти люди усіх вікових груп, але найчастіше це діти та молодь. Такі тенденції є однаковими як в Україні, так і в інших країнах світу.','NEWS');
--
-- INSERT INTO news(id, author_id, date, name, text, type) VALUES (2,2,'2019-10-11 12:00:00','Бронхіальна астма',
-- 'Астма – це хронічна хвороба, яка найчастіше проявляється періодичними нападами задишки та свистячими хрипами. Частота та ступінь важкості цих симптомів можуть бути різними. Наприклад, інтервал між нападами може коливатися від однієї години до одного дня. Як правило, напади відбуваються під час фізичної активності або вночі. Бронхіальною астмою можуть хворіти люди усіх вікових груп, але найчастіше це діти та молодь. Такі тенденції є однаковими як в Україні, так і в інших країнах світу.','NEWS');
--
-- INSERT INTO news(id, author_id, date, name, text, type) VALUES (3,2,'2019-10-11 23:00:00','Бронхіальна астма',
-- 'Астма – це хронічна хвороба, яка найчастіше проявляється періодичними нападами задишки та свистячими хрипами. Частота та ступінь важкості цих симптомів можуть бути різними. Наприклад, інтервал між нападами може коливатися від однієї години до одного дня. Як правило, напади відбуваються під час фізичної активності або вночі. Бронхіальною астмою можуть хворіти люди усіх вікових груп, але найчастіше це діти та молодь. Такі тенденції є однаковими як в Україні, так і в інших країнах світу.','ARTICLE');
--
-- INSERT INTO news(id, author_id, date, name, text, type) VALUES (4,2,'2019-10-11 15:00:00','Бронхіальна астма',
-- 'Астма – це хронічна хвороба, яка найчастіше проявляється періодичними нападами задишки та свистячими хрипами. Частота та ступінь важкості цих симптомів можуть бути різними. Наприклад, інтервал між нападами може коливатися від однієї години до одного дня. Як правило, напади відбуваються під час фізичної активності або вночі. Бронхіальною астмою можуть хворіти люди усіх вікових груп, але найчастіше це діти та молодь. Такі тенденції є однаковими як в Україні, так і в інших країнах світу.','ARTICLE');

INSERT INTO hibernate_sequence (next_val) VALUE (100);