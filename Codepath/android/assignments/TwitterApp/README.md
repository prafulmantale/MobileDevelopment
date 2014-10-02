Required: Build a simple Twitter client that supports viewing a Twitter timeline and composing a new tweet. 

Required: 
		User can sign in to Twitter using OAuth login

		User can view the tweets from their home timeline
			User should be displayed the username, name, and body for each tweet
			User should be displayed the relative timestamp for each tweet "8m", "7h"
			User can view more tweets as they scroll with infinite pagination
			Optional: Links in tweets are clickable and will launch the web browser (see autolink). 
				Have used custom logic to display links as they are displayed in the Twitter app.

		User can compose a new tweet
			User can click a “Compose” icon in the Action Bar on the top right
			User can then enter a new tweet and post this to twitter
			User is taken back to home timeline with new tweet visible in timeline
			Optional: User can see a counter with total number of characters left for tweet

Optional:

	Advanced: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
	Advanced: User can tap a tweet to display a "detailed" view of that tweet
	Advanced: User can select "reply" from detail view to respond to a tweet
	Advanced: Improve the user interface and theme the app to feel "twitter branded"

Not Done:
	Advanced: User can open the twitter app offline and see last loaded tweets
		Tweets are persisted into sqlite and can be displayed from the local DB

		Have done this parially. The logic to save tweets is present, however they were not getting persisted properly.

	Bonus: User can see embedded image media within the tweet detail view
	Bonus: Compose activity is replaced with a modal overlay
		
Notes:
	Total time spent 10-12 Hrs.


Walkthrough of all user stories:

![Alt text](https://github.com/prafulmantale/MobileDevelopment/blob/master/Codepath/android/assignments/TwitterApp/TwitterApp.gif)
