package com.pubnub.api.endpoints.access;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.pubnub.api.core.Pubnub;
import com.pubnub.api.core.PubnubException;
import com.pubnub.api.endpoints.TestHarness;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GrantEndpointTest extends TestHarness {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

    private Grant partialGrant;
    private Pubnub pubnub;

    @Before
    public void beforeEach() throws IOException {

        pubnub = this.createPubNubInstance(8080);
        partialGrant = pubnub.grant();

        pubnub.getConfiguration().setSecretKey("secretKey");

    }

    @Test
    public void testReadMultipleChannelSync() throws PubnubException {

        stubFor(get(urlPathEqualTo("/v1/auth/grant/sub-key/mySubscribeKey"))
                .willReturn(aResponse().withBody("{\"message\":\"Success\",\"payload\":{\"level\":\"user\",\"subscribe_key\":\"sub-c-82ab2196-b64f-11e5-8622-0619f8945a4f\",\"ttl\":0,\"channels\":{\"ch1\":{\"auths\":{\"max\":{\"r\":0,\"w\":1,\"m\":0}}},\"ch2\":{\"auths\":{\"max\":{\"r\":0,\"w\":1,\"m\":0}}}}},\"service\":\"Access Manager\",\"status\":200}")));

        // ?w=1&timestamp=1461190439&r=0&pnsdk=Java/version%20%3D%204.0.0-SNAPSHOT&m=0&uuid=08aa7b43-5ab6-404a-a75f-0d2e63c960de&ttl=0&channel=ch1,ch2&signature=IkfPZ9raFzVvHJ-NxsTqiAg0oU0xG1aiBpT2E27oXD0%3D%0A&auth=key1

        partialGrant
                .channels(Arrays.asList("ch1")).authKeys(Arrays.asList("key1"))
                .read(false).write(true).manage(false)
                .sync();


    }

    @Test
    public void testReadSingleChannelSync() throws PubnubException {

        stubFor(get(urlPathEqualTo("/v1/auth/grant/sub-key/mySubscribeKey"))
                .willReturn(aResponse().withBody("{\"message\":\"Success\",\"payload\":{\"level\":\"user\",\"subscribe_key\":\"sub-c-82ab2196-b64f-11e5-8622-0619f8945a4f\",\"ttl\":0,\"channel\":\"ch1\",\"auths\":{\"key1\":{\"r\":0,\"w\":1,\"m\":0}}},\"service\":\"Access Manager\",\"status\":200}")));

        // ?w=1&timestamp=1461190439&r=0&pnsdk=Java/version%20%3D%204.0.0-SNAPSHOT&m=0&uuid=08aa7b43-5ab6-404a-a75f-0d2e63c960de&ttl=0&channel=ch1,ch2&signature=IkfPZ9raFzVvHJ-NxsTqiAg0oU0xG1aiBpT2E27oXD0%3D%0A&auth=key1

        partialGrant
                .channels(Arrays.asList("ch1")).authKeys(Arrays.asList("key1"))
                .read(true).write(true).manage(true)
                .sync();


    }

    @Test
    public void testSingleChannelMultipleKeysSync() throws PubnubException {

        stubFor(get(urlPathEqualTo("/v1/auth/grant/sub-key/mySubscribeKey"))
                .willReturn(aResponse().withBody("{\"message\":\"Success\",\"payload\":{\"level\":\"user\",\"subscribe_key\":\"sub-c-82ab2196-b64f-11e5-8622-0619f8945a4f\",\"ttl\":0,\"channel\":\"ch1\",\"auths\":{\"key1\":{\"r\":0,\"w\":1,\"m\":0},\"key2\":{\"r\":0,\"w\":1,\"m\":0}}},\"service\":\"Access Manager\",\"status\":200}")));

        // ?w=1&timestamp=1461190439&r=0&pnsdk=Java/version%20%3D%204.0.0-SNAPSHOT&m=0&uuid=08aa7b43-5ab6-404a-a75f-0d2e63c960de&ttl=0&channel=ch1,ch2&signature=IkfPZ9raFzVvHJ-NxsTqiAg0oU0xG1aiBpT2E27oXD0%3D%0A&auth=key1

        partialGrant
                .channels(Arrays.asList("ch1")).authKeys(Arrays.asList("key1"))
                .read(true).write(true).manage(true)
                .sync();

    }

}
