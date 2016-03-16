package com.pubnub.domain;

import lombok.Data;

@Data
public class TimeResult extends Result {

    TimeData data = new TimeData();
    
    public TimeResult(Result result) {
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
}
