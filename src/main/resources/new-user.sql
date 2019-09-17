CREATE USER 'newuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'localhost';
FLUSH PRIVILEGES;


CREATE DATABASE hospital CHARACTER SET utf8 COLLATE utf8_general_ci;