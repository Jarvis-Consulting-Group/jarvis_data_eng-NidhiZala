package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class that provides operations for posting tweets and deleting tweets.
 */
public class TwitterService implements Service {
    private CrdDao dao;

    /**
     * Constructs a new instance of TwitterService with the provided CrdDao.
     *
     * @param dao the CrdDao implementation to use for data access.
     */
    @Autowired
    public TwitterService(@Qualifier("crdDao") CrdDao dao) {
        this.dao = dao;
    }

    /**
     * Validates the tweet text to ensure it doesn't exceed the character limit.
     *
     * @param tweet the tweet to be validated.
     * @throws IllegalArgumentException if the tweet text exceeds the character limit.
     */
    private void validatePostTweet(Tweet tweet) {
        if (tweet.getText().length() > 140) {
            throw new IllegalArgumentException("Tweet characters exceed the limit of 140");
        }
    }

    /**
     * Validates if the provided ID contains only numbers.
     *
     * @param id the ID to be validated.
     * @return true if the ID contains only numbers, false otherwise.
     */
    private boolean validateId(String id) {
        for (char c : id.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Posts a new tweet.
     *
     * @param tweet the tweet to be posted.
     * @return the posted tweet.
     * @throws IllegalArgumentException if the tweet text exceeds the character limit.
     */
    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        return null;
    }

    /**
     * Deletes multiple tweets based on their IDs.
     *
     * @param ids the array of tweet IDs to be deleted.
     * @return the list of deleted tweets.
     * @throws IllegalArgumentException if any of the IDs is null or contains non-digit characters.
     */
    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        List<Tweet> deletedTweets = new ArrayList<>();

        for (String id : ids) {
            if (id == null) {
                throw new IllegalArgumentException("ID must contain a value");
            }

            if (!validateId(id)) {
                throw new IllegalArgumentException("ID must contain only numbers");
            }

            Tweet deletedTweet = (Tweet) dao.deleteById(id);
            deletedTweets.add(deletedTweet);
        }

        return deletedTweets;
    }
}
