package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.location;
import POJO.places;

public class TestDataBuild {
	
	places place;
	
	public places addPlacePayload(String name, String language, String address)
	{
		place = new places();
    	place.setAccuracy(50);
    	place.setAddress(address);
    	List<String> type = new ArrayList<String>();
    	type.add("shoe park");
    	type.add("shop");
    	place.setTypes(type);
    	place .setLanguage(language);
    	place.setWebsite("http://google.com");
    	place.setPhone_number("(+91) 983 893 3937");
    	place.setName(name);
    	
    	location l = new location();
    	l.setLng(33.427362);
    	l.setLat(-38.383494);
    	place.setLocation(l);
    	return place;
	}

}
