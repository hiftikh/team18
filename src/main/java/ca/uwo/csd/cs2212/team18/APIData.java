package ca.uwo.csd.cs2212.team18;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import com.github.scribejava.apis.FitbitApi20;
import com.github.scribejava.apis.service.FitbitOAuth20ServiceImpl;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * The purpose of this class is to create objects that will access the
 * Fitbit's API servers via Oauthentication 2 and will parse through the resulting
 * JSON texts and store the various values for each of the object's attributes
 * @author team 18
 *
 */
public class APIData extends Data{

	/* TODO Notes:
	 * - make separate test class that overrides ApiData Class 
	 * - Make these into a linked list so that you will not need to manually name them(?)
	 * 	make them in the constructor
	 * - //Create separate private methods to better categorize this(?) is it
	 * 	even useful since i am going to be splitting this anyways
	 */

	static final String REQUEST_URL_PREFIX = "https://api.fitbit.com/1/user/3WGW2P/";
	static final String CALL_BACK_URL="http://localhost:8080";
	static final String SCOPE = "activity%20heartrate";

	/*// Initializes constants used to store for default APIData constructor
	// This will be used if the program is using test values
	private static final int DEFAULT_STEPS = 10042;
	private static final int DEFAULT_FLOORS = 27;
	private static final double DEFAULT_DISTANCE = 7.52;
	private static final int DEFAULT_ACTIVE_MINUTES = 265;
	private static final int DEFAULT_SEDENTARY_MINUTES = 1175;
	private static final int DEFAULT_CALORIES_OUT = 2565;
	private static final double DEFAULT_BEST_DISTANCE = 10.0593;
	private static final String DEFAULT_BEST_DISTANCE_DATE = "2016-01-06";
	private static final int DEFAULT_BEST_FlOORS = 26;
	private static final String DEFAULT_BEST_FLOORS_DATE = "2016-01-08";
	private static final int DEFAULT_BEST_STEPS = 13685;
	private static final String DEFAULT_BEST_STEPS_DATE = "2016-01-06";
	private static final double DEFAULT_TOTAL_DISTANCE = 202.95;
	private static final int DEFAULT_TOTAL_FLOORS = 560;
	private static final int DEFAULT_TOTAL_STEPS = 272799;
	private static final int DEFAULT_RESTING_HEART_RATE = 71;
	private static final int DEFAULT_OUT_OF_RANGE_VALUE = 422;
	private static final int DEFAULT_OUT_OF_RANGE_MIN = 30;
	private static final int DEFAULT_OUT_OF_RANGE_MAX = 94;
	private static final int DEFAULT_FAT_BURN_VALUE = 230;
	private static final int DEFAULT_FAT_BURN_MIN = 94;
	private static final int DEFAULT_FAT_BURN_MAX = 131;
	private static final int DEFAULT_CARDIO_VALUE = 12;
	private static final int DEFAULT_CARDIO_MIN = 131;
	private static final int DEFAULT_CARDIO_MAX = 159;
	private static final int DEFAULT_PEAK_VALUE = 12;
	private static final int DEFAULT_PEAK_MIN = 159;
	private static final int DEFAULT_PEAK_MAX = 220;*/

	// String variables that will store the different urls that will access the information
	// from the APIData
	private String requestUrlActivities;
	private String requestUrlHeartRate;
	private String requestUrlBestLife;
	private String requestUrlRecentActivities;


	// Variables used to store authentication information
	private OAuth2AccessToken accessToken;
	private FitbitOAuth20ServiceImpl service;
	private Response response;


	//private String apiKey = null;
	private String apiSecret = null;
	private String clientID = null;
	private String accessTokenItself = null;
	private String tokenType = null;
	private String refreshToken = null;
	private Long expiresIn = null;
	private String rawResponse = null;


