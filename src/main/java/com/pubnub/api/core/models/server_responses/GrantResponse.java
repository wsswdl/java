package com.pubnub.api.core.models.server_responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pubnub.api.core.models.consumer_facing.PNAccessManagerKeyData;
import com.pubnub.api.core.models.consumer_facing.PNAccessManagerKeysData;
import lombok.Getter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class GrantResponse {

    String level;
    int ttl;

    @JsonProperty("subscribe_key")
    String subscribeKey;

    String channel;

    @JsonProperty("auths")
    Map<String, PNAccessManagerKeyData> authKeys;

    Map<String, PNAccessManagerKeysData> channels;

    @JsonProperty("channel-groups")
    // server returns string if one channel groups and a full blown map when multiple.
    Object channelGroups;

}
