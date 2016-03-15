package com.pubnub.interfaces;

import com.pubnub.domain.StreamResult;
import com.pubnub.domain.StreamStatus;

public interface PubnubStreamListenerInterface {
	public void streamStatus(StreamStatus status);
	public void streamResult(StreamResult result);
}
