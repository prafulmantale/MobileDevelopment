package prafulmantale.praful.com.twitterapp.interfaces;

import prafulmantale.praful.com.twitterapp.models.Tweet;
import prafulmantale.praful.com.twitterapp.models.UserProfile;

/**
 * Created by prafulmantale on 10/1/14.
 */
public interface ViewsClickListener {

    public void OnReplyToTweetRequested(Tweet tweet);
    public void OnCreateFavoriteTweetRequested(Tweet tweet);
    public void OnDestroyFavoriteTweetRequested(Tweet tweet);
    public void OnUserProfileRequested(UserProfile user);
}
