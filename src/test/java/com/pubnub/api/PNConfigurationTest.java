package com.pubnub.api;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Max on 5/3/16.
 */
public class PNConfigurationTest {


    @Test
    public void setPresenceTimeoutWithCustomIntervalTest() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPresenceTimeoutWithCustomInterval(150, 10);

        Assert.assertEquals(pnConfiguration.getPresenceTimeout(), 150);
        Assert.assertEquals(pnConfiguration.getHeartbeatInterval(), 10);

    }

}
