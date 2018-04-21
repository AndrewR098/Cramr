# Feed-Aggregator-Cramr
Welcome to Cramr. This application puts your favorite social media platforms all in one place to look at.

# Dummy Login
- Username: abc
- Password: 123
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
- Flickr was discontinued
