package ca.uwo.csd.cs2212.team18;
public class Accolades {

	/**
	 * 
	 */
	public Accolades(){
		
	}
	
	/**
	 * 
	 */
	//static Boolean[] comp = new Boolean[20];
	
	/**
	 * Complete method that iterates through a boolean array 
	 * in order to determine which accolades have been met, and 
	 * which accolades have not been met
	 * @param api The API object used to get data
	 * @return Boolean array with true/false values set to determine which goals have been met
	 */
	public static Boolean[] complete(Data api, Boolean test){
		
		if(test == true){
			api = new TestData();
		}
		else{
			api = new APIData(BaseDashBoard.getCurrentDate());
		}
		final Boolean[] comp = new Boolean[20];
		
		// 10 floors?
		if(api.getTotalFloors().getValue() >= 10){
			comp[1] = true;
		}
		
		// Else The Accolade was not met
		else{	
			comp[1] = false;
		}
		
		// Dubai [Burj Khalifa] (272 floors)
		if(api.getTotalFloors().getValue() >= 272){
			comp[0] = true;
		}
		
		// Else The Accolade was not met
		else{	
			comp[0] = false;
		}
		
		// Climb 1000 floors
		if(api.getTotalFloors().getValue() >= 1000){
			comp[4] = true;
		}
		
		// Else The Accolade was not achieved
		else{
			comp[4] = false;
		}
		
		// Stairway to Heaven (all floor accolades)
		if(comp[0] == true && comp[1] == true && comp[4] == true){	
			comp[2] = true;
		}
		
		// Else The Accolade was not achieved
		else{
			comp[2] = false;
		}
		
		// Left the House (200 steps)
		if(api.getTotalSteps().getValue() >= 200){
			comp[3] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[3] = false;
		}
	
		// Breaking into your shoes (1000 steps)
		if(api.getTotalSteps().getValue() >= 1000){			
			comp[17] = true;
		}
		
		// Else the Accolade was not achieved
		else{			
			comp[17] = false;
		}

		// Over 9000 (9000 steps)
		if(api.getTotalSteps().getValue() >= 9000){			
			comp[5] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[5] = false;
		}
		
		// Back to back (10000 steps)
		if(api.getTotalSteps().getValue() >= 10000){
			comp[6] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[6] = false;
		}
		
		// Walked around the earth (131480184 steps)
		if(api.getTotalSteps().getValue() >= 131480184){
			comp[12] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[12] = false;
		}
		
		// Cereal Killer (379 calories)
		if(api.getCaloriesOut().getValue() >= 379){
			comp[9] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[9] = false;
		}
		
		// The beginning of the end (1000 calories)
		if(api.getCaloriesOut().getValue() >= 1000){
			comp[7] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[7] = false;
		}
		
		// Devilish Delight (2420 calories)
		if(api.getCaloriesOut().getValue() >= 2420){
			comp[8] = true;
		}
		
		// Else the Accolade was not achieved
		else{
			comp[8] = false;
		}
		
		// Fat Burner (3500 calories)
		if(api.getCaloriesOut().getValue() >= 3500){
			comp[13] = true;
		}
		
		// Else the accolade was not met
		else{
			comp[13] = false;
		}
		
		// Cold Hearted Murderer (5200 calories)
		if(api.getCaloriesOut().getValue() >= 5200){
			comp[10] = true;
		}
		
		// Else the accolade was not met
		else{
			comp[10] = false;
		}

		// Mass Destruction (7500 calories)
		if(api.getCaloriesOut().getValue() >= 7500){
			comp[14] = true;
		}
		
		// Else the accolade was not met 
		else{
			comp[14] = false;
		}

		// Walked a mile (1.60934 km)
		if(api.getTotalDistance().getValue() >= 1.60934){
			comp[15] = true;
		}
		
		// Else the accolade was not met
		else{
			comp[15] = false;
		}

		// From Fanshawe to Western (6.2 km)
		if(api.getTotalDistance().getValue() >= 6.2){
			comp[18] = true;
		}
		
		// Else the goal was not met
		else{	
			comp[18] = false;
		}
		
		// Masonville to White Oaks (12.2 km)
		if(api.getTotalDistance().getValue() >= 12.2){
			comp[19] = true;
		}
		
		// Else the accolade was not met
		else{	
			comp[19] = false;
		}
		
		// Distance around the world (40075 km)
		if(api.getTotalDistance().getValue() >= 40075){
			comp[11] = true;
		}
		
		// Else the accolade was not met
		else{
			comp[11] = false;
		}
		
		// Distance to the moon (384400 km)
		if(api.getTotalDistance().getValue() >= 384400){
			comp[16] = true;
		}
		
		// Else the accolade was not met
		else{
			comp[16] = false;
		}
		// Return the Boolean array to determine which goals have been met
		return comp;
	}
	
}