	/**
	 * Default constructor that creates an APIData object
	 * that has test attributes
	 */
	/*public APIData(){
		// Sets default values to the object's attributes
		caloriesOut.setValue(DEFAULT_CALORIES_OUT);
		floors.setValue(DEFAULT_FLOORS);
		steps.setValue(DEFAULT_STEPS);
		actMin.setValue(DEFAULT_ACTIVE_MINUTES);
		sedMin.setValue(DEFAULT_SEDENTARY_MINUTES);
		distance.setValue((int)DEFAULT_DISTANCE);
		bestDistance.setValue((int)DEFAULT_BEST_DISTANCE);
		bestDistance.setDate(DEFAULT_BEST_DISTANCE_DATE);
		bestFloors.setValue(DEFAULT_BEST_FlOORS);
		bestFloors.setDate(DEFAULT_BEST_FLOORS_DATE);
		bestSteps.setValue(DEFAULT_BEST_STEPS);
		bestSteps.setDate(DEFAULT_BEST_STEPS_DATE);
		totalDistance.setValue((int)DEFAULT_TOTAL_DISTANCE);
		totalFloors.setValue(DEFAULT_TOTAL_FLOORS);
		totalSteps.setValue(DEFAULT_TOTAL_STEPS);
		restingHeartRate.setValue(DEFAULT_RESTING_HEART_RATE);
		outOfRange.setValue(DEFAULT_OUT_OF_RANGE_VALUE);
		outOfRange.setMin(DEFAULT_OUT_OF_RANGE_MIN);
		outOfRange.setMax(DEFAULT_OUT_OF_RANGE_MAX);
		fatBurn.setValue(DEFAULT_FAT_BURN_VALUE);
		fatBurn.setMin(DEFAULT_FAT_BURN_MIN);
		fatBurn.setMax(DEFAULT_FAT_BURN_MAX);
		cardio.setValue(DEFAULT_CARDIO_VALUE);
		cardio.setMin(DEFAULT_CARDIO_MIN);
		cardio.setMax(DEFAULT_CARDIO_MAX);
		peak.setValue(DEFAULT_PEAK_VALUE);
		peak.setMin(DEFAULT_PEAK_MIN);
		peak.setMax(DEFAULT_PEAK_MAX);

	}
	 */

	/**
	 * This constructor allows a date to be used as a parameter in order to access the Fitbit's 
	 * API for a specific date
	 * @param date The String parameter date is used to choose the date that the APIData class will
	 * access from Fitbit API servers in order to fill the object's attributes
	 */
	public APIData(String date){
		readFiles();

		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
				.apiKey(clientID)       //fitbit uses the clientID here
				.apiSecret(apiSecret)
				.callback(CALL_BACK_URL)
				.scope(SCOPE)
				.grantType("authorization_code")
				.build(FitbitApi20.instance());

		//  The access token contains everything you will need to authenticate your requests
		//  It can expire - at which point you will use the refresh token to refresh it
		//    I have authenticated and given you the contents of the response to use
		accessToken = new OAuth2AccessToken(
				accessTokenItself,
				tokenType,
				refreshToken,
				expiresIn,
				rawResponse);

		// Method that will create the request urls based on the date
		createRequests(date);
	}



	private void createRequests(String date){
		// Builds the strings to request for specific information from the API
		requestUrlActivities = REQUEST_URL_PREFIX + "activities/date/" + date + ".json";
		requestUrlHeartRate = REQUEST_URL_PREFIX + "activities/heart/date/" + date + "/1d.json";
		requestUrlBestLife = REQUEST_URL_PREFIX + "activities.json";
		requestUrlRecentActivities = REQUEST_URL_PREFIX + "activities/recent.json";
		// Calls methods that will pull information from the API and store them
		// in the appropriate attributes
		setActivities();
		setBestLife();
		setHeartRate();
		setRecentActivity();
		save();

	}


