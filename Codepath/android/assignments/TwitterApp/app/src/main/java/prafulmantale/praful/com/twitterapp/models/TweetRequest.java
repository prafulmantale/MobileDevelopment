package prafulmantale.praful.com.twitterapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by prafulmantale on 9/30/14.
 */
public class TweetRequest implements Parcelable {

    private String body;

    //Add other details like in response to etc


    public TweetRequest() {
        this.body = "";
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(body);
    }

    public static final Creator<TweetRequest> CREATOR = new Creator<TweetRequest>() {
        @Override
        public TweetRequest createFromParcel(Parcel source) {
            return new TweetRequest(source);
        }

        @Override
        public TweetRequest[] newArray(int size) {
            return new TweetRequest[size];
        }
    };

    private TweetRequest(Parcel in) {
        body = in.readString();
    }
}
