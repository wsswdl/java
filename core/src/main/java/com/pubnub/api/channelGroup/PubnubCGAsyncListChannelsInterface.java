package com.pubnub.api.channelGroup;

import com.pubnub.callbacks.GroupChannelsAuditCallback;

public interface PubnubCGAsyncListChannelsInterface {
    PubnubCGAsyncListChannelsApiStateChannelGroup callback(GroupChannelsAuditCallback callback);
}
