package com.pubnub.callbacks;

import com.pubnub.api.PubnubError;
import com.pubnub.domain.ChannelGroupChannelsResult;
import com.pubnub.domain.ErrorStatus;
import com.pubnub.domain.OperationType;
import com.pubnub.domain.Result;

public abstract class GroupChannelsAuditCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(ChannelGroupChannelsResult result);
    
    @Override
    void successCallback(String channel, Object message, Result result) {
        
    }
    
    @Override
    void errorCallback(String channel, PubnubError error, Result result) {
        ErrorStatus status = fillErrorStatusDetails(error, result);
        status.operation = OperationType.CHANNELS_FOR_GROUP;
        status.errorData.channels = new String[]{channel};
        status(status);          
    }
}
