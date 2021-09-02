create table user
(
 id bigint generated by default as identity,
 username varchar(255),
 password varchar(255),
 enabled bit,
 primary key (id),
UNIQUE KEY username (username )
)

create table role
(
 id bigint generated by default as identity,
 name varchar(255),
 primary key (id)
)

create table user_role
(
 user_id bigint,
 role_id bigint,
 primary key (user_id, role_id)
)
