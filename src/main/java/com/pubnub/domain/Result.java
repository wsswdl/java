package com.pubnub.domain;

import com.pubnub.api.Config;
import com.pubnub.api.HttpRequest;
import com.pubnub.api.PubnubCore;
import lombok.Data;

@Data
public class Result {
	private ResultType type;
	private int code;
	private OperationType operation;
	private Config config;
	private String connectionId;
	private String clientRequest;
	private String serverResponse;

	public Result() {
		config = new Config();
		type = ResultType.RESULT;
	}

	private HttpRequest hreq;
	private PubnubCore pubnub;


}
