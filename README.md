## SteamFM
#### LP3 - 4G12 - 2015

**SteamFM** was a college project for the _Programming Languages 3_ class which we had to consume any API of our choice and build a web application around it using **Java EE** technologies such as **JPA**, **RMI**, **EJB** and **Servlet**.

Two APIs were chosen for the job: **Steam** and **LastFM**. The idea was to suggest songs, albums and artists to the user based on their game library on Steam.

The recommendation was made using a database relation between game genres and music releases. The project was deployed on a **GlassFish** server using **Derby**.

**Java RMI** was a requirement for the project and it was used to perform remote calls for database operations, therefore one application provided the RMI server and another worked as a client, which also exposed a web interface to the user.
