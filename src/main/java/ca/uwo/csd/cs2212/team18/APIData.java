package ca.uwo.csd.cs2212.team18;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Calendar;
import java.text.SimpleDateFormat;


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
 * @author Sam Ali-mirsalari
 *
 */
public class APIData {

	/* Notes:
	 * - make separate test class that overrides ApiData Class
	 * - Make these into a linked list so that you will not need to manually name them(?)
	 * 	make them in the constructor
	 * - //Create separate private methods to better categorize this(?) is it
	 * 	even useful since i am going to be splitting this anyways
	 */


	// Initializes constants used to store for default APIData constructor
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
	private static final int DEFAULT_PEAK_MAX = 220;


	// String variables that will store the different urls that will access the information
	// from the APIData
	private String requestUrlActivities;
	private String requestUrlHeartRate;
	private String requestUrlBestLife;

	// Constants that store important user and authentication information
	private static String REQUEST_URL_PREFIX = "https://api.fitbit.com/1/user/3WGW2P/";
	private static String CALL_BACK_URL="http://localhost:8080";
	private static int CALL_BACK_PORT=8080;

	// Variables used to store authentication information
	private OAuth2AccessToken accessToken;
	private FitbitOAuth20ServiceImpl service;
	private Response response;


	// Initializes activity variables to store the Activity information
	private Activity caloriesOut = new Activity("Calories Out");
	private Activity floors = new Activity("Floors");
	private Activity steps = new Activity("Steps");
	private Activity actMin = new Activity("Active Minutes");
	private Activity sedMin = new Activity("Sedentary Minutes");
	private Activity distance = new Activity("Distance");

	// Initializes Activity object that stores the resting heart rate
	private Activity restingHeartRate = new Activity("Resting Heart Rate");

	// Initializes BestActivity objects that will store that best activities
	private BestActivity bestDistance = new BestActivity("Distance");
	private BestActivity bestFloors = new BestActivity("Floors");
	private BestActivity bestSteps = new BestActivity("Steps");

	// Initializes Activity objects that will store the total activities
	private Activity totalDistance = new Activity("Distance");
	private Activity totalFloors = new Activity("Floors");
	private Activity totalSteps = new Activity("Steps");

	// Initializes the HeartRateZone objects that will store the various heart rate zones
	private HeartRateZone outOfRange = new HeartRateZone("Out of Range");
	private HeartRateZone fatBurn = new HeartRateZone("Fat Burn");
	private HeartRateZone cardio = new HeartRateZone("Cardio");
	private HeartRateZone peak = new HeartRateZone("Peak");

	// Initializes JSON objects to store JSON text
	private JSONObject jsonObj;
	private JSONArray jsonArray;

	// Variable that will store whether the api is in testmode or not
	private boolean isTest;

	/**
	 * Default constructor that creates an APIData object
	 * that has test attributes
	 */
	public APIData(){
		// Sets default values to the object's attributes
		isTest = true;
		caloriesOut.setValue(DEFAULT_CALORIES_OUT);
		floors.setValue(DEFAULT_FLOORS);
		steps.setValue(DEFAULT_STEPS);
		actMin.setValue(DEFAULT_ACTIVE_MINUTES);
		sedMin.setValue(DEFAULT_SEDENTARY_MINUTES);
		distance.setValue(DEFAULT_DISTANCE);
		bestDistance.setValue(DEFAULT_BEST_DISTANCE);
		bestDistance.setDate(DEFAULT_BEST_DISTANCE_DATE);
		bestFloors.setValue(DEFAULT_BEST_FlOORS);
		bestFloors.setDate(DEFAULT_BEST_FLOORS_DATE);
		bestSteps.setValue(DEFAULT_BEST_STEPS);
		bestSteps.setDate(DEFAULT_BEST_STEPS_DATE);
		totalDistance.setValue(DEFAULT_TOTAL_DISTANCE);
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
		setHRDescriptions();

	}


