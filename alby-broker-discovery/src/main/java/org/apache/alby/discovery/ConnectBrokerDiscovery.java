package org.apache.alby.discovery;

import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClusterTopologyListener;
import org.apache.activemq.artemis.api.core.client.TopologyMember;
import org.apache.activemq.artemis.core.client.impl.ServerLocatorInternal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectBrokerDiscovery implements BrokerDiscovery, ClusterTopologyListener {

   private ServerLocatorInternal locator;

   public static void main(String[] args) throws Exception {
      ServerLocatorInternal locator = (ServerLocatorInternal) ActiveMQClient.createServerLocator("tcp://localhost:61616");
      ConnectBrokerDiscovery discoverer = new ConnectBrokerDiscovery(locator);
      discoverer.start();
   }

   private void start() throws Exception {
      ExecutorService executor = Executors.newFixedThreadPool(1);
      locator.addClusterTopologyListener(this);
      locator.start(executor);
   }

   public ConnectBrokerDiscovery(ServerLocatorInternal locator) {
      this.locator = locator;
   }

   public void nodeUP(TopologyMember topologyMember, boolean b) {
      System.out.println("topologyMember = " + topologyMember);
   }

   public void nodeDown(long l, String s) {
      System.out.println("s = " + s);
   }

   @Override
   public List<String> getBrokers() {
      return null;
   }
}
