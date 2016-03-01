package ca.uwo.csd.cs2212.team18;

public class HeartRate extends Activity{
	
	private int min;
	private int max;
	
	public HeartRate(String type){
		super(type);
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
