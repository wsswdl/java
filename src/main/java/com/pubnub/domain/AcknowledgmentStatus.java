package com.pubnub.domain;

public class AcknowledgmentStatus extends ErrorStatus {
    public AcknowledgmentStatus(Result result) {
        super(result);
    }

    AcknowledgmentStatus() {
        super();
    }
    
    AcknowledgmentStatus(ErrorStatus s) {
        super();
    }
    
}
