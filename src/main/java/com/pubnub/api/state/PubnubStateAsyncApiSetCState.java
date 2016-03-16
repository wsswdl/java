package com.pubnub.api.state;

import org.json.JSONObject;

public interface PubnubStateAsyncApiSetCState {
    PubnubStateAsyncApiSetCCb state(JSONObject state);
}
