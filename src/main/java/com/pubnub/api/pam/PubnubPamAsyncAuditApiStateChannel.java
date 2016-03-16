package com.pubnub.api.pam;

public interface PubnubPamAsyncAuditApiStateChannel extends PubnubPamAsyncAuditEnd {
    PubnubPamAsyncAuditApiStateCOptions channel(String channel);
    PubnubPamAsyncAuditApiStateCGOptions channelGroup(String channelGroup);
}
