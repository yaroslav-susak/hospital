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
    banned BOOLEAN,
    PRIMARY KEY (id)
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE department
(
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id)
) ENGINE =INNODB CHAR SET =utf8;

CREATE TABLE question
(
    id INT AUTO_INCREMENT,
    question TEXT NOT NULL,
    answer TEXT NOT NULL,
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
    user_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE,
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
    start DATETIME(6) NOT NULL,
    end DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES user(id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE disease
(
    id INT AUTO_INCREMENT,
    name VARCHAR(1000) NOT NULL,
    department_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE review
(
    id INT AUTO_INCREMENT,
    date DATETIME(6) NOT NULL,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    text TEXT NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (patient_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE news
(
    id INT AUTO_INCREMENT,
    author_id INT,
    date DATETIME(6),
    name VARCHAR(150) NOT NULL,
    text TEXT NOT NULL,
    type VARCHAR(25) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE comment
(
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    date DATETIME(6) NOT NULL,
    text TEXT NOT NULL,
    news_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=INNODB CHAR SET=utf8;

CREATE TABLE hibernate_sequence
(
    next_val INT
) ENGINE=INNODB;


