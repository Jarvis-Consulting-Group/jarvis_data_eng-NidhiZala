package ca.jrvs.apps.twitter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Arrays;

public class TwitterApiTest {

    private static String CONSUMER_KEY = "f6bfieMFM8gWcvycKMdCKGA3i";
    private static String CONSUMER_SECRET = "84V5uonAr1ZSN9AKrPyJ9B9Q1K12C8qsA3XXZeGenMvyNAUf7E";
    private static String ACCESS_TOKEN = "1648781568696111107-S4cma4EAc6ppF4KGD0GXTV3RLJiR1O";
    private static String TOKEN_SECRET = "nVSI9rAHvRg8h7O30Rf8VHeKqzVkzpZqJ0AO8eHaVNrRF";

    public static void main(String[] args) throws Exception{
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "Twitter TEST";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", status);
        HttpPost httpPost = new HttpPost("https://api.twitter.com/2/tweets");
        StringEntity input = new StringEntity(jsonObject.toString());
        input.setContentType("application/json");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(input);
        consumer.sign(httpPost);

        System.out.println("Http Request Headers:");
        Arrays.stream(httpPost.getAllHeaders()).forEach(System.out::println);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
