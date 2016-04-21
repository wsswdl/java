package com.pubnub.api;

import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.core.PnConfiguration;
import com.pubnub.api.core.Pubnub;
import com.pubnub.api.core.PubnubException;
import com.pubnub.api.core.models.consumer_facing.PNAccessManagerGrantResult;
import com.pubnub.api.core.models.consumer_facing.PNMessageResult;
import com.pubnub.api.core.models.consumer_facing.PNPresenceEventResult;
import com.pubnub.api.core.models.consumer_facing.PNStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class max_test {

    public static void main(String[] args) throws InterruptedException {
        PnConfiguration pnConfiguration = new PnConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-82ab2196-b64f-11e5-8622-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-8beb3658-0dfd-4032-8f4b-9c6b9ca4d803");
        pnConfiguration.setSecretKey("sec-c-NDJkOWM2ZWItNzBhMS00YzllLWFlZjAtNGJlMjVkZjZlNzMy");
        // pnConfiguration.setCipherKey("testCipher");


        Pubnub pubnub = new Pubnub(pnConfiguration);

        //pubnub.publish()
        //        .channel("coolChannel").usePOST(false).shouldStore(true)
        //        .message(Arrays.asList("m1", "m2")).sync();

        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(Pubnub pubnub, PNStatus status) {
                int moose = 10;
            }

            @Override
            public void message(Pubnub pubnub, PNMessageResult message) {
                log.debug("result ->>", message.toString());
            }

            @Override
            public void presence(Pubnub pubnub, PNPresenceEventResult presence) {
                log.debug("presence ->>", presence.toString());
            }
        });

        Map<String, Object> state = new HashMap<>();
        state.put("max", "moose");

        //pubnub.subscribe().channels(Arrays.asList("coolChannel")).withPresence().execute();
        //pubnub.setPresenceState().channel("max-ch1").state(state).build().async(new PNCallback<PNSetStateResult>() {
        //    @Override
        //    public void onResponse(PNSetStateResult result, PNErrorStatus status) {
        //        int moose = 10;
        //    }
        //});

        try {
            PNAccessManagerGrantResult moose = pubnub.grant().authKeys(Arrays.asList("key1"))
                    .channelGroups(Arrays.asList("cg1", "cg2"))
                    .ttl(0).write(true)
                    .sync();
            int max = 11;
        } catch (PubnubException e) {
            e.printStackTrace();
        }


        // PNHistoryResult moose = pubnub.history().channel("coolChannelENC2").build().sync();


        int max = 10;

        try {
            Object moose = pubnub.audit()
                    .authKeys(Arrays.asList("max"))
                    .channel("ch1")
                    .sync();
        } catch (PubnubException e) {
            e.printStackTrace();
        }

        //Thread.sleep(5000);

        //pubnub.unsubscribe().channel("max-ch1").execute();

    }

}
