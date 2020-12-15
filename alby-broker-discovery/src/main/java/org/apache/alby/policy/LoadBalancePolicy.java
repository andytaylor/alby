package org.apache.alby.policy;

import org.apache.activemq.artemis.api.core.client.ClientRequestor;

public interface LoadBalancePolicy<T> {

   String getResourceName();

   String getAttribute();

   void setResult(T result);
}
