package org.apache.alby.policy;


import org.apache.alby.metric.BalanceMetric;

import java.util.HashMap;
import java.util.Map;

public abstract class LoadBalancePolicy<T, U extends BalanceMetric> {

   public abstract String getResourceName();

   public abstract String getAttribute();

   public abstract String getPolicyName();

   public abstract  U createBalanceMetric();

   Map<String, U> metrics = new HashMap<>();

   public void addMetric(String url) {
      metrics.put(url, createBalanceMetric());
   }

   public U getBalanceMetric(String uri) {
      return metrics.get(uri);
   }

}
