// Import needed files
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

// Create SingleBox Class
public class SingleBox{

	// Initialize fonts and color
	Font font = new Font("Arial", Font.PLAIN, 18);
	Font font2 = new Font("Arial", Font.BOLD, 12);
	Font font3 = new Font("Arial", Font.BOLD, 14);
	Font apiFont = new Font("Arial", Font.BOLD, 34);
	Font plusFont = new Font("Arial", Font.BOLD, 48);
	Color blueColour = Color.decode("#45C2C5");

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

	// Create JLabel with updated information
	JLabel calString = new JLabel();
	JLabel activeString = new JLabel();
	JLabel sedString = new JLabel();
	JLabel distanceDailyString = new JLabel();
	JLabel distanceBestString = new JLabel();
	JLabel distanceTotalString = new JLabel();
	JLabel floorDailyString = new JLabel();
	JLabel floorBestString = new JLabel();
	JLabel floorTotalString = new JLabel();
	JLabel stepDailyString = new JLabel();
	JLabel stepBestString = new JLabel();
	JLabel stepTotalString = new JLabel();

	public static boolean testBool;

	// Constructor
	public SingleBox(){
	} 

	/**
	 * Set new string for activities
	 */
	APIData apiData;
	public void setString(String dateInput) {
		System.out.println(testBool);
		if (testBool == true) {
			apiData = new APIData();
			modifyString();
		}
		else {
			apiData = new APIData(dateInput);
			modifyString();
		}
	}

	/**
	 *  Method used to produce API Data
	 */
	private void modifyString() {

		int caloriesOut = (int) apiData.getCaloriesOut().getValue();
		calString.setText(String.valueOf(caloriesOut));

		int floorDailyOut = (int) apiData.getFloors().getValue();
		floorDailyString.setText(String.valueOf(floorDailyOut));

		int floorBestOut = (int) apiData.getBestFloors().getValue();
		floorBestString.setText(String.valueOf(floorBestOut));

		int floorTotalOut = (int) apiData.getTotalFloors().getValue();
		floorTotalString.setText(String.valueOf(floorTotalOut));

		int stepDailyOut = (int) apiData.getSteps().getValue();
		stepDailyString.setText(String.valueOf(stepDailyOut));

		int stepBestOut = (int) apiData.getBestSteps().getValue();
		stepBestString.setText(String.valueOf(stepBestOut));

		int stepTotalOut = (int) apiData.getTotalSteps().getValue();
		stepTotalString.setText(String.valueOf(stepTotalOut));

		int activeOut = (int) apiData.getActMin().getValue();
		activeString.setText(String.valueOf(activeOut));

		int sedOut = (int) apiData.getSedMin().getValue();
		sedString.setText(String.valueOf(sedOut));

		int distanceDailyOut = (int) apiData.getDistance().getValue();
		distanceDailyString.setText(String.valueOf(distanceDailyOut));

		int distanceBestOut = (int) apiData.getBestDistance().getValue();
		distanceBestString.setText(String.valueOf(distanceBestOut));

		int distanceTotalOut = (int) apiData.getTotalDistance().getValue();
		distanceTotalString.setText(String.valueOf(distanceTotalOut));
	}

