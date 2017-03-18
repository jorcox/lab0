package es.unizar.tmdad.lab0.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.MimeTypeUtils;

public class SimpleStreamListener implements StreamListener {
	private SimpMessageSendingOperations messageSendingOps;
	private String query;
	
	public SimpleStreamListener(SimpMessageSendingOperations messageSendingOps, String query){
		this.messageSendingOps = messageSendingOps;
		this.query = query;
	}

	@Override
	public void onTweet(Tweet tweet) {
		System.out.println(tweet);

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

		messageSendingOps.convertAndSend("/queue/search/" + query, tweet, headers);
	}

	@Override
	public void onDelete(StreamDeleteEvent ev) {}

	@Override
	public void onLimit(int limit) {}


	@Override
	public void onWarning(StreamWarningEvent ev) {}

}