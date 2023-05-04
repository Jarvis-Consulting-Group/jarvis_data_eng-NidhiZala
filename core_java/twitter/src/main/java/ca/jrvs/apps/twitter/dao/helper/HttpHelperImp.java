package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;


@Component
public class HttpHelperImp implements HttpHelper {

    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public HttpHelperImp(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, tokenSecret);
        this.httpClient = HttpClientBuilder.create().build();
    }

    public HttpHelperImp() {
        this(System.getenv("consumerKey"), System.getenv("consumerSecret"), System.getenv("accessToken"), System.getenv("tokenSecret"));
    }


    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        try {
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            if (stringEntity != null) {
                request.setEntity(stringEntity);
            }
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public HttpResponse httpGet(URI uri) {
        try {
            HttpGet request = new HttpGet(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpResponse httpDelete(URI uri) {
        try {
            HttpDelete request = new HttpDelete(uri);
            consumer.sign(request);
            return httpClient.execute(request);
        } catch (OAuthException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

