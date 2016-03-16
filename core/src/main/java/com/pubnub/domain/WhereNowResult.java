package com.pubnub.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class WhereNowResult extends Result {
    private WhereNowData data;

    public WhereNowResult() {
        data = new WhereNowData();
    }


    public String toString() {
        String s = super.toString();
        s = s + data + "\n";
        return s;

    }
}
