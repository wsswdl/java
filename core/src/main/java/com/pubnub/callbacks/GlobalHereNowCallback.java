package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.*;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class GlobalHereNowCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(GlobalHereNowResult result);
    
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        GlobalHereNowResult hresult = (GlobalHereNowResult)result;
        System.out.println(message);
        try {
            hresult.getData().setOccupancy(((JSONObject) message).getInt("occupancy"));
        } catch (JSONException e) {
            // ERROR
            //e.printStackTrace();
        } catch(Exception e) {
            //e.printStackTrace();
        }
        try {

            hresult.getData().setUuids(GlobalHereNowData.getUuidDataArray(((JSONObject) message).getJSONArray("uuids")));
        } catch (JSONException e) {
            // ERROR
            //e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        result(hresult);
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        ErrorStatus status = fillErrorStatusDetails(error, result);
        status.setOperation(OperationType.HERE_NOW_GLOBAL);
        status.getErrorData().setChannels(new String[]{channel});
        status(status);  
    }
}
