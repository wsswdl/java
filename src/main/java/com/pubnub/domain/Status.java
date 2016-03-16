package com.pubnub.domain;

import com.pubnub.interfaces.StatusInterface;
import lombok.Data;

@Data
public class  Status extends Result implements StatusInterface {
	boolean wasAutoRetried;
	boolean isError;
	StatusCategory category;
	
	public String toString() {
	    String s = super.toString();
		s = s + "Was Auto Retried ? : " + wasAutoRetried + "\n"; 
		s = s + "Is Error ? : " + isError + "\n";
		s = s + "Category : " + category + "\n";
		return s;
	}

    @Override
    public StatusCategory getCategory() {
        return category;
    }

    @Override
    public boolean isError() {
        return isError;
    }

    @Override
    public boolean wasAutoRetried() {
        return wasAutoRetried;
    }
    Status() {
        super();
        this.setType(ResultType.STATUS);
    }
    
    Status(Result result) {
        this.setType(ResultType.STATUS);
        this.setCode(result.getCode());
        this.setOperation(result.getOperation());
        this.setConfig(result.getConfig());
        this.setConnectionId(result.getConnectionId());
        this.setClientRequest(result.getClientRequest());
        this.setServerResponse(result.getServerResponse());
    }

    @Override
    public void retry() {

    }

}
