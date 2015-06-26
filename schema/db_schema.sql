- Table: trip

-- DROP TABLE trip;

CREATE TABLE trip
(
  user_egn character(10) NOT NULL,
  date_of_arrival date,
  departure_date date,
  city character(120),
  CONSTRAINT pk_egn PRIMARY KEY (user_egn)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE trip
  OWNER TO postgres;


-- Table: "user"

-- DROP TABLE "user";

CREATE TABLE "user"
(
  user_name character(80),
  user_egn character(10) NOT NULL,
  user_age integer,
  user_email_address character(120),
  CONSTRAINT pk_user_egn UNIQUE (user_egn)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user"
  OWNER TO postgres;

-- Insert into user table and entered values.

INSERT INTO  user values(?, ?, ?, ?);

-- Insert into trip table with entered values.
INSERT INTO trip values(?, ?, ?, ?);

-- Delete data from table trip.
DELETE FROM trip;

--Delete data from table user.
DELETE FROM user;