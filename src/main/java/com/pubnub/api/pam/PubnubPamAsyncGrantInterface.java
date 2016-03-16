package com.pubnub.api.pam;

import com.pubnub.callbacks.PamModifyCallback;

public interface PubnubPamAsyncGrantInterface {
    PubnubPamAsyncGrantApiStateChannel callback(PamModifyCallback callback);
}
