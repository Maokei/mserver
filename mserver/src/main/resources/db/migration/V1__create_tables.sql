CREATE TABLE IF NOT EXISTS comment (
	id uuid not null,
    user_id uuid not null,
	comment text not null,
	created timestamp default now(),
	updated timestamp default now(),
	PRIMARY KEY( id )
);
INSERT INTO "comment" ("id", "user_id", "comment")
VALUES ('545183b5-e7e5-4380-8bb0-d3853da3be85', '74e95ef3-0e8d-4f75-9205-0c43c6e280dc', 'First');