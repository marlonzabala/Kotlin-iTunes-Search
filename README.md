# Kotlin-itunes-Search
Simple Itunes API search using MVVM in kotlin

This is a master-detail application that lists items from the itunes search API

Default URL used on startup:

https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all

Shows a list of movies on the main page and you can search through them.

Details showed on the list are:
Track Name, Artwork (400x400), Price, Genre


Clicking on an item will direct you to the Details Activity, giving a longer description on about the selected item.

A placeholder image is used in the event of image retrieval failure. 


The app stores data on the preferences.

When app is destroyed when viewing a detail of a movie, you are retured to the detail activity upon reopen

Search terms are also retianed. When returning to the app. The last search term will be showed.



The app uses MVVM architecture.

MVVM was chosen because it is recommended by Google.

With the use of live data, components are less coupled which results in less buggy code.
