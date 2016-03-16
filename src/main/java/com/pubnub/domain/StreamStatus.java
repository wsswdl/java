package com.pubnub.domain;

import com.pubnub.interfaces.StatusInterface;
import lombok.Data;

@Data
public class StreamStatus extends StreamResult implements StatusInterface {

	private boolean wasAutoRetried;
	private boolean error;
	private StatusCategory category;
    
	public StreamStatus(StreamResult result) {
		this();

		this.setClientRequest(result.getClientRequest());
		this.setCode(result.getCode());
		this.setConfig(result.getConfig());
		this.setConnectionId(result.getConnectionId());
		this.setHreq(result.getHreq());
		this.setOperation(result.getOperation());
		this.setPubnub(result.getPubnub());
		this.setServerResponse(result.getServerResponse());
		this.setType(ResultType.STATUS);

	    data = new StreamData();
	    data.message = result.data.message;
	    data.timetoken = result.data.timetoken;
	}
	
	public StreamStatus() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public StatusCategory getCategory() {
		return category;
	}

	@Override
	public boolean wasAutoRetried() {
		return wasAutoRetried;
	}

	@Override
	public void retry() {

	}

}
