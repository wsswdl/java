package com.pubnub.api.publish;

import com.pubnub.callbacks.PublishCallback;

public interface PubnubPublishAsyncInterface {
    PubnubPublishAsyncApiStateChannel callback(PublishCallback callback);
}
