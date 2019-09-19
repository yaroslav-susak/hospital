INSERT INTO hospital.department (id, description, name) VALUES (1, 'опис', 'Неврологія');
INSERT INTO hospital.department (id, description, name) VALUES (2, 'опис', 'Анестезіологія');
INSERT INTO hospital.department (id, description, name) VALUES (3, 'опис', 'Педіатрія');

INSERT INTO hospital.doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id) VALUES (1, 'Васильович', 'Ярослав', 'kz1pqa.jpg', 'Вища', 'Нейрохірург', 'Лупуляк', 1);
INSERT INTO hospital.doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id) VALUES (2, 'Львович', 'Ярослав', 'h56caz.jpg', 'Вища', 'Невролог', 'Криштафович', 1);
INSERT INTO hospital.doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id) VALUES (3, 'Васильович', 'Тарас', 'ce1kz8.jpg', 'Перша', 'Анестезіолог', 'Мельник', 2);
INSERT INTO hospital.doctor (id, middle_name, name, photo_name, qualification_level, specialization, surname, department_id) VALUES (4, 'Михайлович', 'Тарас', 'c34tqa.jpg', 'Перша', 'Педіатр', 'Величко', 3);

INSERT INTO hospital.role (id, name) VALUES (1, 'ADMIN');
INSERT INTO hospital.role (id, name) VALUES (2, 'USER');
INSERT INTO hospital.role (id, name) VALUES (3, 'AUTHOR');

INSERT INTO hospital.user (id, date_of_registration, email, first_name, last_name, password, photo) VALUES (1, '2019-09-17 15:58:37.612000000', 'susak.slava@gmail.com', 'Ярослав', 'Сусак', '$2a$10$XwOBe/x7BnbdNlOLdkW7IeGTCMhjy22MXgWtMDVOONtwgvbbKvxpy', null);

INSERT INTO hospital.user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO hibernate_sequence (next_val) VALUE (100);