package org.apache.alby;

import java.util.concurrent.Callable;

public class BrokerEndpointRunnable implements Runnable {
   private BrokerEndpoint brokerEndpoint;

   public BrokerEndpointRunnable(BrokerEndpoint brokerEndpoint) {
      this.brokerEndpoint = brokerEndpoint;
   }

   @Override
   public void run() {
      brokerEndpoint.gather();
   }
}
