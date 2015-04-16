package praful.com.kidsonwheels.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 4/12/15.
 */
public class Duration {

    @JsonProperty("text")
    private String mText;

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "mText='" + mText + '\'' +
                '}';
    }
}
