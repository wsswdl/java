package com.pubnub.api.hereNow;

public interface PubnubHereNowSyncInterface {
    PubnubHereNowSyncApiStateOptions callback(String channel);
    PubnubHereNowSyncApiStateOptions channelGroup(String channelGroup);
}
