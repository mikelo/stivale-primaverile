package ibm.com.stivaleprimaverile;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import io.prometheus.client.Histogram;
import okhttp3.Request;
// import io.prometheus.client.Counter;

@Component
public class StivalOne {

  private final AtomicInteger testGauge;
  private final Counter testCounter;
  private static Histogram requestLatency;
  private static io.prometheus.client.Counter prometheusCounter = io.prometheus.client.Counter.build()
  .name("stivalone").help("Total calls.")
  .labelNames("stivaletto").register();

  public StivalOne(MeterRegistry meterRegistry) {
    // Counter vs. gauge, summary vs. histogram
    // https://prometheus.io/docs/practices/instrumentation/#counter-vs-gauge-summary-vs-histogram
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

  private static int getRandomNumberInRange(int min, int max) {
    if (min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  void processThatCalculates(String key) {
    prometheusCounter.labels(key).inc();
    // Run calculations.
   }
     
       void processRequest(Request req) {
         Histogram.Timer requestTimer = requestLatency.startTimer();
         try {
           // Your code here.
         } finally {
           requestTimer.observeDuration();
         }
       }
}
