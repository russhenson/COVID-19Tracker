# COVID-19Tracker
COVID-19 Tracker in the Philippines

This COVID-19 Tracker Android App is made by Russ Henson and Gerard Josol.

What we have accomplished:
- Connect the COVID-19 Philippines Web API to the app
- Retrieve data from firebase firestore
- Show the general statistics of COVID-19 in the Philippines
- Show the top 10 regions with most cases
- Show the statistic in NCR region
- Show the list of symptoms by category: Most common, serious, and less commong symptoms
- Show the COVID-19 hotline

What we changed from our proposal:
- We were planning to add a geocoder where the app will get the location (with permission) of the user.
  Then, the app will show the statistics of the city where the user is located. However, after trying
  multiple algorithms, it seems that the android emulator can only get one location which is the Google Headquarters
  in the U.S. We were not able to get our location here in the Philippines after many tries. 
- We were planning to put a top 10 cities with most cases in the dashboard. However, the COVID-19 Philippines Web API 
  do not support the city statistics. The API has the statistics of every region in the country and it is sorted by the
  number of cases, but not the cities in the country. With this, we removed the top 5 cities and replace it with top 10
  regions. We also removed the city locator and replaced it with NCR statistics.

