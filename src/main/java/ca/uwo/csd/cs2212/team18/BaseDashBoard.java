package ca.uwo.csd.cs2212.team18;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


//BaseDasbBoard UI will create an instance of BaseDashBoard class to handle all logical operations
public class BaseDashBoard implements Serializable{

	/**
	 * API serial version UID
	 */
	private static final long serialVersionUID = 2562196749014765959L;
	//ApiData apiData = new ApiData();

	/**
	 * ArrayList of type string to store the closed tile names
	 */
	private static ArrayList<String> closedTileNames = new ArrayList<String>();

	/**
	 * String representing the api input
	 */

	private String apiInput = "";
	private String curDate = "";
	private String curYear = "";
	private String curMonth = "";
	private String curDay = "";
	
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	//get current date time with Date()


	/**
	 * constructor to make an instance of BaseDashBoard
	 */
	public BaseDashBoard() {
	}	
	
	/**
	 * Converts the ArrayList<String> into String[]
	 * @return an array containing the contents of the ArrayList
	 */	
	public String[] getStringArrayList() {
		String[] stockArray = new String[closedTileNames.size()];
		stockArray = closedTileNames.toArray(stockArray);
		return stockArray;
	}
	/**
	 * This is the method that is called when the user presses the submit button on the JDialog that enables the user
	 * to change the date that is displayed on the dashboard.
	 * @param year
	 * @param month
	 * @param day
	 * @return Returns either an appropriate error string message or nothing if no error
	 */
	public String checkDateInput(String year, String month, String day) {
		// Check for correct input length
		if (year.length() != 4 || month.length() != 2 || day.length() != 2) {
			return "Please input date with 4 numbers in year, 2 numbers in month and 2 numbers in day";
		}
		String combine = "";
		combine = combine.concat(year);
		combine = combine.concat(month);
		combine = combine.concat(day);

		//Check for inputting actual numbers
		for ( int i=0;i<combine.length(); i++) {
			if ((int)(combine.charAt(i)) > 57 || (int)(combine.charAt(i)) < 48) {
				return "Please input a number";
			}
		}

		//Check the month validity and the day validity
		if (Integer.parseInt(month) > 12 || Integer.parseInt(month) == 00) {
			return "Month value is invalid";
		}
		if (Integer.parseInt(day) > 31 || Integer.parseInt(day) == 00) {
			return "Day value is invalid";
		}
		//Checks when certain months have 31 or 30
		int oddOrEvenMonth = Integer.parseInt(month) % 2;
		if (oddOrEvenMonth == 0 && Integer.parseInt(month) < 8 && Integer.parseInt(day) == 31) {
			return "Day value is invalid";
		}
		if (oddOrEvenMonth == 1 && Integer.parseInt(month) > 8 && Integer.parseInt(day) == 31) {
			return "Day value is invalid";
		}
		//Taking into account February
		if (Integer.parseInt(month) == 2 && (Integer.parseInt(day) == 30 || Integer.parseInt(day) == 31)) {
			return "Day value is invalid";
		}
		int yearDiv = Integer.parseInt(year) % 4;
		if (yearDiv != 0 && Integer.parseInt(day) == 29) {
			return "Day value is invalid";
		}

		//Make sure selected date is not in the future
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		//get current date time with Date()
		Date date = new Date();
		String fulldate = dateFormat.format(date);
		curYear = fulldate.substring(0,4);
		curMonth = fulldate.substring(4,6);
		curDay = fulldate.substring(6,8);

		if (Integer.parseInt(curYear) < Integer.parseInt(year)) {
			return "We can't look at data on a future date... yet. In the meantime input a date in the past";
		}
		if (Integer.parseInt(curMonth) < Integer.parseInt(month) && Integer.parseInt(curYear) == Integer.parseInt(year)) {
			return "We can't look at data on a future date... yet. In the meantime input a date in the past";
		}
		if (Integer.parseInt(curDay) < Integer.parseInt(day) && Integer.parseInt(curYear) == Integer.parseInt(year) && Integer.parseInt(curMonth) == Integer.parseInt(month)) {
			return "We can't look at data on a future date... yet. In the meantime input a date in the past";
		}



		apiInput = apiInput.concat(year);
		apiInput = apiInput.concat("-");
		apiInput = apiInput.concat(month);
		apiInput = apiInput.concat("-");
		apiInput = apiInput.concat(day);



		//ApiData apidata = new ApiData(apiInput)
		return "";
	}
	
	public String getCurrentDate() {
		curDate = curDate.concat(curYear);
		curDate = curDate.concat("-");
		curDate = curDate.concat(curMonth);
		curDate = curDate.concat("-");
		curDate = curDate.concat(curDay);
		return curDate;
	}

	/**
	 * Method that returns the selected Date by the user	
	 */
	public String getSelectedDate() {
		return apiInput;
	}

	/**
	 * This method is going to be called when user clicks the remove button on any of the 5 tiles. This will add the specific
	 * tile onto a list.
	 * @param string
	 */	
	public static void addClosedTile(String string) {
		closedTileNames.add(string);
		System.out.println(string);
	}
	/**
	 * Stores list of tiles that have been closed by the user into an external file
	 */

	public void storeClosedTileList() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("customize.dat"));
		out.writeObject(closedTileNames);
		out.close();
	}
	/**
	 * Loads the list of tiles that have been closed by the user and returns an arraylist
	 */

	public static ArrayList<String> loadClosedTileList() throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("customize.dat"));
		closedTileNames = (ArrayList<String>) in.readObject();
		in.close();

		return closedTileNames;
	}

	/**
	 * Provides the options for the ComboBox that is shown in the JDialog when the Plus Button on the Dashboard is clicked
	 * @return Either return a String[] or return null if unapplicable
	 */
	public String[] plusComboBoxOptions() {
		String[] stringArray = closedTileNames.toArray(new String[closedTileNames.size()]);
		if (closedTileNames.size() == 0) {
			return null;
			/*
            JOptionPane.showMessageDialog(theFrame,"All the tiles are open already; Use this button when you want to maximize removed tiles"
            		,"Input warning",JOptionPane.ERROR_MESSAGE);
			 */
		}
		else {
			/*JDialog dialog = new JDialog(theFrame);
			JComboBox<String> tileList = new JComboBox<String>(stringArray);
			dialog.add(tileList);
			dialog.setVisible(true);
			 */
			return stringArray;
		}
	}
	/**
	 *This method is called when the user clicks OK Button when user has selected which tile they want to re-add to the dashboard. 
	 *This will help keep track of which tiles are added.   
	 */
	public void readdedSelectedTile(String selectedTile) {			
		//String selected = closedTileNames.getSelectedIndex();
		for (int i = 0; i < closedTileNames.size(); i++) {
			if (closedTileNames.get(i).equals(selectedTile)) {
				System.out.println("Removed");
				closedTileNames.remove(i);
			}
		}
	}		
}
