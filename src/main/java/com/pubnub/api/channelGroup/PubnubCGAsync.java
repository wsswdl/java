package com.pubnub.api.channelGroup;

import com.pubnub.api.Pubnub;
import com.pubnub.callbacks.ChannelGroupChangeCallback;
import com.pubnub.callbacks.GroupAuditCallback;
import com.pubnub.callbacks.GroupChannelsAuditCallback;

public class PubnubCGAsync implements PubnubCGAsyncInterface{

    final Pubnub pubnub;
    
    ChannelGroupChangeCallback channelGroupChangeCallback;
    GroupAuditCallback groupAuditCallback;
    GroupChannelsAuditCallback groupChannelsAuditCallback;
    
    String channel;
    String[] channels;
    String channelGroup;
    
    PubnubCGAsync pns = this;


    public PubnubCGAsync(Pubnub pubnub) {
        this.pubnub = pubnub;
    }
    
    
    
    @Override
    public PubnubCGAsyncAddChannelInterface addChannel() {
        
        
        final PubnubCGAsyncAddChannelEnd apiEnd = new PubnubCGAsyncAddChannelEnd(){

            @Override
            public void add() {
                if (pns.channel != null) {
                    pubnub.channelGroupAddChannel(
                            pns.channelGroup, pns.channel, pns.channelGroupChangeCallback);
                } else if (pns.channels != null) {
                    pubnub.channelGroupAddChannel(
                            pns.channelGroup, pns.channels, pns.channelGroupChangeCallback);
                }
            }
            
        };

        final PubnubCGAsyncAddChannelApiStateChannel apiChannel =
                new PubnubCGAsyncAddChannelApiStateChannel() {

                    @Override
                    public PubnubCGAsyncAddChannelEnd channel(String channel) {
                        pns.channel = channel;
                        return apiEnd;
                    }

                    @Override
                    public PubnubCGAsyncAddChannelEnd channels(String[] channels) {
                        pns.channels = channels;
                        return apiEnd;
                    }
            
        };


        final PubnubCGAsyncAddChannelApiStateChannelGroup apiChannelGroup =
                new PubnubCGAsyncAddChannelApiStateChannelGroup(){

                    @Override
                    public PubnubCGAsyncAddChannelApiStateChannel channelGroup(String channelGroup) {
                        pns.channelGroup = channelGroup;
                        return apiChannel;
                    }
            
        };

        final PubnubCGAsyncAddChannelInterface apiAddChannel = new PubnubCGAsyncAddChannelInterface(){

            @Override
            public PubnubCGAsyncAddChannelApiStateChannelGroup callback(ChannelGroupChangeCallback callback) {
                pns.channelGroupChangeCallback = callback;
                return apiChannelGroup;
            }
            
        };
        return apiAddChannel;
    }
    
    
    @Override
    public PubnubCGAsyncRemoveChannelInterface removeChannel() {

        final PubnubCGAsyncRemoveChannelEnd apiEnd = new PubnubCGAsyncRemoveChannelEnd() {

            @Override
            public void remove() {
                if (pns.channel != null) {
                    pubnub.channelGroupRemoveChannel(channelGroup, channel, pns.channelGroupChangeCallback);
                } else if (pns.channels != null) {
                    pubnub.channelGroupRemoveChannel(channelGroup, channels, pns.channelGroupChangeCallback);
                }

            }
            
        };

        final PubnubCGAsyncRemoveChannelApiStateChannel apiChannel =
                new PubnubCGAsyncRemoveChannelApiStateChannel() {

                    @Override
                    public PubnubCGAsyncRemoveChannelEnd channel(String channel) {
                        pns.channel = channel;
                        return apiEnd;
                    }

                    @Override
                    public PubnubCGAsyncRemoveChannelEnd channels(String[] channels) {
                        pns.channels = channels;
                        return apiEnd;
                    }
            
        };

        final PubnubCGAsyncRemoveChannelApiStateChannelGroup apiChannelGroup =
                new PubnubCGAsyncRemoveChannelApiStateChannelGroup() {

                    @Override
                    public PubnubCGAsyncRemoveChannelApiStateChannel channelGroup(String channelGroup) {
                        pns.channelGroup = channelGroup;
                        return apiChannel;
                    }
            
        };

        final PubnubCGAsyncRemoveChannelInterface apiRemoveChannel = new PubnubCGAsyncRemoveChannelInterface(){

            @Override
            public PubnubCGAsyncRemoveChannelApiStateChannelGroup callback(ChannelGroupChangeCallback callback) {
                pns.channelGroupChangeCallback = callback;
                return apiChannelGroup;
            }
            
        };
        return apiRemoveChannel;
    }
    
    
    @Override
    public PubnubCGAsyncRemoveGroupInterface removeGroup() {

        final PubnubCGAsyncRemoveGroupEnd apiEnd = new PubnubCGAsyncRemoveGroupEnd() {

            @Override
            public void remove() {
                if (pns.channel != null) {
                    pubnub.channelGroupRemoveChannel(
                            pns.channelGroup, pns.channel, pns.channelGroupChangeCallback);
                } else if (pns.channels != null) {
                    pubnub.channelGroupRemoveChannel(
                            pns.channelGroup, pns.channels, pns.channelGroupChangeCallback);   
                }
            }
            
        };

        final PubnubCGAsyncRemoveGroupApiStateChannelGroup apiChannelGroup =
                new PubnubCGAsyncRemoveGroupApiStateChannelGroup() {

                    @Override
                    public PubnubCGAsyncRemoveGroupEnd channelGroup(String channelGroup) {
                        pns.channelGroup = channelGroup;
                        return apiEnd;
                    }
            
        };

        final PubnubCGAsyncRemoveGroupInterface apiRemoveGroup =
                new PubnubCGAsyncRemoveGroupInterface(){

                    @Override
                    public PubnubCGAsyncRemoveGroupApiStateChannelGroup callback(ChannelGroupChangeCallback callback) {
                        pns.channelGroupChangeCallback = callback;
                        return apiChannelGroup;
                    }
            
        };
        return apiRemoveGroup;
    }
    
    
    @Override
    public PubnubCGAsyncListChannelsInterface listChannels() {

        final PubnubCGAsyncListChannelsEnd apiEnd = new PubnubCGAsyncListChannelsEnd() {

            @Override
            public void list() {
                pubnub.channelGroupListChannels(pns.channelGroup, pns.groupChannelsAuditCallback);
            }
            
        };

        final PubnubCGAsyncListChannelsApiStateChannelGroup apiChannelGroup =
                new PubnubCGAsyncListChannelsApiStateChannelGroup() {

                    @Override
                    public PubnubCGAsyncListChannelsEnd channelGroup(String channelGroup) {
                        pns.channelGroup = channelGroup;
                        return apiEnd;
                    }
            
        };

        final PubnubCGAsyncListChannelsInterface apiListChannels =
                new PubnubCGAsyncListChannelsInterface() {

                    @Override
                    public PubnubCGAsyncListChannelsApiStateChannelGroup callback(
                            GroupChannelsAuditCallback callback) {
                        pns.groupChannelsAuditCallback = callback;
                        return null;
                    }
            
        };
        return apiListChannels;
    }
    
    PubnubCGAsyncListGroupsEnd apiListGroupsEnd = 
            new PubnubCGAsyncListGroupsEnd() {

                @Override
                public void list() {
                    pubnub.channelGroupListGroups(pns.groupAuditCallback);
                }
        
    };
    
    PubnubCGAsyncListGroupsInterface apiListGroups = 
            new PubnubCGAsyncListGroupsInterface() {

                @Override
                public PubnubCGAsyncListGroupsEnd callback(GroupAuditCallback callback) {
                    pns.groupAuditCallback = callback;
                    return apiListGroupsEnd;
                }
        
    };
    
    
    @Override
    public PubnubCGAsyncListGroupsInterface listGroups() {
        return apiListGroups;
    }
}
