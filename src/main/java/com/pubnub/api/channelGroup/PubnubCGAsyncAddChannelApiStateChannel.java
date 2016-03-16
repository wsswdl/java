package com.pubnub.api.channelGroup;

public interface PubnubCGAsyncAddChannelApiStateChannel {
    PubnubCGAsyncAddChannelEnd channel(String channel);
    PubnubCGAsyncAddChannelEnd channels(String[] channels);   
}
