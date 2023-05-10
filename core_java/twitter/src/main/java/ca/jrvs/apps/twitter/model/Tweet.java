package ca.jrvs.apps.twitter.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "text",
        "public_metrics",
        "edit_history_tweet_ids",
        "created_at",
        "geo",
        "entities",
        "favorited",
        "favorite_count",
        "retweet_count",
        "coordinates",
        "id_str",
        "retweeted",
        "user"

})
public class Tweet {

    private String created_at;
    private long id;
    private String id_str;
    private String text;
    private Entities entities;
    private Coordinates coordinates;
    private int retweet_count;
    private int favorite_count;
    private boolean favorited;
    private boolean retweeted;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getId_str() {
            return id_str;
        }

        public void setId_str(String id_str) {
            this.id_str = id_str;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Entities getEntities() {
            return entities;
        }

        public void setEntities(Entities entities) {
            this.entities = entities;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public int getRetweet_count() {
            return retweet_count;
        }

        public void setRetweet_count(int retweet_count) {
            this.retweet_count = retweet_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isRetweeted() {
            return retweeted;
        }

        public void setRetweeted(boolean retweeted) {
            this.retweeted = retweeted;
        }

}
