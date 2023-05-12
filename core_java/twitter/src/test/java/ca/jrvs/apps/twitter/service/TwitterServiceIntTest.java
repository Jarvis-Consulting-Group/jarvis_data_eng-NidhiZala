package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelperImp;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TwitterServiceIntTest {
    private TwitterService twitterService;
    private static final String consumerKey = System.getenv("CONSUMER_KEY");
    private static final String consumerSecret = System.getenv("CONSUMER_SECRET");
    private static final String accessToken = System.getenv("ACCESS_TOKEN");
    private static final String tokenSecret = System.getenv("TOKEN_SECRET");

    @Before
    public void setup() {
        HttpHelper httpHelper = new HttpHelperImp(consumerKey, consumerSecret,accessToken, tokenSecret);
        CrdDao dao = new TwitterDao(httpHelper);
        twitterService = new TwitterService(dao);
    }
    @Test
    public void postTweet() {
        // Create a sample tweet
        Tweet tweet = new Tweet();
        tweet.setText("Hey");
        // Call the postTweet method
        Tweet postedTweet = twitterService.postTweet(tweet);
        System.out.println(postedTweet);
    }

    @Test
    public void deleteTweets() {
        String[] tweetID = {"1656327894728159235"};
        List<Tweet> deletedTweet = twitterService.deleteTweets(tweetID);
    }
}