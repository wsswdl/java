package com.pubnub.api.channelGroup;

import com.pubnub.callbacks.GroupAuditCallback;

public interface PubnubCGAsyncListGroupsInterface {
    PubnubCGAsyncListGroupsEnd callback(GroupAuditCallback callback);
}
