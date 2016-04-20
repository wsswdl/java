package com.pubnub.api.endpoints.access;

import com.pubnub.api.core.Pubnub;
import com.pubnub.api.core.PubnubException;
import com.pubnub.api.core.PubnubUtil;
import com.pubnub.api.core.enums.PNOperationType;
import com.pubnub.api.core.models.Envelope;
import com.pubnub.api.core.models.consumer_facing.PNAccessManagerGrantData;
import com.pubnub.api.core.models.consumer_facing.PNAccessManagerGrantResult;
import com.pubnub.api.core.models.server_responses.GrantResponse;
import com.pubnub.api.endpoints.Endpoint;
import lombok.Setter;
import lombok.experimental.Accessors;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Accessors(chain = true, fluent = true)
public class Grant extends Endpoint<Envelope<GrantResponse>, PNAccessManagerGrantResult> {

    @Setter private boolean read;
    @Setter private boolean write;
    @Setter private boolean manage;
    @Setter private int ttl;


    @Setter private List<String> authKeys;
    @Setter private List<String> channels;
    @Setter private List<String> channelGroups;

    public Grant(Pubnub pubnub) {
        super(pubnub);
        channels = new ArrayList<>();
        channelGroups = new ArrayList<>();
    }

    @Override
    protected boolean validateParams() {
        return true;
    }

    @Override
    protected Call<Envelope<GrantResponse>> doWork(Map<String, String> queryParams) throws PubnubException {
        String signature;
        int timestamp = (int) ((new Date().getTime()) / 1000);


        String r = (read) ? "1" : "0";
        String w = (write) ? "1" : "0";
        String m = (manage) ? "1" : "0";

        String signInput = this.pubnub.getConfiguration().getSubscribeKey() + "\n"
                + this.pubnub.getConfiguration().getPublishKey() + "\n"
                + "grant" + "\n";

        queryParams.put("timestamp", String.valueOf(timestamp));

        if (channels.size() > 0) {
            queryParams.put("channel", PubnubUtil.joinString(channels, ","));
        }

        if (channelGroups.size() > 0) {
            queryParams.put("channel-group", PubnubUtil.joinString(channelGroups, ","));
        }

        if (authKeys.size() > 0) {
            queryParams.put("auth", PubnubUtil.joinString(authKeys, ","));
        }

        if (ttl >= -1) {
            queryParams.put("ttl", String.valueOf(ttl));
        }

        queryParams.put("r", r);
        queryParams.put("w", w);
        queryParams.put("m", m);

        signInput += PubnubUtil.preparePamArguments(queryParams);

        signature = signSHA256(this.pubnub.getConfiguration().getSecretKey(), signInput);

        queryParams.put("signature", signature);

        AccessManagerService service = this.createRetrofit().create(AccessManagerService.class);
        return service.grant(pubnub.getConfiguration().getSubscribeKey(), queryParams);
    }

    @Override
    protected PNAccessManagerGrantResult createResponse(Response<Envelope<GrantResponse>> input) throws PubnubException {
        PNAccessManagerGrantResult pnAccessManagerGrantResult = new PNAccessManagerGrantResult();


        GrantResponse grantResponse = input.body().getPayload();

        PNAccessManagerGrantData pnAccessManagerGrantData = PNAccessManagerGrantData.builder()
                .level(grantResponse.getLevel())
                .subscribeKey(grantResponse.getSubscribeKey())
                .ttl(grantResponse.getTtl())
                .build();

        pnAccessManagerGrantResult.setData(pnAccessManagerGrantData);

        System.out.println(input.raw().request().url().toString());


        return pnAccessManagerGrantResult;
    }

    protected int getConnectTimeout() {
        return pubnub.getConfiguration().getConnectTimeout();
    }

    protected int getRequestTimeout() {
        return pubnub.getConfiguration().getNonSubscribeRequestTimeout();
    }

    @Override
    protected PNOperationType getOperationType() {
        return PNOperationType.PNAccessManagerGrant;
    }

}
