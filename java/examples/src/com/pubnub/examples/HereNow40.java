package com.pubnub.examples;

import com.pubnub.api.*;

public class HereNow40 {

	static String channel = "a3" + System.currentTimeMillis();
	public static void main(String[] args) {
		Pubnub pubnub = new Pubnub("demo", "demo");
		
		pubnub.hereNow(channel, new Callback(){
			public void hereNowCallback(Result result){
				System.out.println(result);
			}
		});

		
		final Pubnub pubnub_pam = new Pubnub("pam", "pam", "pam", "", true);
		
		pubnub_pam.hereNow(channel, new Callback(){

			public void hereNowCallback(final Result result){
				
				if (result.getType() == ResultType.ERROR) {
					
					System.out.println("ERROR");
					System.out.println(result);
					
					pubnub_pam.pamGrant(channel, true, true, new Callback(){
						public void grantCallback(Status status) {
							
							System.out.println(status);
							
							if (status.getType() == ResultType.ERROR) {
								status.retry();
							} else {
								((Status)result).retry();
							}
							
						}
					});
					
				} else {
					System.out.println(result);
				}
			}
		});
		
		
	}

}
