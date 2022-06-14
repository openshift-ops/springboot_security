drop table if exists user;
create table user(
                      id INTEGER primary key,
                      active boolean not null,
                      password varchar(50) not null,
                      roles varchar(100) not null,
                      user_name varchar(50) not null
);