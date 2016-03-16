package com.pubnub.api.unsubscribe;

import com.pubnub.callbacks.UnsubscribeCallback;

public interface PubnubUnsubscribeAsyncInterface {
    PubnubUnsubscribeAsyncPresence callback(UnsubscribeCallback callback);
}
