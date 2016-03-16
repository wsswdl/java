package com.pubnub.domain;


public class StreamResult extends Result {
	StreamData data;

	public StreamData getData() {
		return data;
	}
	public StreamResult() {
		data = new StreamData();
	}
	
	public StreamResult(SubscribeResult result) {
		this.setClientRequest(result.getClientRequest());
		this.setCode(result.getCode());
		this.setConfig(result.getConfig());
		this.setConnectionId(result.getConnectionId());
		this.setHreq(result.getHreq());
		this.setOperation(result.getOperation());
		this.setPubnub(result.getPubnub());
		this.setServerResponse(result.getServerResponse());
		this.setType(result.getType());

		data = new StreamData();
		data.message = result.data.message;
		data.timetoken = result.data.timetoken;
	}
	
	public String toString() {
		System.out.println("to string " + data.message);
		String s = super.toString();
		s = s + data + "\n";
		return s;
		
	}
}