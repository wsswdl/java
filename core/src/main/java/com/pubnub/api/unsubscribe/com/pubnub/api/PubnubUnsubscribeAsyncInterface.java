package com.pubnub.api.unsubscribe.com.pubnub.api;

import com.pubnub.api.unsubscribe.com.pubnub.api.PubnubUnsubscribeAsyncPresence;
import com.pubnub.callbacks.UnsubscribeCallback;

public interface PubnubUnsubscribeAsyncInterface {
    PubnubUnsubscribeAsyncPresence callback(UnsubscribeCallback callback);
}
