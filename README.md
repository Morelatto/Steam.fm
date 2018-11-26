## SteamFM
#### LP3 - 4G12 - 2015

**SteamFM** was a college project for the _Programming Languages 3_ class which we had to consume any API of our choice and build a web application around it using **Java EE** technologies such as **JPA**, **RMI**, **EJB**, **JSF** and **Servlets**.

Two APIs were chosen for the job: **Steam** and **LastFM**. The idea was to suggest songs, albums and artists to the user based on their game library on Steam.

The recommendation was made using a database relation between game genres and music releases. The project was deployed on a **GlassFish** server using **Derby**.

**Java RMI** was one of the requirements for the project and used to perform remote calls for database operations, therefore one application provided the RMI server and another worked as a client, which also exposed a web interface to the user.

The **JPA** entities were abstracted to a different project so they could be used as a common library in the server and client.
