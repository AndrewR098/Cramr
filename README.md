# Feed-Aggregator-Cramr
Welcome to Cramr. This application puts your favorite social media platforms all in one place to look at.

# Dummy Login 
- Username: abc
- Password: 123
Or you can create your own.
-----------------------------------------------------
# Dummy twitter login to show authentication code:
- Username: cramrtest
- Password: Cramrtest1
-----------------------------------------------------
# Compiling Instructions in Eclipse (Maven Required)
1. Right click on the project
2. [project] --> Run As --> [Maven build...]
3. Set the Java JRE to the directory of JDK, it can't compile if Maven is set to a JRE environment.
      -->Click the JRE Tab. If Workspace default JRE(jdk1.8.0_51) or some lower version (jdk1.7.X), you're good to go
          --> otherwise, select Alternate JRE checkbox; Click installed JREs...->Add...->Standard VM->
              -->Navigate Computer->C:->Program Files->Java->select the jdk1.X.X_X (choose the latest verison) folder and hit OK
                -->select the checkbox of the new jdk and apply.
4. In goals textbox, type "clean compile"
5. Press [ok]
6. After compiling successfully, go to [Project] --> [Run As] --> [Java Application]
7. And there you go, you now have the application running on your computer.
--------------------------------------------------------
# Removed features due to certain circumstances
- Facebook had to be discontinued due to changes to their system after the whole privacy controversy
- Flickr was discontinued due to time constraints.
- Customizable home page was not implemented. Feeds display by default and ask you to populate them if there is nothing to display.
- Tweeting from the home page was removed.
--------------------------------------------------------
# Bugs/Bugged Features
- TWITTER - Retweeting and liking works. However, undoing retweets and likes is broken. Only implemented for first tweet on timeline.
- TWITTER - Likes on retweeted tweets on your timeline always show as 0.
--------------------------------------------------------
# Twitter NOTES:
- The Twitter API only handles 15 API requests per 15 minutes. Please limit loading back and forth between the twitter subview once you have authenticated the account.
- The Twitter Authentication process requires you to access login, authorize the app, and then provide the pin back to the program. 
- For security purposes, Twitter access tokens are not stored. You will need to authenticate each time you launch the app.
- Cramr is able to load the user's timeline (20 tweets)
- Cramr is able to post to the user's account.
--------------------------------------------------------
# Reddit NOTES:
- You can manage subreddit subscriptions by clicking the plus sign and swapping to the Reddit tab to add and delete subreddits.
- You can view subscriptions on the home page.
- You can click on any item in any reddit feed to load the link in your default browser.
