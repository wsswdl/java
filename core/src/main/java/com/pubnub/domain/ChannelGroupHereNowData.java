package com.pubnub.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelGroupHereNowData {
    int occupancy;
    public int getOccupancy() {
        return occupancy;
    }

    public int getTotalOccupancy() {
        return totalOccupancy;
    }

    public ChannelGroupHereNowUuidData[] getUuids() {
        return uuids;
    }

    int totalOccupancy;
    ChannelGroupHereNowUuidData[] uuids;
    
    
    public String toString() {

        String s = "";
        s = s + "Occupancy: " + occupancy + "\n";
        s = s + "Total Occupancy: " + occupancy + "\n";
        if (uuids != null) s = s + "UUIDS: " + ChannelGroupHereNowUuidData.arrayToString(uuids) + "\n";

        return s;
    }

    public static ChannelGroupHereNowUuidData[] getUuidDataArray(JSONArray jsonArray) {
        System.out.println(jsonArray.toString());
        ChannelGroupHereNowUuidData[] uuidData = new ChannelGroupHereNowUuidData[jsonArray.length()];
        
        for (int i = 0; i < jsonArray.length(); i++) {
            Object a;
            try {
                a = jsonArray.get(i);
                ChannelGroupHereNowUuidData hd = uuidData[i] = new ChannelGroupHereNowUuidData();
                if (a instanceof JSONObject) {
                    JSONObject jso = (JSONObject)a;
                    hd.uuid = jso.getString("uuid");
                    hd.metadata = jso.getJSONObject("metadata");
                } else if (a instanceof String) {
                    hd.uuid = (String) a;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                //System.out.println(e);
                //e.printStackTrace();
            }

        }
        System.out.println(ChannelGroupHereNowUuidData.arrayToString(uuidData));
        return uuidData;
    }
}
