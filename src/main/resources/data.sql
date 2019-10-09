use hospital;

INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES (1, '2019-09-17 15:58:37.612000000', 'susak.slava@gmail.com', 'Ярослав', 'Сусак', '$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy',null, false);
INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES (2, '2019-09-17 15:58:37.612000000', 'susak.yaroslav@comp-sc.if.ua', 'Ярослав', 'Сусак', '$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy',null, false);
INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES (3, '2019-09-17 15:58:37.612000000', 'symx2019new@gmail.com', 'Ярослав', 'Сусак', '$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy',null, false);
INSERT INTO user (id, date_of_registration, email, first_name, last_name, password, photo, banned) VALUES (4, '2019-09-17 15:58:37.612000000', 'symx2020new@gmail.com', 'Ярослав', 'Лупуляк', '$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy',null, false);

INSERT INTO department (id, description, name) VALUES (1, 'опис', 'Неврологія');
INSERT INTO department (id, description, name) VALUES (2, 'опис', 'Анестезіологія');
INSERT INTO department (id, description, name) VALUES (3, 'опис', 'Педіатрія');

INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (1, 'Васильович', 'Ярослав', 'kz1pqa.jpg', 'Вища', 'Нейрохірург', 'Лупуляк', 1, 4);
INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (2, 'Львович', 'Ярослав', 'h56caz.jpg', 'Вища', 'Невролог', 'Криштафович', 1, null );
INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (3, 'Васильович', 'Тарас', 'ce1kz8.jpg', 'Перша', 'Анестезіолог', 'Мельник', 2, null );
INSERT INTO doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id, user_id) VALUES (4, 'Михайлович', 'Тарас', 'c34tqa.jpg', 'Перша', 'Педіатр', 'Величко', 3, null );

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'AUTHOR');
INSERT INTO role (id, name) VALUES (3, 'DOCTOR');
INSERT INTO role (id, name) VALUES (4, 'PATIENT');

INSERT INTO user_role (user_id, role_id) VALUES (1,1);
INSERT INTO user_role (user_id, role_id) VALUES (2,4);
INSERT INTO user_role (user_id, role_id) VALUES (3,4);
INSERT INTO user_role (user_id, role_id) VALUES (4,3);

INSERT INTO hibernate_sequence (next_val) VALUE (100);

INSERT INTO schedule(id, doctor_id, patient_id, start, end) VALUES (1,1,2,'2019-09-26 09:00:00','2019-09-26 09:07:00');
INSERT INTO schedule(id, doctor_id, patient_id, start, end) VALUES (2,1,3,'2019-09-26 15:30:00','2019-09-26 15:50:00');

INSERT INTO question(question,answer) VALUES('Як саме записатись на прийом?','Подзвоніть або заповніть заявку');
INSERT INTO question(question,answer) VALUES('Мій сеанс відмінили. Що робити?','Зверніться за телефоном, щоб вирішити дане питання, можливі збої, або зміни в розпорядку лікарів');

INSERT INTO disease(name, department_id) VALUES ('Гостра респіраторна вірусна інфекція (ускладнені форми)', 3);
INSERT INTO disease(name, department_id) VALUES ('Гострий бронхіт', 3);
INSERT INTO disease(name, department_id) VALUES ('Гіпертермія неясного ґенезу', 3);