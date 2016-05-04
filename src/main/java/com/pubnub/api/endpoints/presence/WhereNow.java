package com.pubnub.api.endpoints.presence;

import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubErrorBuilder;
import com.pubnub.api.PubNubException;
import com.pubnub.api.enums.PNOperationType;
import com.pubnub.api.models.consumer.presence.PNWhereNowResult;
import com.pubnub.api.models.server.Envelope;
import com.pubnub.api.models.server.presence.WhereNowPayload;
import com.pubnub.api.endpoints.Endpoint;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.Map;

@Accessors(chain = true, fluent = true)
public class WhereNow extends Endpoint<Envelope<WhereNowPayload>, PNWhereNowResult> {

    @Setter private String uuid;

    public WhereNow(final PubNub pubnubInstance) {
        super(pubnubInstance);
    }

    @Override
    protected void validateParams() {

    }

    @Override
    protected final Call<Envelope<WhereNowPayload>> doWork(final Map<String, String> params) {
        PresenceService service = this.createRetrofit().create(PresenceService.class);

        String requestUUID;

        if (this.uuid != null) {
            requestUUID = this.uuid;
        } else {
            requestUUID = this.getPubnub().getConfiguration().getUuid();
        }

        return service.whereNow(this.getPubnub().getConfiguration().getSubscribeKey(), requestUUID, params);
    }

    @Override
    protected final PNWhereNowResult createResponse(final Response<Envelope<WhereNowPayload>> input) throws PubNubException {
        if (input.body() == null || input.body().getPayload() == null) {
            throw PubNubException.builder().pubnubError(PubNubErrorBuilder.PNERROBJ_PARSING_ERROR).build();
        }

        return PNWhereNowResult.builder()
            .channels(input.body().getPayload().getChannels())
            .build();
    }

    protected final int getConnectTimeout() {
        return this.getPubnub().getConfiguration().getConnectTimeout();
    }

    protected final int getRequestTimeout() {
        return this.getPubnub().getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected final PNOperationType getOperationType() {
        return PNOperationType.PNWhereNowOperation;
    }

}
