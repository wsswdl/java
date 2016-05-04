package com.pubnub.api.endpoints.push;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubErrorBuilder;
import com.pubnub.api.PubNubException;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.models.consumer.push.PNPushListProvisionsResult;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

@Accessors(chain = true, fluent = true)
public class ListPushProvisions extends Endpoint<List<String>, PNPushListProvisionsResult> {

    @Setter private PNPushType pushType;
    @Setter private String deviceId;

    public ListPushProvisions(final PubNub pubnubInstance) {
        super(pubnubInstance);
    }

    @Override
    protected void validateParams() {
        // TODO
    }

    @Override
    protected final Call<List<String>> doWork(final Map<String, String> params) throws PubNubException {
        params.put("type", pushType.name().toLowerCase());
        PushService service = this.createRetrofit().create(PushService.class);
        return service.listChannelsForDevice(this.getPubnub().getConfiguration().getSubscribeKey(), deviceId, params);
    }

    @Override
    protected final PNPushListProvisionsResult createResponse(final Response<List<String>> input) throws PubNubException {
        if (input.body() == null) {
            throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
        }

        return PNPushListProvisionsResult.builder().channels(input.body()).build();
    }

    protected final int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected final int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected final PNOperationType getOperationType() {
        return PNOperationType.PNPushNotificationEnabledChannelsOperation;
    }

}
