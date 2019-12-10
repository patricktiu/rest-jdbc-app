# rest-jdbc-app

### Start local PostgreSql docker container
https://medium.com/@wkrzywiec/database-in-a-docker-container-how-to-start-and-whats-it-about-5e3ceea77e50
* docker pull postgres
* docker run --name postgres-docker -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres

### Start Liberty server with setting these env for PostgreSQL DB 
```
 export PG_HOST=localhost
 export PG_PORT=5432
 export PG_USER=postgres
 export PG_PASS=postgres
 export PG_DBNAME=postgres
```
maven package liberty:run

### Open openapi ui in a browser
http://localhost:9080/openapi/ui/

### Call the REST endpoint
http://localhost:9080/myservice/

### Open a local DB tool (https://dbeaver.io/) 

