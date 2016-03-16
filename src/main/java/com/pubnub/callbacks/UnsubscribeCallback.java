package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.AcknowledgmentStatus;
import com.pubnub.domain.Result;

public abstract class UnsubscribeCallback extends Callback {
    public abstract void status(AcknowledgmentStatus status);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        /*
        ClientStateUpdateStatus status = new ClientStateUpdateStatus();
        status = (ClientStateUpdateStatus) fillErrorStatusDetails(status, error, result);
        status.operation = OperationType.SET_STATE;
        status.errorData.channels = new String[]{channel};
        status(status);  
        */
    }
}
