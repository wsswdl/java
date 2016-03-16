package com.pubnub.api.pam;

public interface PubnubPamRevokeApiStateChannel {
    PubnubPamAsyncRevokeApiStateCOptions channel(String channel);
    PubnubPamAsyncRevokeApiStateCGOptions channelGroup(String channelGroup);
}
