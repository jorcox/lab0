package es.unizar.tmdad.lab0.service;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.SimpMessageSendingOperations;

import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamListener;

import es.unizar.tmdad.lab0.service.SimpleStreamListener;


@Service
public class TwitterLookupService {

	@Autowired
	SimpMessageSendingOperations messageSendingOps;

	private Map<String,Stream> streams = new LinkedHashMap<String, Stream>(){
		private static final int MAX_ENTRIES = 10;

    	protected boolean removeEldestEntry(Map.Entry eldest) {
        	return size() > MAX_ENTRIES;
    	}
 	};

	@Value("${twitter.consumerKey}")
	private String consumerKey;

	@Value("${twitter.consumerSecret}")
	private String consumerSecret;

	@Value("${twitter.accessToken}")
	private String accessToken;

	@Value("${twitter.accessTokenSecret}")
	private String accessTokenSecret;
	
	public void search(String query) {
		if (!streams.containsKey(query)) {

			List<StreamListener> listeners = new ArrayList<StreamListener>();

	        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
			
			listeners.add(new SimpleStreamListener(messageSendingOps, query));

			streams.put(query, twitter.streamingOperations().filter(query,  listeners));
		}
    }
}
