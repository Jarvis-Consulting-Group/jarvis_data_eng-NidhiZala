package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class TwitterDaoUnitTest {
    @Mock
    private HttpHelper httpHelper;

    @Mock
    private HttpResponse httpResponse;

    @Mock
    private StatusLine statusLine;

    private TwitterDao twitterDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        twitterDao = new TwitterDao(httpHelper);
    }

    @Test
    public void testPostTweet() throws HttpException, UnsupportedEncodingException, URISyntaxException {
        // Create a mock HTTP response with a 200 status code
        HttpResponse response = Mockito.mock(HttpResponse.class);
        StatusLine statusLine = Mockito.mock(StatusLine.class);
        Mockito.when(statusLine.getStatusCode()).thenReturn(200);
        Mockito.when(response.getStatusLine()).thenReturn(statusLine);

        // Create a mock TwitterHttpHelper instance and stub the httpPost method
        StringEntity stringEntity = new StringEntity("tweet");
        URI uri = new URI("https://api.twitter.com/2/tweets");

        HttpHelper twitterHttpHelper = Mockito.mock(HttpHelper.class);
        Mockito.when(twitterHttpHelper.httpPost(eq(uri), isA(StringEntity.class))).thenReturn(response);

        // Set up the TwitterDao with the mock TwitterHttpHelper
        TwitterDao twitterDao = new TwitterDao(twitterHttpHelper);

        // Create a new tweet
        String tweetText = "Hello, world!";
        Tweet tweet = new Tweet();
        tweet.setText(tweetText);

        // Post the tweet
        Tweet createdTweet = twitterDao.create(tweet);

        // Verify that the tweet is created successfully
        assertNotNull(createdTweet);
        assertEquals(tweetText, createdTweet.getText());
        // Add more assertions if needed
    }


    @Test
    public void testDeleteTweet() throws IOException {
        // Mock the httpHelper's httpDelete method to return the mock httpResponse
        when(httpHelper.httpDelete(any())).thenReturn(httpResponse);

        // Mock the httpResponse's getStatusLine() to return the mock statusLine
        when(httpResponse.getStatusLine()).thenReturn(statusLine);

        // Mock the statusLine's getStatusCode() method to return HTTP_OK
        when(statusLine.getStatusCode()).thenReturn(200);

        // Mock the httpResponse's getEntity() method to return a string representation of the deleted tweet object
        when(httpResponse.getEntity()).thenReturn(new StringEntity("{\"id\": 123456, \"text\": \"Test tweet\"}"));

        // Call the deleteById method and verify the result
        Tweet deletedTweet = twitterDao.deleteById("123456");
        assertEquals(123456, deletedTweet.getId());
        assertEquals("Test tweet", deletedTweet.getText());

        // Verify that the httpDelete method was called with the correct arguments
        verify(httpHelper, times(1)).httpDelete(ArgumentMatchers.any());

        // Verify that the deleteById method returned the correct tweet object
        assertEquals(123456, deletedTweet.getId());
        assertEquals("Test tweet", deletedTweet.getText());
    }
}
