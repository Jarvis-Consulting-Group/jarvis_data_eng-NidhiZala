package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.net.URI;

public class HttpHelperImpCreateTest {
    @Test
    public void httpPost() throws Exception {
        String CONSUMER_KEY= System.getenv("CONSUMER_KEY");
        String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
        String ACCESS_TOKEN =System.getenv("ACCESS_TOKEN");
        String TOKEN_SECRET = System.getenv("TOKEN_SECRET");
        StringEntity entity = new StringEntity("{\"text\": \"Create\"}");
        System.out.println(CONSUMER_KEY+"|"+ CONSUMER_SECRET+ "|" + ACCESS_TOKEN + "|" + TOKEN_SECRET);
        HttpHelper httpHelper = new HttpHelperImp(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN, TOKEN_SECRET);
        HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/2/tweets"), entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}