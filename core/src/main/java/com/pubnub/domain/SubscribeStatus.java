package com.pubnub.domain;

import com.pubnub.interfaces.StatusInterface;

public class SubscribeStatus extends SubscribeResult implements StatusInterface {

	Status status;
	
	public SubscribeStatus(){
		status = new Status();
	}
	
	public SubscribeStatus(SubscribeResult result) {
		this();

		this.setClientRequest(result.getClientRequest());
		this.setCode(result.getCode());
		this.setConfig(result.getConfig());
		this.setConnectionId(result.getConnectionId());
		this.setHreq(result.getHreq());
		this.setOperation(result.getOperation());
		this.setPubnub(result.getPubnub());
		this.setServerResponse(result.getServerResponse());
		this.setType(result.getType());
	}
	
	@Override
	public StatusCategory getCategory() {
		return status.category;
	}

	@Override
	public boolean isError() {
		return status.isError;
	}

	@Override
	public boolean wasAutoRetried() {
		return status.wasAutoRetried;
	}

	@Override
	public void retry() {

	}
	
	public String toString() {
		String s = super.toString();
		s = s + status + "\n";
		return s;	
	}

	
}
