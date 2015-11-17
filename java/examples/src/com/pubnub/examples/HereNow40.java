package com.pubnub.examples;

import com.pubnub.api.*;

public class HereNow40 {

	//static String channel = "a3" + System.currentTimeMillis();
	static String channel = "a";
	public static void main(String[] args) {
		Pubnub pubnub = new Pubnub("demo", "demo");
		final Pubnub pubnub_pam = new Pubnub("pam", "pam", "pam", "", true);
		
		try {
			pubnub.subscribe(channel, new Callback(){
				public void subscribeCallback(SubscribeResult result) {
					if (result.getType() == ResultType.STATUS) {
						System.out.println((SubscribeStatus)result);
					} else 
					System.out.println(result);
				}
			});
		} catch (PubnubException e) {

		}
		
		/*
		pubnub_pam.setAuthKey("abcd");
		
		pubnub.publish(channel, "hello", new Callback(){
			public void publishCallback(PublishStatus statusInterface) {
				System.out.println(statusInterface);
			}
		});
		
		pubnub_pam.publish(channel, "hello", new Callback(){
			public void publishCallback(PublishStatus statusInterface) {
				System.out.println(statusInterface);
			}
		});
		
		
		
		pubnub.hereNow(channel, new Callback(){
			public void hereNowCallback(HereNowResult result){
				System.out.println(result);
			}
		});
		
		

		pubnub_pam.hereNow(channel, new Callback(){

			public void hereNowCallback(final HereNowResult result){
				
				if (result.getType() == ResultType.STATUS) {
					
					final HereNowStatus hereNowStatus = (HereNowStatus) result;

					System.out.println(result);
					
					if (hereNowStatus.isError()) {
						switch(hereNowStatus.getCategory()) {
						case ACCESS_DENIED:
							pubnub_pam.pamGrant(channel, "abcd", true, true, new Callback(){
								public void grantCallback(GrantStatus status) {
									
									System.out.println(status);
									
									if (status.isError()) {
										status.retry();
									} else {
										hereNowStatus.retry();
									}
									
								}
							});
							break;
						default:
							break;
						}
					}
				} else {
					System.out.println(result);
				}
			}
		});
		*/
		
		
	}

}
