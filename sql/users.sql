DROP SCHEMA IF EXISTS userlogin;
CREATE SCHEMA userlogin;
USE userlogin;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT NOT NULL,
  username VARCHAR(255),
  name VARCHAR(255),
  email VARCHAR(255),
  state VARCHAR(255),
  town VARCHAR(255),
  password VARCHAR(255),
  PRIMARY KEY (id)
);
CREATE TABLE role (
  id BIGINT AUTO_INCREMENT NOT NULL,
  name VARCHAR(255),
  user_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO user(id, username, name, email, state, town, password) VALUES
	(1, "sarahK", "Sarah Kerrigan", "sarahK@terran.zerg", "28", "005", "$2a$10$QrC4S.l3fwcGkjvfzaP1OOUZwUONERGMXa7dEY2nU9/1RPQsEdpK6"),
    (2, "pepito", "José Francisco Navarro", "jfn@jfn.jfn", "28", "049", "$2a$10$DrfrLTGlLhcVqnK5DVZqvel10KHR7ql9RVrSR2JYLsVG.8JQfUeiS"),
    (3, "epicRetro", "Miguel Peña", "miguelon@pena.com", "02", "009", "$2a$10$qH6K1613GruZC4FioYw/z.G7HHCk08Hiq8g1dwNGStUs/R7EM6zSW")
;