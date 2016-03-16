package com.pubnub.domain;

import lombok.Data;

@Data
public class ErrorStatus extends Status {

    ErrorData errorData = new ErrorData();
    
    @Override
    public void retry() {
        this.getPubnub().sendNonSubscribeRequest(this.getHreq());
    }

    ErrorStatus() {
        super();
        this.setType(ResultType.STATUS);
    }

    public ErrorStatus(Result result) {
        super(result);
        errorData = new ErrorData();
        this.isError = true;
        this.setCode(result.getCode());
        this.setOperation(result.getOperation());
        this.setConfig(result.getConfig());
        this.setConnectionId(result.getConnectionId());
        this.setClientRequest(result.getClientRequest());
        this.setServerResponse(result.getServerResponse());
        this.setHreq(result.getHreq());
        this.setPubnub(result.getPubnub());
        this.setType(ResultType.STATUS);
    }
    
    ErrorStatus(Status status) {
        super();
        this.wasAutoRetried = status.wasAutoRetried;
        this.isError = true;
        this.category = status.category;
        errorData = new ErrorData();
    }
}
