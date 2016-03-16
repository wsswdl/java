package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.ClientStateUpdateStatus;
import com.pubnub.domain.OperationType;
import com.pubnub.domain.Result;

public abstract class SetStateCallback extends Callback {
    public abstract void status(ClientStateUpdateStatus status);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        ClientStateUpdateStatus status = new ClientStateUpdateStatus();
        status = (ClientStateUpdateStatus) fillErrorStatusDetails(status, error, result);
        status.setOperation(OperationType.SET_STATE);
        status.getErrorData().setChannels(new String[]{channel});
        status(status);  
    }
}
