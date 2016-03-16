package com.pubnub.domain;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;

@Data
public class HistoryData {
    private JSONArray messages;
    private String start;
    private String end;

}
