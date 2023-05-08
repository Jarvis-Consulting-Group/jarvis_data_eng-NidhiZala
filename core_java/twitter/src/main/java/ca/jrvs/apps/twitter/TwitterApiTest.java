package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelperImp;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URI;
import java.util.Arrays;

public class TwitterApiTest {

    private static String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private static String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private static String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private static String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

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

//package ca.jrvs.apps.twitter.dao.helper;
//
//        import ch.qos.logback.core.net.SyslogOutputStream;
//        import junit.framework.TestCase;
//        import org.apache.http.HttpResponse;
//        import org.apache.http.entity.StringEntity;
//        import org.apache.http.util.EntityUtils;
//        import org.junit.Test;
//        import java.net.URI;
//
//public class HttpHelperImpTest{
//    @Test
//    public void httpPost() throws Exception {
//        String CONSUMER_KEY= System.getenv("CONSUMER_KEY");
//        String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
//        String ACCESS_TOKEN =System.getenv("ACCESS_TOKEN");
//        String TOKEN_SECRET = System.getenv("TOKEN_SECRET");
//        StringEntity entity = new StringEntity("{\"text\": \"Hi Nidhi here\"}");
//        System.out.println(CONSUMER_KEY+"|"+ CONSUMER_SECRET+ "|" + ACCESS_TOKEN + "|" + TOKEN_SECRET);
//        HttpHelper httpHelper = new HttpHelperImp(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN, TOKEN_SECRET);
//        HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/2/tweets"), entity);
//        System.out.println(EntityUtils.toString(response.getEntity()));
//    }
//}