App flow:

- App has seed data of 6 Students with random address across bay area
- App has seed data for a School. This is used as the "Destination"
- User's current location is used as "Origin"
- Collected Lat Lang info for above address using geocode Apis. (This info was helpful for drawing Markers on the Map)
- When App is launched,User is shown the Progress bar saying best possible route is being looked for
- Students list is displayed in alphabetical order
- App collects the best possible route using Google Directions API.
- Once the best route is received, Students list is displayed as per the order in which they should be picked.
The list now also displays distance needs to be traveled for each leg of the journey
- User can click, "Next Pick up" button to view Directions screen
- Once all the Students are either Picked or marked as No Show, clicking on "Next Pick up" shows "All pcikups done" toast.
-On the Directions screen, User can 
    - view Student info
    - View Map with path drawn on it with markers for Start(User's current location') and End address
    - Mark the students as picked or no show
    - Click on "List icon" on map to view the step by step instructions

Travel steps activty dispalys the travel steps for current Travel Leg. (with a Dummy left turn icon)


Notes:

The UI and workflow can be improved temendously

Few of the things that definitely missing are - 
Google Play service is installed or not
Network Connection check (NetworkUtils helper has the methods but not used)
Progress Bar handling
Different orientations and screen sizes
Logging
Exception Handling
Scope for performance improvemet


![Alt text](https://github.com/prafulmantale/MobileDevelopment/blob/master/AndroidApps/KidsOnWheels/KidsOnWheels.gif)
