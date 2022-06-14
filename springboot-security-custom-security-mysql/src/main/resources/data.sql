insert into my_users (my_username,my_password,enabled)
values ( 'foo','foo',true );

insert into my_users (my_username,my_password,enabled)
values ( 'bishesh','bishesh',true );

insert into my_authorities (my_username,authority)
values ('bishesh','ROLE_USER');

insert into my_authorities (my_username,authority)
values ('foo','ROLE_ADMIN');

