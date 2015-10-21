package com.pubnub.api;

public class Result {
	protected ResultType type;
	protected int statusCode;
	protected OperationType operation;
	protected boolean TLSEnabled;
	protected String uuid;
	protected String authKey;
	protected String origin;
	protected HttpRequest hreq;
	protected PubnubCore pubnub;
	
	public PubnubCore getPubnub() {
		return pubnub;
	}
	public void setPubnub(PubnubCore pubnub) {
		this.pubnub = pubnub;
	}
	public HttpRequest getHreq() {
		return hreq;
	}
	public void setHreq(HttpRequest hreq) {
		this.hreq = hreq;
	}
	public ResultType getType() {
		return type;
	}
	public void setType(ResultType type) {
		this.type = type;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public OperationType getOperation() {
		return operation;
	}
	public void setOperation(OperationType operation) {
		this.operation = operation;
	}
	public boolean isTLSEnabled() {
		return TLSEnabled;
	}
	public void setTLSEnabled(boolean tLSEnabled) {
		TLSEnabled = tLSEnabled;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	// NSURLRequest
	
	public String toString() {
		String s = "";
		s = s + "Status Code: " + statusCode + "\n";
		s = s + "Origin: " + origin + "\n";
		s = s + "TLS: " + TLSEnabled + "\n";
		s = s + "Result Type: " + type + "\n";
		s = s + "Operation Type: " + operation + "\n";
		s = s + "Auth Key: " + authKey + "\n";
		s = s + "UUID: " + uuid + "\n";
		
		return s;
	}
}
