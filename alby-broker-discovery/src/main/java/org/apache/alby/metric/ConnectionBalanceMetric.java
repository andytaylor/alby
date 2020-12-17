package org.apache.alby.metric;

public class ConnectionBalanceMetric extends BalanceMetric<Long> {

   private Long connectionCount;
   @Override
   public void setMetric(Long metric) {
      this.connectionCount = metric;
   }

   @Override
   public Long getMetric() {
      return this.connectionCount;
   }

   @Override
   public void setResult(Long result) {
      this.connectionCount = result;
   }
}
