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
import com.pubnub.api.models.consumer.presence.PNSetStateResult;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.managers.SubscriptionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Accessors(chain = true, fluent = true)
public class SetState extends Endpoint<Envelope<Map<String, Object>>, PNSetStateResult> {

    @Getter(AccessLevel.NONE)
    private SubscriptionManager subscriptionManager;

    @Setter private List<String> channels;
    @Setter private List<String> channelGroups;
    @Setter private Object state;

    public SetState(final PubNub pubnub, final SubscriptionManager subscriptionManagerInstance) {
        super(pubnub);
        this.subscriptionManager = subscriptionManagerInstance;
        channels = new ArrayList<>();
        channelGroups = new ArrayList<>();
    }

    @Override
    protected void validateParams() {
        // TODO
    }

    @Override
    protected final Call<Envelope<Map<String, Object>>> doWork(final Map<String, String> params) throws PubNubException {
        ObjectWriter ow = new ObjectMapper().writer();
        String stringifiedState;


        subscriptionManager.adaptStateBuilder(channels, channelGroups, state);

        PresenceService service = this.createRetrofit().create(PresenceService.class);

        if (channelGroups.size() > 0) {
            params.put("channel-group", PubNubUtil.joinString(channelGroups, ","));
        }

        try {
            stringifiedState = ow.writeValueAsString(state);
        } catch (JsonProcessingException e) {
            throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_INVALID_ARGUMENTS).errormsg(e.getMessage()).build();
        }

        stringifiedState = PubNubUtil.urlEncode(stringifiedState);
        params.put("state", stringifiedState);

        String channelCSV;

        if (channels.size() > 0) {
            channelCSV =  PubNubUtil.joinString(channels, ",");
        } else {
            channelCSV  = ",";
        }

        return service.setState(this.getPubnub().getConfiguration().getSubscribeKey(), channelCSV, this.getPubnub().getConfiguration().getUuid(), params);
    }

    @Override
    protected PNSetStateResult createResponse(Response<Envelope<Map<String, Object>>> input) throws PubNubException {

        if (input.body() == null) {
            throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
        }

        PNSetStateResult.PNSetStateResultBuilder pnSetStateResult = PNSetStateResult.builder()
                .state(input.body().getPayload());

        return pnSetStateResult.build();
    }

    protected int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNSetStateOperation;
    }

}
