package com.pubnub.api;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

class PublishResult {
    int result = 0;
}

public class Subscribe {

    @Test
    public void shouldReceiveMessage() throws Exception {

        /*
        Pubnub pubnub = new Pubnub.Builder() ("demo", "demo");

        PublishResult pr = new PublishResult();

        pubnub.publish("demo", "demo", new Callback() {
            public void successCallback(String channel, Object message) {
                System.out.println(message);
                // pr.result = 1;
            }

            public void errorCallback(String channel, PubnubError error) {

            }
        });
        await().pollDelay(1, TimeUnit.MICROSECONDS).until(() -> pr.result == 1);
        */
    }

}
