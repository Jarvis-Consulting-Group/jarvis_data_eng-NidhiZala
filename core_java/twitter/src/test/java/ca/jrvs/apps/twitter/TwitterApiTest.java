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
    static String CONSUMER_KEY= System.getenv("consumerKey");
    static String CONSUMER_SECRET = System.getenv("consumerSecret");
    static String ACCESS_TOKEN =System.getenv("accessToken");
    static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception{
        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "Twitter TEST yo";
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
