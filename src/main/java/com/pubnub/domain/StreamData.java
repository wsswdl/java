package com.pubnub.domain;

import lombok.Data;

@Data
public class StreamData extends SubscribeData {
    public String[] channels;
    public String channel;
}