	/**
	 * Method will create a box with Calories information
	 * @return JPanel with Calories
	 */
	public JPanel createCaloriesBox() {

		// Initialize variables
		JLabel nameLabel = new JLabel("Daily Calories Burned");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();

		// Setup how x button will look
		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		// Setup how label will look like
		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		// Setup how calString will look like
		calString.setFont(apiFont);
		calString.setForeground(blueColour);
		calString.setBounds(10,50,500,50);

		// Setup how pane box will look like
		paneName.setLayout(null);
		paneName.setBackground(Color.WHITE);
		paneName.setVisible(true);
		paneName.add(calString);
		paneName.add(xSignButton);
		paneName.add(nameLabel, SwingConstants.CENTER);

		// Adding action when x button is pressed
		// Close the pane when pressed
		xSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneName.setVisible(false);
				BaseDashBoardUI.getFrame().remove(paneName);
				//BaseDashBoardUI.addClosedTile(name);
			}
		});
		paneName.add(xSignButton);
		return paneName;
	}

	/**
	 * Method will create a box with Active and Sedentary information
	 * @return JPanel with Active and Sedentary info
	 */
	public JPanel createActiveSedentaryBox() {

		// Initialize Variables
		JLabel nameLabel = new JLabel("Daily Minutes");
		JLabel nameLabelA = new JLabel("ACTIVE");
		JLabel nameLabelS = new JLabel("SEDENTARY");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();

		// Setup how x button looks like
		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		// Setup how Active label looks like
		nameLabelA.setFont(font3);
		nameLabelA.setForeground(blueColour);
		nameLabelA.setBounds(10,30,500,50);

		// Setup how activeString will look like
		activeString.setFont(apiFont);
		activeString.setForeground(blueColour);
		activeString.setBounds(10,52,500,50);

		// Setup how Sedentary looks like
		nameLabelS.setFont(font3);
		nameLabelS.setForeground(blueColour);
		nameLabelS.setBounds(10,72,500,50);

		// Setup how sedString will look like
		sedString.setFont(apiFont);
		sedString.setForeground(blueColour);
		sedString.setBounds(10,94,500,50);

		// Setup how pane looks like
		paneName.setLayout(null);
		paneName.setBackground(Color.WHITE);
		paneName.setVisible(true);

		xSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneName.setVisible(false);
				BaseDashBoardUI.getFrame().remove(paneName);
				//BaseDashBoardUI.addClosedTile(name);
			}
		});
		// Add labels and button to the pane
		paneName.add(nameLabelA, SwingConstants.CENTER);
		paneName.add(nameLabelS, SwingConstants.CENTER);
		paneName.add(nameLabel, SwingConstants.CENTER);
		paneName.add(activeString, SwingConstants.CENTER);
		paneName.add(sedString, SwingConstants.CENTER);
		paneName.add(xSignButton);
		return paneName;
	}

	/**
	 * Method will create a box with Distance information
	 * @return JPanel with Distance info
	 */
	public JPanel createDistanceBox() {

		// Initilize Variables
		JLabel nameLabel = new JLabel("Distance");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();
		JLabel bestDay = new JLabel("BEST DAY:");
		JLabel lifeTime = new JLabel("LIFE TIME:");
		JLabel daily = new JLabel("DAILY:");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,13,100,50);

		// Setup how Daily distanceString will look like
		distanceDailyString.setFont(apiFont);
		distanceDailyString.setForeground(blueColour);
		distanceDailyString.setBounds(10,33,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,55,100,50);

		// Setup how best distanceString will look like
		distanceBestString.setFont(apiFont);
		distanceBestString.setForeground(blueColour);
		distanceBestString.setBounds(10,75,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,97,100,50);

		// Setup how total distanceString will look like
		distanceTotalString.setFont(apiFont);
		distanceTotalString.setForeground(blueColour);
		distanceTotalString.setBounds(10,117,500,50);

		paneName.setLayout(null);
		paneName.setBackground(Color.WHITE);
		paneName.setVisible(true);
		paneName.add(distanceBestString);
		paneName.add(distanceDailyString);
		paneName.add(distanceTotalString);
		paneName.add(xSignButton);
		paneName.add(nameLabel);
		paneName.add(bestDay);
		paneName.add(lifeTime);
		paneName.add(daily);

		xSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneName.setVisible(false);
				BaseDashBoardUI.getFrame().remove(paneName);
				//BaseDashBoardUI.addClosedTile(name);
			}
		});
		paneName.add(xSignButton);
		return paneName;
	}

	/**
	 * Method will create a box with Floor information
	 * @return JPanel with Floor info
	 */
	public JPanel createFloorBox() {

		JLabel nameLabel = new JLabel("Floors");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();
		JLabel bestDay = new JLabel("BEST DAY:");
		JLabel lifeTime = new JLabel("LIFE TIME:");
		JLabel daily = new JLabel("DAILY:");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,13,100,50);

		// Setup how Daily floorString will look like
		floorDailyString.setFont(apiFont);
		floorDailyString.setForeground(blueColour);
		floorDailyString.setBounds(10,33,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,55,100,50);

		// Setup how best floorString will look like
		floorBestString.setFont(apiFont);
		floorBestString.setForeground(blueColour);
		floorBestString.setBounds(10,75,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,97,100,50);

		// Setup how total floorString will look like
		floorTotalString.setFont(apiFont);
		floorTotalString.setForeground(blueColour);
		floorTotalString.setBounds(10,117,500,50);

		paneName.setLayout(null);
		paneName.setBackground(Color.WHITE);
		paneName.setVisible(true);
		paneName.add(floorBestString);
		paneName.add(floorDailyString);
		paneName.add(floorTotalString);
		paneName.add(xSignButton);
		paneName.add(nameLabel);
		paneName.add(bestDay);
		paneName.add(lifeTime);
		paneName.add(daily);

		xSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paneName.setVisible(false);
				BaseDashBoardUI.getFrame().remove(paneName);
				//BaseDashBoardUI.addClosedTile(name);
			}
		});
		paneName.add(xSignButton);
		return paneName;
	}

	/**
	 * Method will create a box with Step information
	 * @return JPanel with Step info
	 */
	public JPanel createStepBox() {

		JLabel nameLabel = new JLabel("Steps");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();
		JLabel bestDay = new JLabel("BEST DAY:");
		JLabel lifeTime = new JLabel("LIFE TIME:");
		JLabel daily = new JLabel("DAILY:");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,13,100,50);

		// Setup how Daily stepString will look like
		stepDailyString.setFont(apiFont);
		stepDailyString.setForeground(blueColour);
		stepDailyString.setBounds(10,33,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,55,100,50);

		// Setup how best stepString will look like
		stepBestString.setFont(apiFont);
		stepBestString.setForeground(blueColour);
		stepBestString.setBounds(10,75,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,97,100,50);

		// Setup how total stepString will look like
		stepTotalString.setFont(apiFont);
		stepTotalString.setForeground(blueColour);
		stepTotalString.setBounds(10,117,500,50);

		paneName.setLayout(null);
		paneName.setBackground(Color.WHITE);
		paneName.setVisible(true);
		paneName.add(stepTotalString);
		paneName.add(stepDailyString);
		paneName.add(stepBestString);
		paneName.add(xSignButton);
		paneName.add(nameLabel);
		paneName.add(bestDay);
		paneName.add(lifeTime);
		paneName.add(daily);

		xSignButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paneName.setVisible(false);
				BaseDashBoardUI.getFrame().remove(paneName);
				//BaseDashBoardUI.addClosedTile(name);
			}
		});
		paneName.add(xSignButton);
		return paneName;
	}

} // End of Class
