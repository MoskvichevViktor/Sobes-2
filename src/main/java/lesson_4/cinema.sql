DROP SCHEMA IF EXISTS cinema;
CREATE SCHEMA cinema;
USE cinema;

CREATE TABLE IF NOT EXISTS movies (
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(128) NOT NULL,
    duration INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS halls (
    id INT NOT NULL AUTO_INCREMENT,
    hall_name VARCHAR(64) NOT NULL,
    comments VARCHAR(128),
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS seats (
    id INT NOT NULL AUTO_INCREMENT,
    hall_id INT NOT NULL,
    row_num INT NOT NULL,
    seat_num INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    INDEX fk_seats_halls_idx (hall_id ASC) VISIBLE,
    CONSTRAINT fk_seats_halls
      FOREIGN KEY (hall_id)
  	REFERENCES halls (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION) ;

CREATE TABLE sessions (
    id INT NOT NULL AUTO_INCREMENT,
    movie_id INT NOT NULL,
    hall_id INT NOT NULL,
    session_date DATE NOT NULL,
    session_time TIME NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_sessions_halls_idx (hall_id ASC) VISIBLE,
    INDEX fk_sessions_movies_idx (movie_id ASC) VISIBLE,
    CONSTRAINT fk_sessions_halls
      FOREIGN KEY (hall_id)
      REFERENCES halls (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
    CONSTRAINT fk_sessions_movies
      FOREIGN KEY (movie_id)
  	REFERENCES movies (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

CREATE TABLE prices (
    id INT NOT NULL AUTO_INCREMENT,
    session_id INT NOT NULL,
    price DECIMAL(5,2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    INDEX fk_prices_sessions_idx (session_id ASC) VISIBLE,
    CONSTRAINT fk_prices_sessions
      FOREIGN KEY (session_id)
  	REFERENCES sessions (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

CREATE TABLE tickets (
    id INT NOT NULL AUTO_INCREMENT,
    session_id INT NOT NULL,
    seat_id INT NOT NULL,
    sold_out TINYINT,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    INDEX fk_tickets_sessions_idx (session_id ASC) VISIBLE,
    INDEX fk_tickets_seats_idx (seat_id ASC) VISIBLE,
    CONSTRAINT fk_tickets_sessions
      FOREIGN KEY (session_id)
  	REFERENCES sessions (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
    CONSTRAINT fk_tickets_seats
      FOREIGN KEY (seat_id)
      REFERENCES seats (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);

INSERT INTO halls(hall_name, comments) VALUES
    ("Зал №1", "малый зал - 10 мест");


INSERT INTO movies(title, duration) VALUES
    ("Movie_1", 60),
    ("Movie_2", 90),
    ("Movie_3", 90),
    ("Movie_4", 120);

INSERT INTO seats(hall_id, row_num, seat_num) VALUES
    (1, 1, 1), (1, 1, 2), (1, 1, 3), (1, 1, 4), (1, 1, 5),
    (1, 2, 1), (1, 2, 2), (1, 2, 3), (1, 2, 4), (1, 2, 5);

INSERT INTO sessions(movie_id, hall_id, session_date, session_time) VALUES
    (1, 1, '2022-01-01', '09:00'),
    (2, 1, '2022-01-01', '12:00'),
    (3, 1, '2022-01-01', '16:00');

INSERT INTO prices(session_id, price) VALUES
    (1, 100.00),
    (2, 150.00),
    (3, 150.00);

INSERT INTO tickets(session_id, seat_id, sold_out) VALUES
    (1, 1, 1),
    (2, 2, 1);