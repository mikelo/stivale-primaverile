# stivale-primaverile

* running on localhost:
```bash
mvn spring-boot:run
```

* running on openshift
```bash
oc new-project stivale
http://localhost:8080/actuator/metrics/http.server.requests
mvn package oc:deploy -Popenshift -DskipTests
```