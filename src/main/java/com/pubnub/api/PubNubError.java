package com.pubnub.api;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PubNubError {

    private int errorCode;
    private int errorCodeExtended;
    private JsonNode errorObject;
    private String errorString;
    private String message;

}
