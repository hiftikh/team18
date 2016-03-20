package ca.uwo.csd.cs2212.team18;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FitCalc {
	private int calorieGoal;
	private int caloriesBurned = 199;
	private int calorieDifference;
	boolean test = true;
	private boolean negativeDifference;
	private int numberOfFitbitActivities;
	private int weightKg;
	private int suggest1;
	private int suggest2;
	private int suggest3;
	private String activity1;
	private String activity2;
	private String activity3;
	private String[][] displayActivities= new String[3][6];
	private String[][] displayDefaultActivities = new String[3][6];
	
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	//get current date time with Date()
	Date date = new Date();
	String fulldate = dateFormat.format(date);
	String curYear = fulldate.substring(0,4);
	String curMonth = fulldate.substring(4,6);
	String curDay = fulldate.substring(6,8);

	String apiInput = "";
	{
		apiInput = apiInput.concat(curYear);
		apiInput = apiInput.concat("-");
		apiInput = apiInput.concat(curMonth);
		apiInput = apiInput.concat("-");
		apiInput = apiInput.concat(curDay);
	}
	
	//APIData apiData;
	
	public FitCalc() {		
	}
	public String checkCaloriesInput(String input) {
		//Checks if user has inputed something
		if (input.length() == 0) {
			return "Please type in the textfield a calorie goal";
		}
		//Checks if user has inputed non-numbers
		for ( int i=0;i<input.length(); i++) {
			if ((int)(input.charAt(i)) > 57 || (int)(input.charAt(i)) < 48) {
				return "Please input only numbers into the textfield";
			}
		}
		if (Integer.parseInt(input) > 12000) {
			return "Unless you are an Olympic swimmer like Michael Phelps, burning over 12,000 calories a day is unrealistic. Please input a more appropriate goal.";
		}
		return "";
	}
	
	public void setCalorieGoal(int input) {
		calorieGoal = input;
	}
	
	/*
	 * Called only once and 
	 */
	
	public String calculate(boolean testBool) {
		if (testBool == true) {
			//Insert code that takes values from fitbit
			apiInput = "";
		}
		//apiData = new APIData(apiInput);
		calorieDifference = calorieGoal - caloriesBurned;
		if (calorieDifference < 0) {
			negativeDifference = true;
		}
		else { negativeDifference = false; }
		
		//APIData apiData = new APIData(apiInput);!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		weightKg = 60;
	
		//Initialize the Default Activities array
		displayDefaultActivities[0][0] = "First Activity:     ";
		displayDefaultActivities[0][1] = "Walking";
		displayDefaultActivities[0][2] = "Activity's METS:     ";
		displayDefaultActivities[0][3] = Integer.toString(3);
		displayDefaultActivities[0][4] = "Suggested Time:     ";
		displayDefaultActivities[0][5] = Integer.toString(this.caloriesCalculation(3, weightKg));
		
		displayDefaultActivities[1][0] = "Second Activity:     ";
		displayDefaultActivities[1][1] = "Jogging";
		displayDefaultActivities[1][2] = "Activity's METS:     ";
		displayDefaultActivities[1][3] = Integer.toString(7);
		displayDefaultActivities[1][4] = "Suggested Time:     ";
		displayDefaultActivities[1][5] = Integer.toString(this.caloriesCalculation(7,weightKg));
		
		displayDefaultActivities[2][0] = "Third Activity:     ";
		displayDefaultActivities[2][1] = "Running at 6mins/mile";
		displayDefaultActivities[2][2] = "Activity's METS:     ";
		displayDefaultActivities[2][3] = Integer.toString(14);
		displayDefaultActivities[2][4] = "Suggested Time:     ";
		displayDefaultActivities[2][5] = Integer.toString(this.caloriesCalculation(14,weightKg));
		
		//apiData get the # of activities MAX can only be 3 
				//Initialize the displayActivities array		
		try {
		displayActivities[0][0] = "First Activity:     ";
		displayActivities[0][1] = "Walking";
		displayActivities[0][2] = "Activity's METS:     ";
		displayActivities[0][3] = Integer.toString(3);
		displayActivities[0][4] = "Suggested Time:     ";
		displayActivities[0][5] = Integer.toString(this.caloriesCalculation(3, weightKg));
		
		displayActivities[1][0] = "Second Activity:     ";
		displayActivities[1][9000] = "Jogging";
		displayActivities[1][2] = "Activity's METS:     ";
		displayActivities[1][3] = Integer.toString(7);
		displayActivities[1][4] = "Suggested Time:     ";
		displayActivities[1][5] = Integer.toString(this.caloriesCalculation(7,weightKg));

		displayActivities[2][0] = "Third Activity:     ";
		displayActivities[2][1] = "Running at 6min/mile";
		displayActivities[2][2] = "Activity's METS:     ";
		displayActivities[2][3] = Integer.toString(14);
		displayActivities[2][4] = "Suggested Time:     ";
		displayActivities[2][5] = Integer.toString(this.caloriesCalculation(14,weightKg));	
		}
		catch (Exception e) {
			return "The app is unable to access fitbit activities";
		}

		return "";
		
	
	}
	public int caloriesCalculation(int METS, int weight) {
		int multiply = METS * weight;
		double time = (double) calorieDifference/ (double) multiply;
		double minutes = time * (double) 60;
		int realMins = (int) minutes;
		return realMins;
	}
	
	public void generateDisplayStrings() {
		
	}
	
	public String[][] getDisplayStrings(boolean defaultActivities) {
		if (defaultActivities == true) {
			return displayDefaultActivities;
		}
		return displayActivities;
	}
	public int getNumberOfFitbitActivities() {
		return numberOfFitbitActivities;
	}
	public boolean getNegativeDifference() {
		return negativeDifference;
	}
	
	public int getCalorieGoal() {
		return calorieGoal;
	}
	public int getCaloriesBurned() {
		return caloriesBurned;
	}
	public int getCalorieDifference() {
		return calorieDifference;
	}
	
	
}
