package com.pubnub.api.pam;

public interface PubnubPamAsyncGrantApiStateChannel {
    PubnubPamAsyncGrantApiStateCOptions channel(String channel);
    PubnubPamAsyncGrantApiStateCGOptions channelGroup(String channelGroup);
}
