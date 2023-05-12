package ca.jrvs.apps.twitter.service;

import org.junit.Test;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.*;

public class TwitterServiceUnitTest {
    @Mock
    private CrdDao daoMock;

    private TwitterService twitterService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        twitterService = new TwitterService(daoMock);
    }

    @Test
    public void postTweet() {
        Tweet tweet = new Tweet();
        tweet.setText("Service Unit Test");

        // Mock the dao.create() method to return the tweet
        when(daoMock.create(tweet)).thenReturn(tweet);

        // Perform the postTweet operation
        Tweet result = twitterService.postTweet(tweet);

        // Verify that the dao.create() method was called once with the tweet parameter
        verify(daoMock, times(1)).create(tweet);

        // Verify the result
        assertNotNull(result);
        assertEquals(tweet, result);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPostTweet_ExceedCharacterLimit() {
        Tweet tweet = new Tweet();
        tweet.setText("This tweet text exceeds the character limit of 140 characters. " +
                "It is intentionally created to test the validation.");

        // Perform the postTweet operation, which should throw an IllegalArgumentException
        twitterService.postTweet(tweet);
    }

    @Test
    public void deleteTweets() {
        String[] tweetIds = {"000", "2323", "2321"};

        // Mock the dao.deleteById() method to return the corresponding tweets
        when(daoMock.deleteById("000")).thenReturn(mock(Tweet.class));
        when(daoMock.deleteById("2323")).thenReturn(mock(Tweet.class));
        when(daoMock.deleteById("2321")).thenReturn(mock(Tweet.class));

        // Perform the deleteTweets operation
        twitterService.deleteTweets(tweetIds);

        // Verify that the dao.deleteById() method was called once for each tweet ID
        verify(daoMock, times(1)).deleteById("000");
        verify(daoMock, times(1)).deleteById("2323");
        verify(daoMock, times(1)).deleteById("2321");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteTweets_InvalidId() {
        String[] tweetIds = {"000", "2323", "2321"};

        // Perform the deleteTweets operation, which should throw an IllegalArgumentException
        twitterService.deleteTweets(tweetIds);
    }
}