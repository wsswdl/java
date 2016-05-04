package com.pubnub.api.endpoints.push;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubErrorBuilder;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.Endpoint;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.enums.PNPushType;
import com.pubnub.api.models.consumer.push.PNPushRemoveAllChannelsResult;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

@Accessors(chain = true, fluent = true)
public class RemoveAllPushChannelsForDevice extends Endpoint<List<Object>, PNPushRemoveAllChannelsResult> {

    @Setter private PNPushType pushType;
    @Setter private String deviceId;

    public RemoveAllPushChannelsForDevice(final PubNub pubnubInstance) {
        super(pubnubInstance);
    }

    @Override
    protected final void validateParams() {
        // TODO
    }


    @Override
    protected final Call<List<Object>> doWork(final Map<String, String> params) throws PubNubException {
        params.put("type", pushType.name().toLowerCase());

        PushService service = this.createRetrofit().create(PushService.class);

        return service.removeAllChannelsForDevice(this.getPubnub().getConfiguration().getSubscribeKey(), deviceId, params);

    }

    @Override
    protected final PNPushRemoveAllChannelsResult createResponse(final Response<List<Object>> input) throws PubNubException {
        if (input.body() == null) {
            throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
        }

        return PNPushRemoveAllChannelsResult.builder().build();
    }

    protected final int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected final int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected final PNOperationType getOperationType() {
        return null; // TODO PNOperationType.PNPushNotificationModifiedChannelsOperations;
    }

}
