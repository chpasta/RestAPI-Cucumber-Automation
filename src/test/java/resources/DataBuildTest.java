package resources;

import addPlace.AddPlace;
import addPlace.Location;

public class DataBuildTest {

    public AddPlace addPlacePayload(int accuracy, String name, String language, String address ){

        AddPlace addPlace = new AddPlace();

        addPlace.setAccuracy(accuracy);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://rahulshettyacademy.com");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        return addPlace;
    }

    public String deletePlacePayload(String placeId){
        return "{ \"place_id\": \""+placeId+"\"}" ;
    }
}
