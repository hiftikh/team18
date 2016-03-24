package ca.uwo.csd.cs2212.team18;

public class heartRateZoneBackend {
	
	//creates the API Object
	APIData api;
	public heartRateZoneBackend() {
		
	}
	
	public String getRestingHR() {
		int val = api.getRestingHeartRate().getValue();
		return Integer.toString(val);
	}
	
	public String getOutOfRangeHR() {
		int val = api.getOutOfRange().getValue();
		return Integer.toString(val);
	}
	
	public String getFatBurnHR() {
		int val = api.getFatBurn().getValue();
		return Integer.toString(val);
	}
	
	public String getCardioHR() {
		int val = api.getCardio().getValue();
		return Integer.toString(val);
	}
	
	public String getPeakHR() {
		int val = api.getPeak().getValue();
		return Integer.toString(val);
	}
	
	

}