# stivale-primaverile

* running on localhost:
```bash
mvn spring-boot:run
```

* running on openshift
```bash
oc new-project stivale
mvn package oc:deploy -Popenshift -DskipTests
```
