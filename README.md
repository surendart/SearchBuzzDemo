# SearchBuzzDemo

This app demonstrates the usage of trending search terms android sdk.

Steps to build and run the app.

1> Clone the repo. Add app/libs directory relative to root of project.

2> Add trending android sdk to above directory. You also need to replace appId in MainActivity.java with the one provided to you by Partner team to attribute tracking.

3> Build using command "./gradlew clean assemble" OR use Android Studio Toolbar.

4> Launch the app to see the trending search terms on landing screen.

Email us at: searchsdk-feedback@yahoo-inc.com for feedback / questions.

5> International Search Buzz :
    SDK picks the Country (locale) from the phone preferred language setting. For eg. when the language setting in the phone is English (US) the trending terms would be for the country US.
    SDK also allows the developers to set the country and get trending terms for the specific country irrespective of the phone language setting. The country value should be ISO 3166 alpha-2 country code.
    In the below snippet the country was set to **IN** using the **TrendingViewSettings** builder.

```java

 TrendingViewSettings.Builder builder = new TrendingViewSettings.Builder(YOUR_APP_ID, TrendingCategory.DEFAULT);
 builder.setNumTerms(6);
 builder.setCountry("IN");

```


![device-2016-04-27-150026](https://cloud.githubusercontent.com/assets/1653569/14871866/6e3d7252-0c9b-11e6-98fa-89bf90bd5567.png)
