package org.apache.alby.policy;

import org.apache.alby.metric.ConnectionBalanceMetric;

public class ConnectionCountPolicy extends LoadBalancePolicy<Long, ConnectionBalanceMetric> {

   @Override
   public String getPolicyName() {
      return "Connection";
   }

   long connectionCount;

   @Override
   public String getResourceName() {
      return "broker";
   }

   @Override
   public String getAttribute() {
      return "connectionCount";
   }

   @Override
   public ConnectionBalanceMetric createBalanceMetric() {
      return new ConnectionBalanceMetric();
   }
}
