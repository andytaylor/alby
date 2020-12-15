package org.apache.alby;

import org.apache.activemq.artemis.api.core.client.ActiveMQClient;
import org.apache.activemq.artemis.api.core.client.ClientMessage;
import org.apache.activemq.artemis.api.core.client.ClientRequestor;
import org.apache.activemq.artemis.api.core.client.ClientSession;
import org.apache.activemq.artemis.api.core.client.ClientSessionFactory;
import org.apache.activemq.artemis.api.core.client.ServerLocator;
import org.apache.activemq.artemis.api.core.management.ManagementHelper;
import org.apache.alby.policy.LoadBalancePolicy;

import java.util.List;

public class BrokerEndpoint {

   private String uri;
   private List<LoadBalancePolicy> policies;

   public BrokerEndpoint(String uri, List<LoadBalancePolicy> policies) {
      this.uri = uri;
      this.policies = policies;
   }

   public void gather() {
      try (ServerLocator locator = ActiveMQClient.createServerLocator(uri)) {

         ClientSessionFactory sessionFactory = locator.createSessionFactory();
         ClientSession session = sessionFactory.createSession();
         ClientRequestor requestor = new ClientRequestor(session, "activemq.management");
         session.start();
         for (LoadBalancePolicy policy : policies) {
            ClientMessage message = session.createMessage(false);
            ManagementHelper.putAttribute(message,policy.getResourceName(), policy.getAttribute());
            ClientMessage reply = requestor.request(message);
            policy.setResult(ManagementHelper.getResult(reply));
         }
      } catch (Exception e) {
         System.out.println("unable to gather connection metrics for " + uri + " " + e.getMessage());
      }
   }
}
