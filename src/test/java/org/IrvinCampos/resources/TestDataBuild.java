package org.IrvinCampos.resources;

import io.restassured.RestAssured;
import org.IrvinCampos.pojo.AddPlace;
import org.IrvinCampos.pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayLoad(String name, String language, String address) {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhoneNumber("(+1) 123 456 7890");
        addPlace.setWebsite("https://rahulshettyacademy.com/");
        addPlace.setName(name);
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        return addPlace;
    }

    public String deletePlacePayload(String placeId) {
        return "{\n  \"place_id\": \""+placeId+"\"\n}";
    }

}
