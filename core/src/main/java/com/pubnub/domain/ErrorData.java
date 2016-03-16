package com.pubnub.domain;

import com.pubnub.api.PubnubUtil;
import lombok.Data;

@Data
public class ErrorData {

    String[] channels;
    public String[] getChannels() {
        return channels;
    }

    String[] channelGroups;
    public String[] getChannelGroups() {
        return channelGroups;
    }

    String information;
    public String getInformation() {
        return information;
    }
    
    public String toString()    {
        String s = "";
        s += "Channels: " + PubnubUtil.joinString(channels, ",") + "\n";
        s += "Channel Groups: " + PubnubUtil.joinString(channelGroups, ",") + "\n";
        s += "Information: " + information + "\n";
        return s;
    }

}
