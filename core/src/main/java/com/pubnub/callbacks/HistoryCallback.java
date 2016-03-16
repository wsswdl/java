package com.pubnub.callbacks;

import com.pubnub.api.Callback;
import com.pubnub.api.PubnubError;
import com.pubnub.domain.ErrorStatus;
import com.pubnub.domain.HistoryResult;
import com.pubnub.domain.OperationType;
import com.pubnub.domain.Result;
import org.json.JSONArray;
import static com.pubnub.api.PubnubError.*;
import org.json.JSONException;

public abstract class HistoryCallback extends Callback {
    public abstract void status(ErrorStatus status);
    public abstract void result(HistoryResult result);
    
    @Override
    public void successCallback(String channel, Object message, Result result) {
        HistoryResult hresult = (HistoryResult)result;
        try {
            hresult.getData().setMessages(((JSONArray) message).getJSONArray(0));
            hresult.getData().setStart((String)((JSONArray) message).getString(1));
            hresult.getData().setEnd((String)((JSONArray) message).getString(2));

            result(hresult);
        } catch (JSONException e) {
            // ERROR
            e.printStackTrace();
        } catch(Exception e) {
            // ERROR
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void errorCallback(String channel, PubnubError error, Result result) {
        
        ErrorStatus status = fillErrorStatusDetails(error, result);
        status.setOperation(OperationType.HISTORY);
        status.getErrorData().setChannels(new String[]{channel});
        status(status);

    }
}
