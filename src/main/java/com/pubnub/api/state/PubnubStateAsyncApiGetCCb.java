package com.pubnub.api.state;

import com.pubnub.callbacks.ChannelStateCallback;

public interface PubnubStateAsyncApiGetCCb {
    PubnubStateAsyncApiGetEnd callback(ChannelStateCallback callback);
}
