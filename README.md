## Steam.fm
#### LP3 - 4G12 - 2015

Steam.fm is a music recommendation web application developed for the _Programming Languages 3_ class in college. The project was developed using **Java EE** technologies and two APIs of our choice. The idea was to suggest songs, albums and artists to the user based on their game library using **Steam** and **Last.fm** public APIs. The recommendation was created using a database relation between game genres and music releases and the project was deployed on a GlassFish server using Derby. 

The use of Java RMI was required and applied on remote calls for database operations (CRUD), one application provided the RMI server and a second one exposed a web interface to the client. The database entities (JPA) were put in a different package so they could be reused. 

Other technologies used: EJB, JNDI, JSF and Servlets.
