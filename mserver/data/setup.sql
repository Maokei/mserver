CREATE TABLE user
(
    id uuid PRIMARY KEY,
    username VARCHAR ( 50 ) UNIQUE NOT NULL,
    password VARCHAR ( 50 ) NOT NULL,
    email VARCHAR ( 255 ) UNIQUE NOT NULL,
    created TIMESTAMP NOT NULL,
    updated TIMESTAMP NULL,
    last_login TIMESTAMP
);

/*CREATE TABLE "user" (
  "id" integer NOT NULL,
  "username" character varying(30) NOT NULL,
  "password" character varying(50) NOT NULL,
  "email" character varying(50) NOT NULL,
  "enabled" boolean NOT NULL,
  "roles" text NOT NULL
);*/

CREATE TABLE "comment" (
    "id" uuid NOT NULL,
    PRIMARY KEY ("id"),
    "user_id" uuid NOT NULL,
    "comment" text NOT NULL,
    "created" timestamp NOT NULL,
    "updated" timestamp NULL
);