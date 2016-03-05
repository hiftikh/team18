package ca.uwo.csd.cs2212.team18;

public class HeartRateZone extends Activity{
	
	private int min;
	private int max;
	private String description;

	public HeartRateZone(String type){
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
