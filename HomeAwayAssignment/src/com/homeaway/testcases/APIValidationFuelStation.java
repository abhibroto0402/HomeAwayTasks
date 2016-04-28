package com.homeaway.testcases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.homeaway.api.FuelStationsUtils;
/**
 * This is the test case class 
 * 
 * The first test performs an API search query based on Austin, TX and Network = ChargePoint Network
 * - Extracts all the fuel stations from the response JSON file
 * - Fetches the StationID from the File and Validates with Expected Station ID
 * - Stores the station ID in the dataFile.txt
 * 
 * The Second test performs an API Search based on stationID
 * - Fetches the stationID from the dataFile.txt
 * - sends the query through HTTPS
 * - From the response JSON file extracts Street Address, City, State and Zip code
 * - Compares with Expected Address
 * 
 * @author Abhibroto
 *
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class APIValidationFuelStation {
	private FuelStationsUtils fsu, fsu1;
	private JSONObject allInfo;
	private JSONArray fuelStations;
	private final String KEY = "UG07byblSe6XM516eJjJJROOGW1pMbXGLPZSfbne&location";
	String station_ID;
	@Before
	public void setup(){
		this.fsu = new FuelStationsUtils ("https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=UG07byblSe6XM516eJjJJROOGW1pMbXGLPZSfbne&location=");
	}
	
	@Test
	public void validateFuelStationId() throws JSONException {
		
		//Search Based on City, State, Field Name and Criterion
		this.allInfo= fsu.getResponse("Austin", "TX", "ev_network", "ChargePoint%20Network");
		// Validate the response is not blank
		Assert.assertNotNull(allInfo);
		
		// Fetch all the Fuel Stations returned based on the search criteria
		this.fuelStations = allInfo.getJSONArray("fuel_stations");
		
		//Find Station Name Exists in Array of Fuel Stations and if found get Station ID
		this.station_ID = fsu.getStationID(fuelStations, "HYATT AUSTIN");
		// Validate the correct Station ID is returned
		Assert.assertEquals(station_ID, String.valueOf(62029));
		
		//Store the Station ID
		fsu.storeItem(station_ID);
		
	}
	
	@Test
	public void validateStationAddress(){
		
		this.fsu1 = new FuelStationsUtils("https://api.data.gov/nrel/alt-fuel-stations/v1/");
		//Get Station ID
		this.station_ID = fsu1.getStoredItem();
		
		// Validate the address returned matches the expected address
		String stationAddr = fsu1.getStationAddress(KEY, station_ID);
		Assert.assertEquals("208 Barton Springs Rd, Austin, TX, 78704", stationAddr);
		
	}

}
