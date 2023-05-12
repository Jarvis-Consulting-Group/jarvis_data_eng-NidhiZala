package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterController implements Controller {
    private final Service twitterService;

    @Autowired
    public TwitterController(Service twitterService) {
        this.twitterService = twitterService;
    }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: TwitterCliApp -> Post: text");
        }
        String text = args[1];
        Tweet tweet = new Tweet();
        tweet.setText(text);
        return twitterService.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage: deleteTweet <id1> <id2> ... <idN>");
        }

        String[] tweetIds = new String[args.length - 1];
        System.arraycopy(args, 1, tweetIds, 0, tweetIds.length);

        return twitterService.deleteTweets(tweetIds);
    }
}
