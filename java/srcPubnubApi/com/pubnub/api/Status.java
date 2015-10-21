package com.pubnub.api;

public class Status extends Result {
	
	public Status() {
		super();
	}
	public Status(Result result) {
		this.authKey = result.authKey;
		this.operation = result.operation;
		this.origin = result.origin;
		this.statusCode = result.statusCode;
		this.uuid = result.uuid;
		this.type = result.type;
		this.hreq = result.hreq;
		this.pubnub = result.pubnub;
	}
	
	public void retry() {
		this.pubnub.sendNonSubscribeRequest(hreq);
	}
}
