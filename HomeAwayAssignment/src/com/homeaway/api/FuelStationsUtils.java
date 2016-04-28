package com.homeaway.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
/**
 * This is the Utilies class which is used to GET and POST data to API
 * Fetch Specific data 
 * @author Abhibroto
 *
 */
public class FuelStationsUtils {

	private String apiURL;
	private HttpURLConnection apiConnection;

	public FuelStationsUtils(String apiURL) {
		this.apiURL = apiURL;
	}

	public static void disableCertificateValidation() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Ignore differences between given hostname and certificate hostname
		HostnameVerifier hv = new HostnameVerifier() {

			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting trust manager
		try {

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {

		}
	}

	/**
	 * This method is used to return a JSON Object after fetching the
	 * appropriate data after API call
	 * 
	 * @param city
	 * @param state
	 * @param field
	 * @param criteria
	 * @return
	 */
	public JSONObject getResponse(String city, String state, String field, String criteria) {
		apiURL = apiURL + city + "+" + state + "&" + field + "=" + criteria;
		disableCertificateValidation();
		JSONObject jsonObj = null;
		try {

			URL url = new URL(apiURL);
			apiConnection = (HttpURLConnection) url.openConnection();
			apiConnection.setRequestMethod("GET");
			System.out.println(apiConnection.getResponseCode());

			if (apiConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED
					|| apiConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

				String inputline;
				StringBuffer html = new StringBuffer();

				while ((inputline = in.readLine()) != null) {
					html.append(inputline);
				}
				in.close();

				jsonObj = new JSONObject(html.toString().trim());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
			return null;
		}
		return jsonObj;

	}

	/**
	 * The method searches the array and returns true is the element is found
	 * else returns false
	 * 
	 * @param stationArray
	 * @param expectedName
	 * @return boolean
	 */
	public String getStationID(JSONArray stationArray, String expectedName) {
		String stationID = "";
		JSONObject obj;
		for (int i = 0; i < stationArray.length(); i++) {
			try {
				obj = (JSONObject) stationArray.get(i);
				if (obj.get("station_name").equals(expectedName)) {
					stationID = obj.getString("id");
					break;
				}

			} catch (JSONException e) {
				System.out.println(e.getMessage());
				return null;
			}

		}
		return stationID;
	}

	/**
	 * This method fetches different key values, street address, city, state and
	 * zip. Builds and returns a string, Address
	 * 
	 * @param fuelStations
	 * @param stationID
	 * @return
	 */
	public String getStationAddress(String Key, String stationID) {

		apiURL = apiURL + stationID + ".json?api_key=" + Key;

		StringBuilder sb = new StringBuilder();
		disableCertificateValidation();
		JSONObject jsonObj = null;
		try {

			URL url = new URL(apiURL);
			apiConnection = (HttpURLConnection) url.openConnection();
			apiConnection.setRequestMethod("GET");
			System.out.println(apiConnection.getResponseCode());

			if (apiConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED
					|| apiConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));

				String inputline;
				StringBuffer html = new StringBuffer();

				while ((inputline = in.readLine()) != null) {
					html.append(inputline);
				}
				in.close();

				jsonObj = new JSONObject(html.toString().trim());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
			return null;
		}

		try {
			jsonObj = jsonObj.getJSONObject("alt_fuel_station");
			sb.append(jsonObj.getString("street_address") + ", ");
			sb.append(jsonObj.getString("city") + ", ");
			sb.append(jsonObj.getString("state") + ", ");
			sb.append(jsonObj.getString("zip"));

		} catch (JSONException e) {
			System.out.println(e.getMessage());
			return null;
		}

		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * This method stores the data item passed to the data file
	 * 
	 * @param item
	 */
	public void storeItem(String item) {
		File file = new File("dataFile.txt");
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(item);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException ignore) {
				}
		}
	}
/**
 * This method reads the dataFile and returns the first line of the file
 * @return string
 */
	public String getStoredItem() {

		try {
			FileReader fr = new FileReader("dataFile.txt");
			BufferedReader textReader = new BufferedReader(fr);
			String value = textReader.readLine();
			textReader.close();
			return value;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
