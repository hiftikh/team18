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

public class ApiData {

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


	private static String CALL_BACK_URL="http://localhost:8080";
	private static int CALL_BACK_PORT=8080;

	private OAuth2AccessToken accessToken;
	private FitbitOAuth20ServiceImpl service;
	private Response response;


	private Activity caloriesOut = new Activity("Calories Out");
	private Activity floors = new Activity("Floors");
	private Activity steps = new Activity("Steps");
	private Activity actMin = new Activity("Active Minutes");
	private Activity sedMin = new Activity("Sedentary Minutes");
	private Activity distance = new Activity("Distance");

	private BestActivity bestDistance = new BestActivity("Distance");
	private BestActivity bestFloors = new BestActivity("Floors");
	private BestActivity bestSteps = new BestActivity("Steps");

	private Activity totalDistance = new Activity("Distance");
	private Activity totalFloors = new Activity("Floors");
	private Activity totalSteps = new Activity("Steps");

	private JSONObject jsonObj;
	private JSONArray jsonArray;

	public ApiData(){
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
	}

	public ApiData(String date){

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
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file\n"+ex.getMessage());
			System.exit(1);
		}
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

		String requestUrlPrefix = "https://api.fitbit.com/1/user/3WGW2P/";
		String requestUrlActivities = requestUrlPrefix + "activities/date/" + date + ".json";
		String requestUrlHeartRate = requestUrlPrefix + "activities/heart/date/" + date + "/1d.json";
		String requestUrlBestLife = requestUrlPrefix + "activities.json";

		api(requestUrlActivities);
		setActivities();
		api(requestUrlBestLife);
		setBestLife();
		api(requestUrlHeartRate);


		BufferedWriter bW = null;
		try {
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



	private void api(String requestUrl) {
		OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl, service);

		service.signRequest(accessToken, request);

		response = request.send();

		System.out.println("HTTP response code: " + response.getCode());
		int statusCode = response.getCode();

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

	private void setActivities(){
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
	private void setBestLife(){
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

	private void setHeartRate(){

	}

	public String refresh(String date, ApiData api){
		api = new ApiData(date);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public Activity getCaloriesOut() {
		return caloriesOut;
	}

	public Activity getFloors() {
		return floors;
	}

	public Activity getSteps() {
		return steps;
	}

	public Activity getActMin() {
		return actMin;
	}

	public Activity getSedMin() {
		return sedMin;
	}

	public Activity getDistance() {
		return distance;
	}
	public BestActivity getBestDistance() {
		return bestDistance;
	}
	public BestActivity getBestFloors() {
		return bestFloors;
	}
	public BestActivity getBestSteps() {
		return bestSteps;
	}
	public Activity getTotalDistance() {
		return totalDistance;
	}
	public Activity getTotalFloors() {
		return totalFloors;
	}
	public Activity getTotalSteps() {
		return totalSteps;
	}
	
	public static void main(String args[]){
		ApiData api = new ApiData("2016-01-01");
		api.refresh("2016-01-02",api);
	}
}

