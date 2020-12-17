package org.apache.alby.metric;

import java.util.HashMap;

public abstract class BalanceMetric<T> {

   abstract void setMetric(T metric);

   abstract T getMetric();

   public abstract void setResult(T result);
}
