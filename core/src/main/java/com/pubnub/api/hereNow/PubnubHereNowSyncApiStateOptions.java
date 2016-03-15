package com.pubnub.api.hereNow;

public interface PubnubHereNowSyncApiStateOptions extends PubnubHereNowSyncEnd {
    PubnubHereNowSyncApiStateOptions state(boolean state);
    PubnubHereNowSyncApiStateOptions uuids(boolean uuids);
}
