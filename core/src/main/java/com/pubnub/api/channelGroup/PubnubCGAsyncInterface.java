package com.pubnub.api.channelGroup;

public interface PubnubCGAsyncInterface {
    PubnubCGAsyncAddChannelInterface  addChannel();
    PubnubCGAsyncRemoveChannelInterface  removeChannel();
    PubnubCGAsyncRemoveGroupInterface  removeGroup();
    PubnubCGAsyncListChannelsInterface listChannels();
    PubnubCGAsyncListGroupsInterface  listGroups();
}
