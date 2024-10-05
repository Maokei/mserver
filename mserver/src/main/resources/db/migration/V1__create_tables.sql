CREATE TABLE comments (
	id UUID NOT NULL,
    user_id UUID NOT NULL,
	comment TEXT NOT NULL,
	created TIMESTAMP DEFAULT now(),
	updated TIMESTAMP DEFAULT now(),
	PRIMARY KEY(id)
);
INSERT INTO "comments" ("id", "user_id", "comment")
VALUES ('545183b5-e7e5-4380-8bb0-d3853da3be85', '74e95ef3-0e8d-4f75-9205-0c43c6e280dc', 'First');

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    roles VARCHAR(255)[],
    enabled BOOLEAN NOT NULL
);

CREATE TABLE userRoles (
    user_id UUID NOT NULL,
    role_ud UUID NOT NULL
);

CREATE TABLE roles (
    id INT NOT NULL,
    role VARCHAR(15) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE media (
    id UUID NOT NULL,
    created TIMESTAMP DEFAULT now(),
    updated TIMESTAMP DEFAULT now(),
    foreign_id VARCHAR(30) NOT NULL,
    title VARCHAR(60) NOT NULL,
    user_id UUID NOT NULL,
    type TEXT NOT NULL,
    /*metadata JSONB NOT NULL,*/
    filename VARCHAR(60) NOT NULL,
    size INT NOT NULL,
    content TEXT NOT NULL,
    location TEXT NOT NULL,
    PRIMARY KEY (id)
);