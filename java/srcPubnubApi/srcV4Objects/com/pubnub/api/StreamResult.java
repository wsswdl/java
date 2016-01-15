package com.pubnub.api;


public class StreamResult extends Result {
	StreamData data;

	public StreamData getData() {
		return data;
	}
	public StreamResult() {
		data = new StreamData();
	}
	
	public StreamResult(SubscribeResult result) {
		data = new StreamData();
		data.message = result.data.message;
		data.timetoken = result.data.timetoken;
	}
	
	public String toString() {
		String s = super.toString();
		s = s + data + "\n";
		return s;
		
	}
}