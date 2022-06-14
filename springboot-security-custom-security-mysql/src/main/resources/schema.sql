drop table if exists my_authorities;
drop table if exists my_users;
create table my_users(
                      my_username varchar(50) not null primary key,
                      my_password varchar(50) not null,
                      enabled boolean not null
);

create table my_authorities (
                             my_username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(my_username) references my_users(my_username)
);
create unique index ix_auth_username on my_authorities (my_username,authority);