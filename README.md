# identifierservice
JAX-WS based WebService which can be used to build a service which creates unique identifiers backed by a relational database.

Steps to setup the WebService:

1. Setup DataSource in your ApplicationService with the following JNDI-Name: jdbc/IdentifierDataSource
2. Execute SQL scripts located in src/main/resources/sql to create all needed database objects like tables and sequences.
