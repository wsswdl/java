package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.PublishStatus;
import com.pubnub.domain.Result;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class PublishCallback extends Callback {
    public abstract void status(PublishStatus status);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {

        PublishStatus status = new PublishStatus(result);
        status.setError(false);
        try {
            status.getData().timetoken = ((JSONArray) message).getString(2);
            status.getData().information = ((JSONArray) message).getString(1);
            status(status);
        } catch (JSONException e) {
            e.printStackTrace();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        PublishStatus status = new PublishStatus(result);
        status.getErrorData().setChannels(new String[]{channel});
        status(status);
    }
}
