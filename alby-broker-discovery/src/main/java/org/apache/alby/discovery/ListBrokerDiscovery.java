package org.apache.alby.discovery;

import java.util.List;

public class ListBrokerDiscovery implements BrokerDiscovery {
   private List<String> uris;

   public ListBrokerDiscovery(List<String> uris) {
      this.uris = uris;
   }

   @Override
   public List<String> getBrokers() {
      return uris;
   }
}
