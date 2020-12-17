package org.apache.alby;

import org.apache.alby.discovery.BrokerDiscovery;
import org.apache.alby.policy.ConnectionCountPolicy;
import org.apache.alby.policy.LoadBalancePolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BrokerEndpoints {

   private final ScheduledExecutorService executor;
   private final BrokerDiscovery brokerDiscovery;
   private final Map<String, LoadBalancePolicy> policies = new HashMap<>();

   public BrokerEndpoints(ScheduledExecutorService executor, BrokerDiscovery brokerDiscovery) {
      this.executor = executor;
      this.brokerDiscovery = brokerDiscovery;
      ConnectionCountPolicy connectionCountPolicy = new ConnectionCountPolicy();
      policies.put(connectionCountPolicy.getPolicyName(), connectionCountPolicy);
   }

   public void gather() {
      List<String> brokers = brokerDiscovery.getBrokers();
      for (String broker : brokers) {
         BrokerEndpoint brokerEndpoint = new BrokerEndpoint(broker, policies);
         BrokerEndpointRunnable runnable = new BrokerEndpointRunnable(brokerEndpoint);
         executor.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
      }
   }

   public String getbroker(String policyType) {
      return null;
   }
}
