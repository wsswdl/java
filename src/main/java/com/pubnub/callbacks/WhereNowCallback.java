package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubUtil;
import com.pubnub.domain.ErrorStatus;
import com.pubnub.domain.OperationType;
import com.pubnub.domain.Result;
import com.pubnub.domain.WhereNowResult;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WhereNowCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(WhereNowResult result);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        WhereNowResult hresult = (WhereNowResult)result;
        System.out.println(message);
        try {
            hresult.getData().setChannels(PubnubUtil.jsonArrayToStringArray(((JSONObject) message).getJSONArray("channels")));
            result(hresult);
        } catch (JSONException e) {
            // ERROR
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        ErrorStatus status = fillErrorStatusDetails(error, result);
        status.setOperation(OperationType.WHERE_NOW);
        status(status);  
    }
}
