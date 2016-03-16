package com.pubnub.interfaces;

import com.pubnub.domain.StatusCategory;

public interface StatusInterface {
	public StatusCategory getCategory();
	public boolean isError();
	public boolean wasAutoRetried();
	public void retry();
}
