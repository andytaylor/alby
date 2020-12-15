package org.apache.alby;

import org.apache.alby.discovery.BrokerDiscovery;
import org.apache.alby.discovery.ListBrokerDiscovery;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Bootstrap {
   public static void main(String[] args) throws Exception {
      ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);
      ArrayList<String> uris = new ArrayList<>();
      uris.add("tcp://localhost:61616");
      BrokerDiscovery brokerDiscovery = new ListBrokerDiscovery(uris);
      BrokerEndpoints brokerEndpoints = new BrokerEndpoints(executor, brokerDiscovery);
      brokerEndpoints.gather();
   }
}
