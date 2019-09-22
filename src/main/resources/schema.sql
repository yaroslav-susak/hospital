drop database hospital;
create database hospital character set utf8;

use hospital;

CREATE TABLE user
(
    id INT AUTO_INCREMENT,
    date_of_registration DATETIME(6)  NOT NULL,
    email VARCHAR(255) NOT NULL  UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    photo VARCHAR(255) NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE department
(
    id INT AUTO_INCREMENT,
    description TEXT NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE =INNODB CHAR SET =utf8;

CREATE TABLE doctor
(
    id INT AUTO_INCREMENT,
    middle_name VARCHAR(50) NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    photo_name VARCHAR(255),
    qualification_level VARCHAR(50) NOT NULL,
    specialization VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    department_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE role
(
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE schedule
(
    id INT AUTO_INCREMENT,
    doctor_id INT NOT NULL,
    patient_id INT,
    start DATE NOT NULL,
    end DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE hibernate_sequence
(
    next_val INT
) ENGINE=INNODB;


