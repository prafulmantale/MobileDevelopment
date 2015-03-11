package com.dhpn.model;

import com.dhpn.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by prafulmantale on 2/21/15.
 */

@Document(collection = "reviews")
public class Review {

    @Id
    @JsonProperty("id")
    private String id;

    @Field(value = "ts")
    @JsonProperty("ts")
    private Date timestamp;

    @Field(value = "dts")
    @JsonProperty("dts")
    private String displayTimestamp;

    @Field(value = "text")
    @JsonProperty("text")
    private String text;

    @Field(value = "hc")
    @JsonProperty("hc")
    private long helpfulCount;

    @Field(value = "nhc")
    @JsonProperty("nhc")
    private long notHelpfulCount;

    public Review() {

        timestamp = new Date();
        displayTimestamp = DateTimeUtils.getFormattedCurrentTime();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDisplayTimestamp() {
        return displayTimestamp;
    }

    public void setDisplayTimestamp(String displayTimestamp) {
        this.displayTimestamp = displayTimestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(long helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public long getNotHelpfulCount() {
        return notHelpfulCount;
    }

    public void setNotHelpfulCount(long notHelpfulCount) {
        this.notHelpfulCount = notHelpfulCount;
    }
}
