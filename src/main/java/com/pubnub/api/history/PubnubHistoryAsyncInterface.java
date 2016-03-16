package com.pubnub.api.history;

import com.pubnub.callbacks.HistoryCallback;

public interface PubnubHistoryAsyncInterface {
    PubnubHistoryAsyncApiStateChannel callback(HistoryCallback callback);
}
