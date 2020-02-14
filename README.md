# AutoCompleteActivity
Demonstrates how to show **AutoCompleActivity** of Google Places API

The project presents how to launch **AutoCompleteActivity** of Google Places API for searching places. It also includes a sample on how to handle the selected place from AutoCompleteActivity.

AutoCompleteActivity allows the user to look for a place from the list of place predictions based on the search query. As the user input the name of the place in the search box, the list of possible places that the user may be referring to will be shown. When an item is selected, details of the corresponding Place object is available in the **Intent** object returned by **AutoCompleteActivity**. 

In summary, to use **AutoCompleteActivity** you need to:
1. Add **implementation 'com.google.android.libraries.places:places:X.X.X'** as dependency in app's build.gradle
2. Initialize **Places** object using the API key created from Google API console
3. Create an intent using **AutoComplete.IntentBuilder** (Note: You can specifiy the fields to be included in the search results)
4. Launch intent using *startActivityResult*
5. Handle selected Place from the query results in *onActivityResult* of the calling activity

Note: To be able to run the project successfully, you need to add **apicredentials.properties** in the project. The file should include **GOOGLE_API_KEY="<API_KEY>"**.

To know more about Places SDK visit https://developers.google.com/places/web-service/intro
