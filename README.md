# GMDB users service
### Requirements
Users can register for a new account (using email)
Users can login using email
Users can logout
### Acceptance Criteria
GIVEN I am a user
WHEN I send a POST request to the URI to login
THEN I recieve a successful status code when I attempt to login

GIVEN I am a user
WHEN I send a POST request to the URI to logout
THEN I recieve a successful status code when I attempt to logout

GIVEN I am a user
WHEN I go to the endpoint associated with the action to "logout"
THEN I recieve a json object or status code indicating that I have successfully been logged out

GIVEN I am not a registered user
WHEN I send a POST request to the URI to login
THEN I recieve a status code or json object indicating that I am not a registered user and therefore cannot be logged in

### Rest API documentation
* POST - /users/login
    {"userId": "userId"}
* POST - /users/logout
    {"userId": "userId"}
