# Task Rabbit

## Taskrabbit using Jhipster Lite
This project is a simple Backend implementation of the Task rabbit application. It mainly has two actors; a bidder and a tasker.
One issues tasks, and the other bids to win and execute won tasks.

## Node.js and NPM

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js](https://nodejs.org/): We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

```
npm install
```

## Local environment

- [Local server](http://localhost:8080)
- [Local API doc](http://localhost:8080/swagger-ui.html)

<!-- jhipster-needle-localEnvironment -->

## Start up

```bash
./mvnw
```

```bash
docker compose -f src/main/docker/sonar.yml up -d
./mvnw clean verify sonar:sonar
```

```bash
docker compose -f src/main/docker/postgresql.yml up -d
```

```bash
docker compose -f src/main/docker/keycloak.yml up -d
```


<!-- jhipster-needle-startupCommand -->

## Documentation

- [Package types](documentation/package-types.md)
- [Assertions](documentation/assertions.md)
- [sonar](documentation/sonar.md)
- [Logs spy](documentation/logs-spy.md)
- [Postgresql](documentation/postgresql.md)
- [Application errors](documentation/application-errors.md)
- [Jpa pages](documentation/jpa-pages.md)
- [CORS configuration](documentation/cors-configuration.md)
- [Cucumber](documentation/cucumber.md)
- [Rest pagination](documentation/rest-pagination.md)
- [Kipe authorization](documentation/kipe-authorization.md)
- [Kipe expression](documentation/kipe-expression.md)

<!-- jhipster-needle-documentation -->
