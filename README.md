# identifierservice #
JAX-WS based WebService which can be used to build a service which creates unique identifiers backed by a relational database.

## Steps to setup the WebService: ##

1. Setup DataSource in your ApplicationService with the following JNDI-Name: jdbc/IdentifierDataSource
2. Execute SQL scripts located in src/main/resources/sql to create all needed database objects like tables and sequences.
3. Build the impl project using Maven.
4. Deploy resulting .war file to a Java EE 7 ApplicationServer. I used Payara for testing purposes. After deployment the WebService should be available to reserve IdentifierBlocks.
