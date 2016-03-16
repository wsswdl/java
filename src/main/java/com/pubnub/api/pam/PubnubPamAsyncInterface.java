package com.pubnub.api.pam;

public interface PubnubPamAsyncInterface {
    PubnubPamAsyncGrantInterface  grant();
    PubnubPamAsyncRevokeInterface revoke();
    PubnubPamAsyncAuditInterface audit();
}
