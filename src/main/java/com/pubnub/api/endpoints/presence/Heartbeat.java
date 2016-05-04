package com.pubnub.api.endpoints.presence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubErrorBuilder;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.endpoints.Endpoint;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

@Accessors(chain = true, fluent = true)
public class Heartbeat extends Endpoint<Envelope, Boolean> {

    @Setter private Object state;
    @Setter private List<String> channels;
    @Setter private List<String> channelGroups;

    public Heartbeat(PubNub pubnub) {
        super(pubnub);
    }

    @Override
    protected void validateParams() {
        // TODO
    }

    @Override
    protected Call<Envelope> doWork(Map<String, String> params) throws PubNubException {
        ObjectWriter ow = new ObjectMapper().writer();

        params.put("heartbeat", String.valueOf(this.getPubnub().getConfiguration().getPresenceTimeout()));

        if (channelGroups != null && channelGroups.size() > 0) {
            params.put("channel-group", PubNubUtil.joinString(channelGroups, ","));
        }

        String channelsCSV;

        if (channels != null && channels.size() > 0) {
            channelsCSV = PubNubUtil.joinString(channels, ",");
        } else {
            channelsCSV = ",";
        }

        if (state != null) {
            String stringifiedState;

            try {
                stringifiedState = ow.writeValueAsString(state);
            } catch (JsonProcessingException e) {
                throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).errormsg(e.getMessage()).build();
            }

            stringifiedState = PubNubUtil.urlEncode(stringifiedState);
            params.put("state", stringifiedState);
        }

        PresenceService service = this.createRetrofit().create(PresenceService.class);
        return service.heartbeat(this.getPubnub().getConfiguration().getSubscribeKey(), channelsCSV, params);
    }

    @Override
    protected Boolean createResponse(Response<Envelope> input) throws PubNubException {
        return true;
    }

    protected int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNHeartbeatOperation;
    }

}
