# SPRING BOOT BIG POC

This project is a sample application applying a number of spring boot technologies in order to study their memory consumption. It implements a simple "Guest book" and works this way:

The guest book is a compendium of signatures of all people that visited your website. Anyone can sign the book but only administrators can list all guests. Administrators can also view how long it took to record each signature. They can also shutdown the application and test its livelihood. Whenever a guest registration occurs, the application will use an external service to generate an "Identicon" based on the visitor's email address. That process occurs on the background in an asynchronous way.

All signatures are kept in a MongoDB database, the timing log is kept on an oracle database, the background processing is backed by a rabbitmq.

## Getting Started

#### 1. Get the project with the command below

```bash
git clone https://bitbucket.org/leonardo-org/spring-boot-big-poc.git
```

#### 2. Build the oracle service and the application (ATTENTION: Oracle build takes about 1 hour to complete)

```bash
docker-compose build oracle guest-book
```

#### 3. Start the minimal applications (ATTENTION: Oracle startup is quite long for the first time)

```bash
docker-compose up oracle mongo rabbitmq guest-book prometheus
```

#### 4. Access the guest book

The application is listening in http at port #3000. You may use your preferred communications program to access the end points, but we advise you to install Postman and use the provided collection [Spring Big POC](./guest-book/Spring%20Big%20POC.postman_collection.json) at the java application folder. 

### Prerequisites

- Postman
- Docker
- Docker Compose
- Some IDE of your preference to tweak the code: VSCode, IntelliJ, Eclipse ...
- DBeaver or Oracle SQL Developer to access Oracle database
- MongoDB Compass or Robot 3T to access the mongo database.

## Utilities

 - The databases will be listening on their default ports.
 - Oracle USER/SCHEMA is named "GUEST_BOOK" and all passwords are '1234qwer'
 - Mongo DB database is named "guest-book".
 - Prometheus and RabbitMQ are also listening on their default ports.
 - RabbitMQ username/password are "guest"/"guest"

### Endpoints

**Java application**

_Access to the following endpoints require a Http Basic auth, except for the POST of guests. The username is **user** and the password is **1234qwer**._

 - http://localhost:3000/guests (Allows GET / POST)
 - http://localhost:3000/guests/{id} (Allows GET only)
 - http://localhost:3000/actuator (GET only)
 - http://localhost:3000/actuator/health (GET only)
 - http://localhost:3000/actuator/shutdown (POST only - It will stop the app)
 - http://localhost:3000/actuator/prometheus (GET only)

Use the following payload to post a guest:

_Schema_
```yaml
Guest:
  type: object
  properties:
    name:
      type: string
    email:
      type: string
```

_Sample_
```json
{
  "name": "Jack Sparrow",
  "email": "jack.sparrow@blackpearl.org"
}
```

**RabbitMQ Management**

 - http://localhost:15672/ (user/password = guest/guest)

**Prometheus Interface**

 - http://localhost:9090/