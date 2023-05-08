package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class HttpHelperImpDeleteTest {

    @Test
    public void httpDelete() throws URISyntaxException {
        String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
        String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
        String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
        String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

        // Create an instance of HttpHelper
        HttpHelper httpHelper = new HttpHelperImp(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

        // Define the tweet ID to delete
        String tweetId = "1655601938094780418";

        // Build the delete tweet URL
        String deleteUrl = "https://api.twitter.com/2/tweets/" + tweetId;

        // Perform the HTTP DELETE request
        HttpResponse response = null;
        try {
            response = httpHelper.httpDelete(new URI(deleteUrl));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        // Assert the response status code
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(200, statusCode);
    }
}