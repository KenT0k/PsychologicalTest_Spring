CREATE TABLE users
(
  id          SERIAL PRIMARY KEY,
  username    VARCHAR(60) NOT NULL UNIQUE,
  password    VARCHAR(70) NOT NULL,
  first_name  VARCHAR(60) NOT NULL,
  last_name   VARCHAR(60) NOT NULL,
  email       VARCHAR(60) NOT NULL,
  study_group VARCHAR(60) NOT NULL,
  role        VARCHAR(20) NOT NULL
);
CREATE TABLE added_tests
(
  id    SERIAL PRIMARY KEY,
  title VARCHAR(30) NOT NULL,
  date  DATE
);
CREATE TABLE result
(
  id      SERIAL PRIMARY KEY,
  id_user INTEGER REFERENCES users (id),
  title   VARCHAR(100) NOT NULL,
  date    DATE,
  result  INTEGER
);
CREATE TABLE role
(
  id   SERIAL PRIMARY KEY,
  role VARCHAR(20) NOT NULL
);
CREATE TABLE user_role
(
  id_user INTEGER NOT NULL REFERENCES users (id),
  id_role INTEGER NOT NULL REFERENCES role (id)
);
INSERT INTO role
VALUES (1, 'ADMIN'),
       (2, 'USER');
CREATE TABLE first_test
(
  id            SERIAL PRIMARY KEY,
  question      VARCHAR(100) NOT NULL,
  very_negative INTEGER,
  negative      INTEGER,
  neutral       INTEGER,
  positive      INTEGER,
  very_positive INTEGER
);
INSERT INTO first_test
VALUES (1, 'first question', -5, -4, 0, 4, 5),
       (2, 'second question', -5, -4, 0, 4, 5),
       (3, 'third question', -5, -4, 0, 4, 5),
       (4, 'fourth question', -5, -4, 0, 4, 5),
       (5, 'fifth question', -5, -4, 0, 4, 5);