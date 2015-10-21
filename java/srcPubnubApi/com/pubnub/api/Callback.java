package com.pubnub.api;

/**
 * Abstract class to be subclassed by objects being passed as callbacks to
 * Pubnub APIs Default implementation for all methods is blank
 *
 * @author Pubnub
 *
 */
public abstract class Callback {
	
	public void hereNowCallback(Result result) {
		
	}

	public void hereNowCallback(Status status) {
		
	}

	public void grantCallback(Result result) {
		
	}

	public void grantCallback(Status status) {
		
	}
	
	
	
	public void successCallback(String channel, Object message, Result result) {
		if (result == null) {
			successCallback(channel, message);
		} else {
			switch(result.getOperation()) {
			case HERE_NOW_FOR_CHANNEL:
				hereNowCallback(result);
				break;
			case GRANT:
				grantCallback(result);
				grantCallback((Status)result);
				break;
			default:
				break;
				
			}
		}
	}
	
	public void errorCallback(String channel, PubnubError error, Result result) {
		if (result == null) {
			errorCallback(channel, error);
		} else {
			switch(result.getOperation()) {
			case HERE_NOW_FOR_CHANNEL:
				hereNowCallback((Status)result);
				hereNowCallback(result);
				break;
			case GRANT:
				grantCallback((Status)result);
				grantCallback(result);				
				break;
			default:
				break;
				
			}
		}
	}
	
	

    /**
     * This callback will be invoked when a message is received on the channel
     *
     * @param channel
     *            Channel Name
     * @param message
     *            Message
     *
     */
    public void successCallback(String channel, Object message) {
    	
    }

    /**
     * This callback will be invoked when a message is received on the channel
     *
     * @param channel
     *            Channel Name
     * @param message
     *            Message
     * @param timetoken
     *            Timetoken
     */
    public void successCallback(String channel, Object message, String timetoken) {

    }

    void successWrapperCallback(String channel, Object message, String timetoken) {
        successCallback(channel, message);
        successCallback(channel, message, timetoken);
    }

    /**
     * This callback will be invoked when an error occurs
     *
     * @param channel
     *            Channel Name
     * @param error
     *            error
     */
    public void errorCallback(String channel, PubnubError error) {
        errorCallback(channel,error.toString());
    }

    /**
     * This callback will be invoked when an error occurs
     *
     * @param channel
     *            Channel Name
     * @param message
     *            Message
     *@deprecated as of version 3.5.2 and will be removed with 3.6.0 .
     *            Replaced by {@link #errorCallback(String channel, PubnubError error)}
     */
    public void errorCallback(String channel, Object message) {

    }

    /**
     * This callback will be invoked on getting connected to a channel
     *
     * @param channel
     *            Channel Name
     */
    public void connectCallback(String channel, Object message) {
    }

    /**
     * This callback is invoked on getting reconnected to a channel after
     * getting disconnected
     *
     * @param channel
     *            Channel Name
     */
    public void reconnectCallback(String channel, Object message) {
    }

    /**
     * This callback is invoked on getting disconnected from a channel
     *
     * @param channel
     *            Channel Name
     */
    public void disconnectCallback(String channel, Object message) {
    }

}
