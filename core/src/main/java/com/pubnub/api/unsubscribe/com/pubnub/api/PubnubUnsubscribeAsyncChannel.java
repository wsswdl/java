package com.pubnub.api.unsubscribe.com.pubnub.api;

import com.pubnub.api.unsubscribe.com.pubnub.api.PubnubAsyncUnsubscribeEnd;

public interface PubnubUnsubscribeAsyncChannel {
    PubnubAsyncUnsubscribeEnd allChannels();
    PubnubAsyncUnsubscribeEnd channels(String[] channels);
    PubnubAsyncUnsubscribeEnd channel(String channel);
    PubnubAsyncUnsubscribeEnd allChannelGroups();
    PubnubAsyncUnsubscribeEnd channelGroup(String channelGroup);
    PubnubAsyncUnsubscribeEnd channelGroups(String[] channelGroups); 
}
