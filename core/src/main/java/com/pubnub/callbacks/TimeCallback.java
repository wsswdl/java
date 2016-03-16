package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.ErrorStatus;
import com.pubnub.domain.OperationType;
import com.pubnub.domain.Result;
import com.pubnub.domain.TimeResult;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class TimeCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(TimeResult result);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
      TimeResult tresult = new TimeResult(result);
      try {
        tresult.data.timetoken = ((JSONArray) message).getString(0);
        } catch (JSONException e) {
            // Error Handler
        }
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        ErrorStatus status = fillErrorStatusDetails(error, result);
        status.operation = OperationType.TIME;
        status(status);       
    }
}
