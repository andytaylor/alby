package org.acme.getting.started;

import javax.annotation.PostConstruct;
import org.apache.alby.BrokerEndpoints;
import org.apache.alby.discovery.BrokerDiscovery;
import org.apache.alby.discovery.ListBrokerDiscovery;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@ApplicationScoped
public class AlbyService {


   private ScheduledExecutorService executor;
   private BrokerEndpoints brokerEndpoints;

   @PostConstruct
   public void init() {
      executor = Executors.newScheduledThreadPool(8);
      ArrayList<String> uris = new ArrayList<>();
      uris.add("tcp://localhost:61616");
      BrokerDiscovery brokerDiscovery = new ListBrokerDiscovery(uris);
      brokerEndpoints = new BrokerEndpoints(executor, brokerDiscovery);
      brokerEndpoints.gather();
   }

   public String getEndpoint(String policyType) {
      System.out.println("AlbyService.greeting ");
      return brokerEndpoints.getbroker(policyType);
   }

}