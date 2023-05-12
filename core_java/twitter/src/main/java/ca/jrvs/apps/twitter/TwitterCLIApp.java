package ca.jrvs.apps.twitter;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelperImp;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwitterCLIApp {
    public static final String USAGE = "Usage: TwitterCLIApp post|delete [options]";

    private final Controller controller;

    @Autowired
    public TwitterCLIApp(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(USAGE);
            System.exit(1);
        }

        HttpHelperImp httpHelper = getTwitterHttpHelper();
        CrdDao dao = new TwitterDao(httpHelper);
        TwitterService service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        app.run(args);
    }

    public void run(String[] args) {
        switch (args[0].toLowerCase()) {
            case "post":
                handlePostTweet(args);
                break;
            case "delete":
                handleDeleteTweet(args);
                break;
            default:
                System.out.println(USAGE);
                System.exit(1);
        }
    }

    private void handlePostTweet(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments for post");
            System.out.println("Usage: TwitterCLIApp post \"text\"");
            System.exit(1);
        }

        String text = args[1];

        Tweet postedTweet = controller.postTweet(new String[]{text});
        printTweet(postedTweet);
    }

    private void handleDeleteTweet(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid number of arguments for delete");
            System.out.println("Usage: TwitterCLIApp delete \"tweet_id1\" [\"tweet_id2\" ... \"tweet_idN\"]");
            System.exit(1);
        }

        String[] tweetIds = new String[args.length - 1];
        System.arraycopy(args, 1, tweetIds, 0, tweetIds.length);

        List<Tweet> deletedTweets = controller.deleteTweet(tweetIds);
        for (Tweet tweet : deletedTweets) {
            printTweet(tweet);
        }
    }

    private void printTweet(Tweet tweet) {
        System.out.println(tweet.getText());
    }

    private static HttpHelperImp getTwitterHttpHelper() {
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        return new HttpHelperImp(consumerKey, consumerSecret, accessToken, tokenSecret);
    }
}