	protected void readFiles(){
		BufferedReader bR = null;
		try {

			// Stores various user authentication information variables from
			// the Team18Credentials.txt and Team18Tokens.txt files
			FileReader fileReader =
					new FileReader("src/main/resources/Team18Credentials.txt");   
			bR = new BufferedReader(fileReader);
			clientID = bR.readLine();
			bR.readLine();
			apiSecret = bR.readLine();
			bR.close();
			fileReader = new FileReader("src/main/resources/Team18Tokens.txt");
			bR = new BufferedReader(fileReader);

			accessTokenItself = bR.readLine();
			tokenType = bR.readLine();
			refreshToken = bR.readLine();
			expiresIn = Long.parseLong(bR.readLine());
			rawResponse = bR.readLine();

		}
		// Handles exception if the file is not found
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
		// Handles exception if the file's text formating is incorrect
		catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());  
			System.exit(1);
		}
		finally{
			try{
				if (bR!=null)
					// Always close files.
					bR.close(); 
			}
			catch(Exception e){
				System.out.println(
						"Error closing file\n"+e.getMessage()); 
			}
		}
	}

	private void writeFiles() {
		BufferedWriter bW = null;
		try {
			// Will store the new access/refresh tokens in the 
			// appropriate file
			FileWriter fileWriter = 
					new FileWriter("src/main/resources/Team18Tokens.txt");
			bW = new BufferedWriter(fileWriter);
			bW.write(accessToken.getToken());
			bW.newLine();
			bW.write(accessToken.getTokenType());
			bW.newLine();
			bW.write(accessToken.getRefreshToken());
			bW.newLine();
			bW.write(accessToken.getExpiresIn().toString());
			bW.newLine();
			bW.write(accessToken.getRawResponse());
			bW.newLine();
			bW.close();
		} catch(FileNotFoundException ex){
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
		} catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());   
		} finally {
			try {
				if (bW != null)
					bW.close(); 
			} catch(Exception e) {
				System.out.println(
						"Error closing file\n"+e.getMessage());
			}
		}
	}

	private void save(){
		BufferedWriter bW = null;
		try {
			FileWriter fileWriter =
					new FileWriter("src/main/resources/Data.txt");
			bW = new BufferedWriter(fileWriter);
			bW.write(String.valueOf(caloriesOut.getValue()));
			bW.newLine();
			bW.write(String.valueOf(floors.getValue()));
			bW.newLine();
			bW.write(String.valueOf(steps.getValue()));
			bW.newLine();
			bW.write(String.valueOf(actMin.getValue()));
			bW.newLine();
			bW.write(String.valueOf(sedMin.getValue()));
			bW.newLine();
			bW.write(String.valueOf(distance.getValue()));
			bW.newLine();
			bW.write(String.valueOf(bestDistance.getValue()));
			bW.newLine();
			bW.write(bestDistance.getDate());
			bW.newLine();
			bW.write(String.valueOf(bestFloors.getValue()));
			bW.newLine();
			bW.write(bestFloors.getDate());
			bW.newLine();
			bW.write(String.valueOf(bestSteps.getValue()));
			bW.newLine();
			bW.write(bestSteps.getDate());
			bW.newLine();
			bW.write(String.valueOf(totalDistance.getValue()));
			bW.newLine();
			bW.write(String.valueOf(totalFloors.getValue()));
			bW.newLine();
			bW.write(String.valueOf(totalSteps.getValue()));
			bW.newLine();
			bW.write(String.valueOf(restingHeartRate.getValue()));
			bW.newLine();
			bW.write(String.valueOf(outOfRange.getValue()));
			bW.newLine();
			bW.write(String.valueOf(fatBurn.getValue()));
			bW.newLine();
			bW.write(String.valueOf(cardio.getValue()));
			bW.newLine();
			bW.write(String.valueOf(peak.getValue()));
			bW.newLine();
			for (int i =0; i < recentActivities.length; i++){
				bW.write(recentActivities[i].getType());
				bW.newLine();
				bW.write(String.valueOf(recentActivities[i].getValue()));
				bW.newLine();
				bW.write(String.valueOf(recentActivities[i].getMets()));
				bW.newLine();
			}
			bW.close();
		} catch(FileNotFoundException ex){
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
		} catch(IOException ex) {
			System.out.println(
					"Error reading/write file\n"+ex.getMessage());   
		} finally {
			try {
				if (bW != null)
					bW.close(); 
			} catch(Exception e) {
				System.out.println(
						"Error closing file\n"+e.getMessage());
			}
		}
	}
	/**
	 * The api method is a private helper method that will access the Fitbit's API
	 * via OAuthentication and will store the JSON objects that are returned
	 * @param requestUrl The String parameter that stores the specific URL that the 
	 * api method should access in order to store the JSON object that will contain the APIData's
	 * attributes
	 */
	private void api(String requestUrl) {
		// Initializes OAuthRequest object specific to the passed in URL
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		service.signRequest(accessToken, request);

		response = request.send();

		System.out.println("HTTP response code: " + response.getCode());
		int statusCode = response.getCode();

		/*
		 * 200: Authentication successful
		 * 400: Invalid URL request
		 * 401: Possibly expired tokens, will attempt to gain new 
		 * 		pair by using refresh token
		 * 429: Too many API requests have been issued
		 */
		switch(statusCode){
		case 200:
			System.out.println("Success");
			break;
		case 400:
			System.out.println("Bad Request");
			break;
		case 401:
			System.out.println("Likely Expired Token");
			accessToken = service.refreshOAuth2AccessToken(accessToken);
			request = new OAuthRequest(Verb.GET, requestUrl, service);
			service.signRequest(accessToken, request);
			response = request.send();
			writeFiles();
			break;
		case 429:
			System.out.println("Rate limit exceeded");
			break;
		}
	}

	/**
	 * The setActivites method is a private helper method that will parse through
	 * the current response body that contains the JSON information for the APIData object's
	 * Activity attributes.
	 * This includes the attributes Calories Out, Floors, Steps, Active Minutes, Sedentary
	 * Minutes, and Distance 
	 */
	protected void setActivities(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			api(requestUrlActivities);
			JSONObject jsonObj = new JSONObject(response.getBody());
			JSONArray jsonArray;
			jsonObj = jsonObj.getJSONObject("summary");
			caloriesOut.setValue(jsonObj.getInt("caloriesOut"));
			floors.setValue(jsonObj.getInt("floors"));
			steps.setValue(jsonObj.getInt("steps"));
			actMin.setValue(jsonObj.getInt("fairlyActiveMinutes") + jsonObj.getInt("lightlyActiveMinutes") + jsonObj.getInt("veryActiveMinutes"));
			sedMin.setValue(jsonObj.getInt("sedentaryMinutes"));
			jsonArray = jsonObj.getJSONArray("distances");
			jsonObj = jsonArray.getJSONObject(0);
			distance.setValue((int)jsonObj.getDouble("distance"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The setBestLife method is a private helper method that will parse through the
	 * current response body that contains the JSON information for the APIData object's
	 * Best Dates and Total Life activities.
	 * This includes the attributes Best Distance, Best Steps, Best Floors, Total Distance,
	 * Total Steps, and Total Floors
	 */
	protected void setBestLife(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			api(requestUrlBestLife);
			JSONObject jsonBest, jsonLife, jsonDistance, jsonSteps, jsonFloors;
			JSONObject jsonObj = new JSONObject(response.getBody());
			jsonBest = jsonObj;
			jsonBest = jsonBest.getJSONObject("best");
			jsonBest = jsonBest.getJSONObject("total");
			jsonDistance = jsonBest.getJSONObject("distance");
			jsonSteps = jsonBest.getJSONObject("steps");
			jsonFloors = jsonBest.getJSONObject("floors");
			bestDistance.setValue(jsonDistance.getInt("value"));
			bestDistance.setDate(jsonDistance.getString("date"));
			bestSteps.setValue(jsonSteps.getInt("value"));
			bestSteps.setDate(jsonSteps.getString("date"));
			bestFloors.setValue(jsonFloors.getInt("value"));
			bestFloors.setDate(jsonFloors.getString("date"));
			jsonLife = jsonObj;
			jsonLife = jsonLife.getJSONObject("lifetime");
			jsonLife = jsonLife.getJSONObject("total");
			totalDistance.setValue(jsonLife.getInt("distance"));
			totalSteps.setValue(jsonLife.getInt("steps"));
			totalFloors.setValue(jsonLife.getInt("floors"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The setHeartRate method is a private helper method that will parse through the
	 * current response body that contains the JSON information for the APIData object's
	 * Heart Rate information.
	 * This includes the attributes Resting Heart Rate, Out of Range Zone, Fat Burn Zone,
	 * Cardio Zone, and Peak Zone
	 */
	protected void setHeartRate(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			api(requestUrlHeartRate);
			JSONObject jsonObj = new JSONObject(response.getBody());
			JSONObject jsonOutOfRange, jsonFatBurn, jsonCardio,  jsonPeak; 
			JSONArray jsonArray;
			jsonArray = jsonObj.getJSONArray("activities-heart");
			jsonObj = jsonArray.getJSONObject(0);
			jsonObj = jsonObj.getJSONObject("value");
			restingHeartRate.setValue(jsonObj.getInt("restingHeartRate"));
			restingHeartRate.setValue(0);
			jsonArray = jsonObj.getJSONArray("heartRateZones");
			jsonOutOfRange = jsonArray.getJSONObject(0);
			setHRObject(outOfRange, jsonOutOfRange);
			jsonFatBurn = jsonArray.getJSONObject(1);
			setHRObject(fatBurn, jsonFatBurn);
			jsonCardio = jsonArray.getJSONObject(2);
			setHRObject(cardio, jsonCardio);
			jsonPeak = jsonArray.getJSONObject(3);
			setHRObject(peak, jsonPeak);

		} catch (JSONException e) {
			restingHeartRate.setValue(-1);
			outOfRange.setValue(-1);
			fatBurn.setValue(-1);
			cardio.setValue(-1);
			peak.setValue(-1);
		}
	}

	protected void setRecentActivity() {
		try{
			api(requestUrlRecentActivities);
			final String REQUEST_URL_RECENT_ACTIVITY_PREFIX = "https://api.fitbit.com/1/activities/";
			String requestUrlRecentActivity;
			JSONArray jsonArray2 = new JSONArray(response.getBody());
			JSONArray jsonArray;
			JSONObject jsonObj;
			for (int i = 0; i < recentActivities.length; i++){
				jsonObj = jsonArray2.getJSONObject(i);
				recentActivities[i] = new FitCalcActivity(jsonObj.getString("name"),jsonObj.getInt("activityId"));
				requestUrlRecentActivity = REQUEST_URL_RECENT_ACTIVITY_PREFIX + recentActivities[i].getValue() + ".json";
				api(requestUrlRecentActivity);
				try {
					jsonObj = new JSONObject(response.getBody());
					jsonObj = jsonObj.getJSONObject("activity");
					jsonArray = jsonObj.getJSONArray("activityLevels");
					jsonObj = jsonArray.getJSONObject(0);
					recentActivities[i].setMets(jsonObj.getDouble("mets"));
				}catch (JSONException e){
					recentActivities[i].setMets(jsonObj.getDouble("mets"));
				}
			}
		}catch (JSONException e){
			e.printStackTrace();
		}
	}

	/**
	 * The setHRObject method is a private helper method that will pass in a Heart Rate Zone object to be
	 * filled as well as a JSONObject that will be used to extract the values that will fill
	 * the attributes.
	 * @param obj This parameter will be the HeartRateZone object to be filled with the values
	 * from the JSONObject
	 * @param jsonObj This parameter will be the JSONObject that contains the JSON text that contains
	 * the appropriate values to be stored in the HeartRateZone object that is passed in
	 */
	private void setHRObject(HeartRateZone obj, JSONObject jsonObj) {
		try {
			// Sets the attributes of the heart rate zone object using the JSON object
			obj.setMin(jsonObj.getInt("min"));
			obj.setMax(jsonObj.getInt("max"));
			obj.setValue(jsonObj.getInt("minutes"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The refresh method will refresh the attributes of the APIData object with 
	 * information and values from a different date
	 * @param date This parameter is a String which indicates the new date which 
	 * should be now pulled from the Fitbit's API server
	 * @return The current time in the format of HH:mm:ss in order to indicate
	 * the time that a refresh for new information was requested
	 */
	protected String refresh(String date){

		// Calls the methods that will pull information and store them in the
		// appropriate attributes
		createRequests(date);

		return super.refresh();
	}

	public static void main (String args[]){
		APIData api = new APIData("2016-02-25");
	}
}


