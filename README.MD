# A simple social networking application

The application is a simple website which has some simple features like other socialising sites: 
you can create a new account on the register page, log in to your account, write messages in
the chat to other registered users, upload images that other users can see and, if needed,
you can modify your credentials or even delete your account.
The app comprises a Java and SQL project, running on the Tomcat server. 
The management of users and photos are taken care of by this system. It uses HTTP requests to complete the mentioned operations between the client and server. 
The process that the server follows is decoding the request, authenticating the user, and making changes to the database. 
JSON is used here to encode the objects that are communicated between the two sides.


## Installation
Open IntelliJ IDEA and create a new Java project \
Set JDK version 19 and Tomcat version 10

## Usage
After finishing setting up the environment, just start the Tomcat server, and then you will be prompted with the login page.

## Security
The server authenticates all the incoming requests using a token. The token is HttpOnly, so it is protected from XSS attacks as
it cannot be accessed by running scripts on the client side. The database is protected from SQL injection by
sanitizing input from users and using prepared statements when accessing or modifying data in the database.