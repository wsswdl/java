package com.pubnub.domain;

import lombok.Data;

@Data
public class HistoryResult extends Result {
    private HistoryData data;

    public HistoryResult(){
        this.setType(ResultType.RESULT);
        this.data = new HistoryData();
    }
    
    public HistoryData getData() {
        return data;
    }
    
    public String toString() {
        String s = super.toString();
        s = s + data + "\n";
        return s;
    }
    
}
