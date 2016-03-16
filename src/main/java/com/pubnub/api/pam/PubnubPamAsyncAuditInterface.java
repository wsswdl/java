package com.pubnub.api.pam;

import com.pubnub.callbacks.PamAuditCallback;

public interface PubnubPamAsyncAuditInterface {
    PubnubPamAsyncAuditApiStateChannel callback(PamAuditCallback callback);
}
