package com.pubnub.api.unsubscribe;

public interface PubnubUnsubscribeAsyncChannel {
    PubnubAsyncUnsubscribeEnd allChannels();
    PubnubAsyncUnsubscribeEnd channels(String[] channels);
    PubnubAsyncUnsubscribeEnd channel(String channel);
    PubnubAsyncUnsubscribeEnd allChannelGroups();
    PubnubAsyncUnsubscribeEnd channelGroup(String channelGroup);
    PubnubAsyncUnsubscribeEnd channelGroups(String[] channelGroups); 
}
