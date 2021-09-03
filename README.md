simple POC showing how to implement prometheus metrics in a spring-boot application.
=======

we must first add to our pom.xml the required dependencies needed to use prometheus libraries inside our code:

```xml
		<dependency>
			<groupId>io.prometheus</groupId>
			<artifactId>simpleclient_spring_boot</artifactId>
			<version>0.11.0</version>
		</dependency>
       <dependency>
            <groupId>io.prometheus</groupId>
            <artifactId>simpleclient_common</artifactId>
            <version>0.11.0</version>
        </dependency>
        <dependency>
          <groupId>io.prometheus</groupId>
          <artifactId>simpleclient_spring_web</artifactId>
          <version>0.11.0</version>
        </dependency>
```    

then we need to configure our /metrics endpoint in our application.properties:

```properties
management.endpoints.web.exposure.include=prometheus,health
management.endpoints.jmx.exposure.include=prometheus
management.endpoints.web.base-path=/
management.endpoints.web.path-mapping.prometheus=/metrics
```
next we add some custom metrics to the StivalOne.java class:
```java
public StivalOne(MeterRegistry meterRegistry) {
    testGauge = meterRegistry.gauge("stiva_gauge", new AtomicInteger(0));
    testCounter = meterRegistry.counter("conta_stivalone");
    requestLatency = Histogram.build()
    .name("latenza_primaverile").help("Request latency in seconds.").register();
}

  @Scheduled(fixedRateString = "1000", initialDelayString = "0")
  public void schedulingTask() {
    testGauge.set(StivalOne.getRandomNumberInRange(0, 100));

    testCounter.increment();
  }
```
* finally we can deploy our application in two ways:

on localhost:
```bash
mvn spring-boot:run
```

on openshift
```bash
oc new-project stivale
mvn package oc:deploy -Popenshift -DskipTests
```
