package com.pubnub.api;

import com.pubnub.domain.Result;

import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.Vector;
import static com.pubnub.api.PubnubError.*;

class SubscribeWorker extends AbstractSubscribeWorker {

    private Exception excp = null;

    SubscribeWorker(Vector _requestQueue, int connectionTimeout, int requestTimeout, int maxRetries, int retryInterval,
            int windowInterval, Hashtable headers) {
        super(_requestQueue, connectionTimeout, requestTimeout, maxRetries, retryInterval, windowInterval, headers);
    }

    void process(HttpRequest hreq) {
        HttpResponse hresp = null;
        Result result = null;
        int currentRetryAttempt = (hreq.isDar()) ? 1 : maxRetries;
        Worker.log.verbose("disconnectAndResubscribe is " + hreq.isDar());
        if (hreq.getWorker() != null) {
            Worker.log.verbose("Request placed by worker " + hreq.getWorker().getThread().getName());
            if (hreq.getWorker()._die) {
                Worker.log.verbose("The thread which placed the request has died, so ignore the request : "
                        + hreq.getWorker().getThread().getName());
                return;
            }
        }
        hreq.setWorker(this);
        if (!hreq.isSubzero() && windowInterval != 0) {
            try {
                Thread.sleep(windowInterval);
            } catch (InterruptedException e) {
            }
        }
        boolean sleep = false;
        while (!_die && currentRetryAttempt <= maxRetries) {
            if (sleep) {
                try {
                    Thread.sleep(retryInterval);
                } catch (InterruptedException e) {
                }
            }
            sleep = true;
            try {
                Worker.log.debug(hreq.getUrl());

                result = hreq.getResult();
                result.setClientRequest(hreq.getUrl());
                hresp = httpclient.fetch(hreq.getUrl(), hreq.getHeaders());
                result.setServerResponse(hresp.getResponse());
                result.setCode(hresp.getStatusCode());
                if (hresp != null && HttpUtil.checkResponseSuccess(hresp.getStatusCode())) {
                    currentRetryAttempt = 1;
                    break;
                }
            } catch (SocketTimeoutException e) {
                Worker.log.verbose("No Traffic , Read Timeout Exception in Fetch : " + e.toString());
                if (_die) {
                    Worker.log.verbose("Asked to Die, Don't do back from DAR processing");
                    break;
                }
                if (hreq.isDar()) {
                    hreq.getResponseHandler().handleBackFromDar(hreq);
                    return;
                }
                hreq.getResponseHandler().handleError(hreq, PubnubError.getErrorObject(PubnubError.PNERROBJ_SUBSCRIBE_TIMEOUT, 1), result);
                return;

            } catch (PubnubException e) {
                excp = e;
                result.setServerResponse(e.getErrorResponse());
                result.setCode(e.getStatusCode());
                switch (e.getPubnubError().errorCode) {
                case PubnubError.PNERR_FORBIDDEN:
                case PubnubError.PNERR_UNAUTHORIZED:
                    Worker.log.verbose("Authentication Failure : " + e.toString());
                    currentRetryAttempt = maxRetries + 1;
                    break;
                default:
                    Worker.log.verbose("Retry Attempt : "
                            + ((currentRetryAttempt == maxRetries) ? "last" : currentRetryAttempt)
                            + " Exception in Fetch : " + e.toString());
                    currentRetryAttempt++;
                    break;
                }

            } catch (Exception e) {
                excp = e;
                Worker.log.verbose("Retry Attempt : " + ((currentRetryAttempt == maxRetries) ? "last" : currentRetryAttempt)
                        + " Exception in Fetch : " + e.toString());
                currentRetryAttempt++;
            }

        }
        if (!_die) {
            if (hresp == null) {
                Worker.log.debug("Error in fetching url : " + hreq.getUrl());
                if (hreq.isDar()) {
                    Worker.log.verbose("Exhausted number of retries");
                    hreq.getResponseHandler().handleTimeout(hreq);
                } else {
                    if (excp != null && excp instanceof PubnubException
                            && ((PubnubException) excp).getPubnubError() != null) {
                        hreq.getResponseHandler().handleError(hreq, ((PubnubException) excp).getPubnubError(), result);
                    } else {
                        hreq.getResponseHandler().handleError(hreq, PubnubError.getErrorObject(PubnubError.PNERROBJ_HTTP_ERROR, 1), result);
                    }
                }
                return;
            }
            Worker.log.debug(hresp.getResponse());
            hreq.getResponseHandler().handleResponse(hreq, hresp.getResponse(), result);
        }

    }

    public void shutdown() {
        if (httpclient != null)
            httpclient.shutdown();
    }
}
