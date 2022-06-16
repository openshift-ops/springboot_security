To work with JWT there are many steps to follow
-----------------------------------------------
STEPS
-------------------------------
1.We have to add the JWT dependancy to the project in order to create , parse and validate jwt
2.We have to get the user details by using the user details service may it be from DB or LDAP or any other system
3.We have to create the class which will use the Jwt library to create parse and validate the jwt token
4.Now we have to create and api which will accept the user name and password and gives back the jwt
5.In the above api we have to autheticate the user that normally spring security does and then we have to use the
user details service to get the user details and pass it to the class designed to for JWT in step 3 to help in creating the
JWT token and send it back in response
6.User can store the JWT in local storage or in cookie
Note: JWT is not related to authentication but to authorization
7.Then we have to tell spring security that we have to permit all users to access the above api in order for authentication
by disbaling the csrf and permitAll but authenticate for any other request
8.Now we have to tell spring security that before intercepting any request by using OncePerRequestFilter check the JWT once the user has been authenticated
from the Authorization header and take the JWT out and validate it using the JWT helper class mentioned above in step 3
9.If validation is successful then set the jwt in the SecurityContext if the authentication value is null
then we can set the jwt in the security context.
10.Then we have to tell the spring security to look up jwt instead of using session management so we have to disable it and the
tell spring security to use the filter provided.
11.We have to tell spring security to use the implemented filter before the UserPasswordAutheticationFilter.

