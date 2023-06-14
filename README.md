## Mobile Development Documentation
The source code of Android app of Jejaknesia using Kotlin in order to complete Bangkit Capstone Project.

 - ### Feature
      * **Splash Screen**, an introduction page by showing a logo that is displayed in the application

      * **Login**, allow user to gain access to an application by entering their email and password

      * **Register**, enables users to register or create an account by entering their name, email, and password

      * **Home**, a home page that is displayed when you have logged in on your device

      * **Generate Result Based on User Preference**, you can choose a category that fits you and get the top destination result based on it

      * **Top Destination Detail**, you can see the detail of the top destination such as city, address, rating, and description

      * **Location Maps**, you can see the location of each result. You can zoom in, zoom out, and also get your location

      * **Blog About Tourism**, You can read blog articles about Tourism in this Application

      * **Blog Detail**, you can tap each of the blog articles if you want to know more about the articles 

      * **Dark Mode**, you can change the mode as you prefer by clicking Dark Mode icon on the HomePage AppBar 


* #### Dependencies :
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) 
  - [Glide](https://github.com/bumptech/glide) 
  - [Retrofit 2](https://square.github.io/retrofit/)   
  - [Ok Http 3](https://square.github.io/okhttp/) 
  - [DataStore](https://developer.android.com/jetpack/androidx/releases/datastore?hl=id) 
  - [Ok Http](https://square.github.io/okhttp/) 
  - [Ok Http](https://square.github.io/okhttp/) 
  - [Google Play services Maps](https://developers.google.com/maps/documentation/android-sdk/get-api-key) 

**Note**: This list includes some of the major dependencies used in the Jejaknesia App. It may not include all the libraries and dependencies used in the project.


### Getting Started Application

  - ### Prerequisites
      - ##### Softwares
        - [Android Studio](https://developer.android.com/studio)
        - JRE (Java Runtime Environment) or JDK (Java Development Kit).
        - Internet Connection

      - #### Installation
        - Get an API Key at [Google Maps Platform](https://developers.google.com/maps/documentation/android-sdk/get-api-key)

      - Clone this repository and import into Android Studio    
            ```
               https://github.com/Jejaknesia/mobile_development.git
            ``` 
        - Enter your API Key in your AndroidManifest.xml file
           ``` <meta-data
                  android:name="com.google.android.geo.API_KEY"
                  android:value="${MAPS_API_KEY}" />
