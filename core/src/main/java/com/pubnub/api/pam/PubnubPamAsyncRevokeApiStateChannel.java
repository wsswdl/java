package com.pubnub.api.pam;

public interface PubnubPamAsyncRevokeApiStateChannel extends PubnubPamAsyncRevokeEnd {
    PubnubPamAsyncRevokeApiStateOptions channel(String channel);
    PubnubPamAsyncRevokeApiStateOptions channelGroup(String channelGroup);
}
