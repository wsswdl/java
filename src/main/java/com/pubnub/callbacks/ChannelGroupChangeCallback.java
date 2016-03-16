package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.AcknowledgmentStatus;
import com.pubnub.domain.Result;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ChannelGroupChangeCallback extends Callback {
    public abstract void status(AcknowledgmentStatus status);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        AcknowledgmentStatus status = new AcknowledgmentStatus(result);
        status(status);
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        AcknowledgmentStatus status = new AcknowledgmentStatus(fillErrorStatusDetails(error, result));

        // operation type to be filled by API
        
        status.getErrorData().setChannels(new String[]{channel});
        status(status);     
    }
    
}
