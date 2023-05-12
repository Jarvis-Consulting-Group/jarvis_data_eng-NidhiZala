package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelperImp;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
public class TwitterDaoTest {
    private TwitterDao twitterDaoTest;

    @Before
    public void setup() {
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");
        HttpHelper httpHelper = new HttpHelperImp(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.twitterDaoTest = new TwitterDao(httpHelper);
    }

    public long generateUniqueId() {
        return System.currentTimeMillis();
    }

    @Test
    public void create() throws Exception {
        // Create a new tweet
        String hashTag = "#hashtag";
        String text = "Twitter" +" "+hashTag + " "+"test" ;
        Float lon = -1f;
        Float lat = 1f;
        Tweet postTweet = new Tweet();
        postTweet.setText(text);

        Coordinates coordinates = new Coordinates();
        coordinates.setCoordinates(new float[]{lon, lat});
        postTweet.setCoordinates(coordinates);

        // Generate a unique id for the tweet
        long uniqueId = generateUniqueId(); // Replace this with your unique id generation logic
        postTweet.setId(uniqueId);
        ObjectMapper objectMapper = new ObjectMapper();
        String tweetJson = objectMapper.writeValueAsString(postTweet);
        // Call the create method
        Tweet tweet = twitterDaoTest.create(postTweet);

        // Assertions
//        assertEquals(text, tweet.getText());
//        assertNotNull(tweet.getCreated_at());
//        assertNotNull(tweet.getId());
//        assertNotNull(tweet.getId_str());
//        assertNotNull(tweet.getEntities());
//        assertNotNull(tweet.getCoordinates());
//        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
//        assertEquals(Optional.of(lon), tweet.getCoordinates().getCoordinates()[0]);
//        assertEquals(Optional.of(lat), tweet.getCoordinates().getCoordinates()[1]);
//        assertTrue(tweet.getEntities().getHashtags().stream().anyMatch(hashtag -> hashtag.getText().equals(hashTag)));
    }


    @Test
    public void deleteById() {
        String id="1656110614228369408";
        Tweet tweet=twitterDaoTest.deleteById(id);
    }
}
