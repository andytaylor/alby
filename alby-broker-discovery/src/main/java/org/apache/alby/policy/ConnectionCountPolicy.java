package org.apache.alby.policy;

public class ConnectionCountPolicy implements LoadBalancePolicy<Long> {

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
   public void setResult(Long result) {
      connectionCount = result;
   }
}
