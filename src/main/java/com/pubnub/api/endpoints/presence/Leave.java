package com.pubnub.api.endpoints.presence;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.PubNubUtil;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.endpoints.Endpoint;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Accessors(chain = true, fluent = true)
public class Leave extends Endpoint<Envelope, Boolean> {

    public Leave(final PubNub pubnub) {
        super(pubnub);
        channels = new ArrayList<>();
        channelGroups = new ArrayList<>();
    }

    @Setter private List<String> channels;
    @Setter private List<String> channelGroups;

    @Override
    protected void validateParams() {
        // TODO
    }

    @Override
    protected final Call<Envelope> doWork(final Map<String, String> params) {
        String channelCSV;
        PresenceService service = this.createRetrofit().create(PresenceService.class);

        if (channelGroups.size() > 0) {
            params.put("channel-group", PubNubUtil.joinString(channelGroups, ","));
        }

        if (channels.size() > 0) {
            channelCSV = PubNubUtil.joinString(channels, ",");
        } else {
            channelCSV = ",";
        }

        return service.leave(this.getPubnub().getConfiguration().getSubscribeKey(), channelCSV, params);
    }

    @Override
    protected final Boolean createResponse(final Response<Envelope> input) throws PubNubException {
        return true;
    }

    protected final int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected final int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected final PNOperationType getOperationType() {
        return PNOperationType.PNUnsubscribeOperation;
    }

    @Override
    protected final List<String> getAffectedChannels() {
        return channels;
    }

    @Override
    protected final List<String> getAffectedChannelGroups() {
        return channelGroups;
    }

}
