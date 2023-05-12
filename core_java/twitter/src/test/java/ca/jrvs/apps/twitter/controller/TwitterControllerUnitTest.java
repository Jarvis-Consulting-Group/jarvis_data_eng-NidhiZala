package ca.jrvs.apps.twitter.controller;

import org.junit.Test;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.junit.Before;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class TwitterControllerUnitTest {

    private Service twitterService;
    private TwitterController twitterController;

    @Before
    public void setup() {
        twitterService = mock(Service.class);
        twitterController = new TwitterController(twitterService);
    }

    @Test
    public void testPostTweet() {
        String[] args = {"post", "Hello, Twitter!"};
        Tweet expectedTweet = new Tweet();
        when(twitterService.postTweet(any(Tweet.class))).thenReturn(expectedTweet);

        twitterController.postTweet(args);

        verify(twitterService).postTweet(any(Tweet.class));
    }

    @Test
    public void deleteTweet() {
        String[] args = {"delete", "tweet", "test", "example"};
        when(twitterService.deleteTweets(any())).thenReturn(Collections.emptyList());

        twitterController.deleteTweet(args);

        verify(twitterService).deleteTweets(any());
    }
}