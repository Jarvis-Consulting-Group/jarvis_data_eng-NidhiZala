package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.JsonParser;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Repository
public class TwitterDao  implements CrdDao<Tweet,String> {

    private static final String API_BASE = "https://api.twitter.com";
    private static final String POST_PATH = "/2/tweets";
    private static final String DELETE_PATH = "/2/tweets/";
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;
    private ObjectMapper jsonMapper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Tweet create(Tweet tweet) {
        URI uri;
        String string;
        JSONObject ob= new JSONObject();
        try {
            string=tweet.getText();
            ob =new JSONObject();
            ob.put("text",string);
            uri=new URI(API_BASE+POST_PATH);
        } catch (URISyntaxException | JSONException e) {
            throw new RuntimeException(e);
        }

        HttpResponse response;
        try {
            response = httpHelper.httpPost(uri, new StringEntity(ob.toString()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return parseResponseBody(response,HTTP_OK);

    }

    @Override
    public Tweet findById(String s) {
        return null;
    }


    private Tweet parseResponseBody(HttpResponse response, int httpCode) {
        // Check status
        int status = response.getStatusLine().getStatusCode();
        if (status != httpCode && status != HttpStatus.SC_CREATED) {
            try {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(responseBody);
            } catch (IOException e) {
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + status);
        }

        if (response.getEntity() == null) {
            throw new RuntimeException("Empty Response Body");
        }

        String json_string;
        try {
            json_string = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to String", e);
        }

        // Convert JSON String into Tweet Object
        try {
            Tweet tweet = JsonParser.toObjectFromJson(json_string, Tweet.class);
            return tweet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Tweet deleteById(String id) {
        HttpResponse response = httpHelper.httpDelete(URI.create(API_BASE + DELETE_PATH + id));
        return parseResponseBody(response, HTTP_OK);
    }
}
