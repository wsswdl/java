package com.pubnub.domain;

import lombok.Data;

@Data
public class HereNowResult extends Result {
	HereNowData data;

	public HereNowResult() {
		data = new HereNowData();
	}

}
