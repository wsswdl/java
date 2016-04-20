package com.pubnub.api.endpoints;

import com.pubnub.api.core.PnConfiguration;
import com.pubnub.api.core.Pubnub;

public class TestHarness {

    protected Pubnub createPubNubInstance(int port) {
        PnConfiguration pnConfiguration = createBaseConfiguration(port);
        return new Pubnub(pnConfiguration);
    }

    protected Pubnub createPubNubInstance(PnConfiguration configuration) {
        return new Pubnub(configuration);
    }

    protected PnConfiguration createBaseConfiguration(int port) {
        PnConfiguration pnConfiguration = new PnConfiguration();
        pnConfiguration.setOrigin("localhost" + ":" + port);
        pnConfiguration.setSecure(false);
        pnConfiguration.setSubscribeKey("mySubscribeKey");
        pnConfiguration.setPublishKey("myPublishKey");
        pnConfiguration.setUuid("myUUID");

        return pnConfiguration;
    }

}
