package es.unizar.tmdad.lab0.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TwitterLookupService {
	@Autowired
	@Value("${twitter.consumerKey}")
	private String consumerKey;

	@Autowired
	@Value("${twitter.consumerSecret}")
	private String consumerSecret;

	@Autowired
	@Value("${twitter.accessToken}")
	private String accessToken;

	@Autowired
	@Value("${twitter.accessTokenSecret}")
	private String accessTokenSecret;
	
	public SearchResults search(String query) {
		System.out.println(consumerKey);
		System.out.println(consumerSecret);
		System.out.println(accessToken);
		System.out.println(accessTokenSecret);
        Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return twitter.searchOperations().search(query);
    }
}
