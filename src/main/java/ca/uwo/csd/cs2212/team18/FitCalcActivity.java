package ca.uwo.csd.cs2212.team18;

public class FitCalcActivity extends Activity {
	private double mets;
	
	public FitCalcActivity(String type, int value){
		super(type);
		this.setValue(value);
	}

	
	public void setMets(double mets) {
		this.mets = mets;
	}


	public double getMets() {
		return mets;
	}
}
