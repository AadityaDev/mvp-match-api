CREATE DATABASE demodb;

USE demodb;

CREATE TABLE tbl_users
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO tbl_users(email, password) VALUES ("bushan@example.com", "12345");
INSERT INTO tbl_users(email, password) VALUES ("bharath@example.com", "12345");


CREATE TABLE demodb.tbl_maze
(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    entrance VARCHAR(2) NOT NULL,
    gridsize VARCHAR(4) NOT NULL
    -- ,
    -- walls VARCHAR(255) NOT NULL
);