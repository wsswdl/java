package com.pubnub.api.publish;

import org.json.JSONObject;

public interface PubnubPublishSyncApiStateOptions extends PubnubPublishSyncEnd {
    PubnubPublishAsyncApiStateOptions storeInHistory(boolean storeInHistory);
    PubnubPublishAsyncApiStateOptions metadata(JSONObject metadata);
}
