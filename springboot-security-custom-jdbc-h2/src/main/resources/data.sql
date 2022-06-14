insert into users (username,password,enabled)
values ( 'foo','foo',true );

insert into users (username,password,enabled)
values ( 'bishesh','bishesh',true );

insert into authorities (username,authority)
values ('bishesh','ROLE_USER');

insert into authorities (username,authority)
values ('foo','ROLE_ADMIN');