	/**
	 * This constructor allows a date to be used as a parameter in order to access the Fitbit's 
	 * API for a specific date
	 * @param date The String parameter date is used to choose the date that the APIData class will
	 * access from Fitbit API servers in order to fill the object's attributes
	 */
	public APIData(String date){

		// Initializes various variables
		isTest = false;
		BufferedReader bR = null;
		String line = null;
		String apiKey = null;
		String apiSecret = null;
		String clientID = null;
		String accessTokenItself = null;
		String tokenType = null;
		String refreshToken = null;
		Long expiresIn = null;
		String rawResponse = null;
		String scope = "activity%20heartrate";

		try {

			// Stores various user authentication information variables from
			// the Team18Credentials.txt and Team18Tokens.txt files
			FileReader fileReader =
					new FileReader("src/main/resources/Team18Credentials.txt");   
			bR = new BufferedReader(fileReader);
			clientID = bR.readLine();
			apiKey= bR.readLine();
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
		//  Create the Fitbit service - you will ask this to ask for access/refresh pairs
		//     and to add authorization information to the requests to the API
		service = (FitbitOAuth20ServiceImpl) new ServiceBuilder()
				.apiKey(clientID)       //fitbit uses the clientID here
				.apiSecret(apiSecret)
				.callback(CALL_BACK_URL)
				.scope(scope)
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

		// Builds the strings to request for specific information from the API
		requestUrlActivities = REQUEST_URL_PREFIX + "activities/date/" + date + ".json";
		requestUrlHeartRate = REQUEST_URL_PREFIX + "activities/heart/date/" + date + "/1d.json";
		requestUrlBestLife = REQUEST_URL_PREFIX + "activities.json";

		// Calls methods that will pull information from the API and store them
		// in the appropriate attributes
		api(requestUrlActivities);
		setActivities();
		api(requestUrlBestLife);
		setBestLife();
		api(requestUrlHeartRate);
		setHeartRate();

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
	private void setActivities(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			jsonObj = new JSONObject(response.getBody());
			jsonObj = jsonObj.getJSONObject("summary");
			caloriesOut.setValue(jsonObj.getInt("caloriesOut"));
			floors.setValue(jsonObj.getInt("floors"));
			steps.setValue(jsonObj.getInt("steps"));
			actMin.setValue(jsonObj.getInt("fairlyActiveMinutes") + jsonObj.getInt("lightlyActiveMinutes") + jsonObj.getInt("veryActiveMinutes"));
			sedMin.setValue(jsonObj.getInt("sedentaryMinutes"));
			jsonArray = jsonObj.getJSONArray("distances");
			jsonObj = jsonArray.getJSONObject(0);
			distance.setValue(jsonObj.getDouble("distance"));
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
	private void setBestLife(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			JSONObject jsonBest, jsonLife, jsonDistance, jsonSteps, jsonFloors;
			jsonObj = new JSONObject(response.getBody());
			jsonBest = jsonObj;
			jsonBest = jsonBest.getJSONObject("best");
			jsonBest = jsonBest.getJSONObject("total");
			jsonDistance = jsonBest.getJSONObject("distance");
			jsonSteps = jsonBest.getJSONObject("steps");
			jsonFloors = jsonBest.getJSONObject("floors");
			bestDistance.setValue(jsonDistance.getDouble("value"));
			bestDistance.setDate(jsonDistance.getString("date"));
			bestSteps.setValue(jsonSteps.getInt("value"));
			bestSteps.setDate(jsonSteps.getString("date"));
			bestFloors.setValue(jsonFloors.getDouble("value"));
			bestFloors.setDate(jsonFloors.getString("date"));
			jsonLife = jsonObj;
			jsonLife = jsonLife.getJSONObject("lifetime");
			jsonLife = jsonLife.getJSONObject("total");
			totalDistance.setValue(jsonLife.getDouble("distance"));
			totalSteps.setValue(jsonLife.getInt("steps"));
			totalFloors.setValue(jsonLife.getDouble("floors"));

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
	private void setHeartRate(){
		// Parses through the JSON text and save the values in the
		// appropriate attributes
		try {
			jsonObj = new JSONObject(response.getBody());
			JSONObject jsonOutOfRange, jsonFatBurn, jsonCardio,  jsonPeak; 
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
			setHRDescriptions();

		} catch (JSONException e) {
			restingHeartRate.setValue(0);
			outOfRange.setValue(0);
			outOfRange.setMin(DEFAULT_OUT_OF_RANGE_MIN);
			outOfRange.setMax(DEFAULT_OUT_OF_RANGE_MAX);
			fatBurn.setValue(0);
			fatBurn.setMin(DEFAULT_FAT_BURN_MIN);
			fatBurn.setMax(DEFAULT_FAT_BURN_MAX);
			cardio.setValue(0);
			cardio.setMin(DEFAULT_CARDIO_MIN);
			cardio.setMax(DEFAULT_CARDIO_MAX);
			peak.setValue(0);
			peak.setMin(DEFAULT_PEAK_MIN);
			peak.setMax(DEFAULT_PEAK_MAX);
			
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
	 * The setHRDescritptions method is a private helper method that will store the
	 * various Heart Rate Zone descriptions in the appropriate HeartRateZone objects.
	 */
	private void setHRDescriptions() {
		outOfRange.setDescription("When you’re out of zone, which means your heart rate"
				+ "is below 50% of maximum, your heart rate may still be elevated but not "
				+ "enough to be considered exercise.");
		fatBurn.setDescription("Fat burn zone, which means your heart rate is 50 to 69% "
				+ "of maximum, is the low-to-medium intensity exercise zone and may be a good"
				+ " place to start for those new to exercise. It’s called the fat burn zone "
				+ "because a higher percentage of calories are burned from fat, but the total "
				+ "calorie burn rate is lower.");
		cardio.setDescription("Cardio zone, which means your heart rate is 70 to 84% of maximum, "
				+ "is the medium-to-high intensity exercise zone. In this zone, you're pushing"
				+ " yourself but not straining. For most people, this is the exercise zone to target. ");
		peak.setDescription("Peak zone, which means your heart rate is greater than 85% of "
				+ "maximum, is the high-intensity exercise zone. The peak zone is for short "
				+ "intense sessions that improve performance and speed. ");
	}

	/**
	 * The refresh method will refresh the attributes of the APIData object with 
	 * information and values from a different date
	 * @param date This parameter is a String which indicates the new date which 
	 * should be now pulled from the Fitbit's API server
	 * @return The current time in the format of HH:mm:ss in order to indicate
	 * the time that a refresh for new information was requested
	 */
	public String refresh(String date){
		// Calls the methods that will pull information and store them in the
		// appropriate attributes
		if (isTest){
			api(requestUrlActivities);
			setActivities();
			api(requestUrlBestLife);
			setBestLife();
			api(requestUrlHeartRate);
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());

	}

	/**
	 * Method that will return the calories out
	 * @return The APIData's attribute Calories Out; which is an Activity type
	 */
	public Activity getCaloriesOut() {
		return caloriesOut;
	}

	/**
	 * Method that will return the floors
	 * @return The APIData's attribute floors; which is an Activity type
	 */
	public Activity getFloors() {
		return floors;
	}

	/**
	 * Method that will return the steps
	 * @return The APIData's attribute steps; which is an Activity type
	 */
	public Activity getSteps() {
		return steps;
	}

	/**
	 * Method that will return the active minutes
	 * @return The APIData's attribute active minutes; which is an Activity type
	 */
	public Activity getActMin() {
		return actMin;
	}

	/**
	 * Method that will return sedentary minutes
	 * @return The APIData's attribute sedentary minutes; which is an Activity type
	 */
	public Activity getSedMin() {
		return sedMin;
	}

	/**
	 * Method that will return distance
	 * @return The APIData's attribute distance; which is an Activity type
	 */
	public Activity getDistance() {
		return distance;
	}

	/**
	 * Method that will return the best distance
	 * @return The APIData's attribute best distance; which is a BestActivity type
	 */
	public BestActivity getBestDistance() {
		return bestDistance;
	}

	/**
	 * Method that will return the best floors
	 * @return The APIData's attribute best floors; which is a BestActivity type
	 */
	public BestActivity getBestFloors() {
		return bestFloors;
	}

	/**
	 * Method that will return the best steps
	 * @return The APIData's attribute best steps; which is a BestActivity type
	 */
	public BestActivity getBestSteps() {
		return bestSteps;
	}

	/**
	 * Method that will return the total distance
	 * @return The APIData's attribute total distance; which is an Activity type
	 */
	public Activity getTotalDistance() {
		return totalDistance;
	}

	/**
	 * Method that will return the total floors
	 * @return The APIData's attribute total floors; which is an Activity type
	 */
	public Activity getTotalFloors() {
		return totalFloors;
	}

	/**
	 * Method that will return the total steps
	 * @return The APIData's attribute total steps; which is an Activity type
	 */
	public Activity getTotalSteps() {
		return totalSteps;
	}

	/**
	 * Method that will return the resting heart rate
	 * @return The APIData's attribute resting heart rate; which is an Activity type
	 */
	public Activity getRestingHeartRate() {
		return restingHeartRate;
	}

	/**
	 * Method that will return the out of range zone
	 * @return The APIData's attribute out of range zone; which is a HeartRateZone type
	 */
	public HeartRateZone getOutOfRange() {
		return outOfRange;
	}

	/**
	 * Method that will return the fat burn zone
	 * @return The APIData's attribute fat burn zone; which is a HeartRateZone type
	 */
	public HeartRateZone getFatBurn() {
		return fatBurn;
	}

	/**
	 * Method that will return the cardio zone
	 * @return The APIData's attribute cardio; which is a HeartRateZone type
	 */
	public HeartRateZone getCardio() {
		return cardio;
	}

	/**
	 * Method that will return the peak zone
	 * @return The APIData's attribute peak; which is a HeartRateZone type
	 */
	public HeartRateZone getPeak() {
		return peak;
	}
}

