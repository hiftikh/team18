package ca.uwo.csd.cs2212.team18;

//Import needed files
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

//Create SingleBox Class
/**
 * SingleBox class that creates an instance of a single box. 
 * To be used in the BaseDashBoardUI class
 * @author team 18
 *
 */
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
	//APIData currentDataAPI = new APIData(apiInput);

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
	 * Method will emptyBox with plus sign to add more panels
	 * @return JPanel with plus sign
	 */
	public JPanel createEmptyBox() {

		// Initial variables 
		final JButton plusSignButton = new JButton("+");
		final JPanel emptyBox = new JPanel();
		final JPanel paneName = new JPanel();

		// Set how the plus button looks
		plusSignButton.setForeground(Color.WHITE);
		plusSignButton.setFont(plusFont);
		plusSignButton.setBorder(null);

		// Set how the empty box looks
		emptyBox.setBorder(BorderFactory.createDashedBorder(Color.WHITE, 2, 1, 1, false));
		emptyBox.setPreferredSize(new Dimension (170,160));
		emptyBox.setVisible(true);
		emptyBox.setBackground(null);
		emptyBox.setLayout(new BorderLayout());

		// Create action listener when user presses plus button
		plusSignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Initial variables
				JPanel contentPane;
				JDialog plusButton = new JDialog();
				JButton btnNewButton = new JButton("OK");
				JLayeredPane layeredPane = new JLayeredPane();
				JLabel lblSelectTheFeatures = new JLabel("Select the feature(s) you wish to view");
				// Setup how JDialog looks like
				plusButton.setVisible(true);
				plusButton.setTitle("Additional Features");
				plusButton.setBounds(100, 100, 276, 217);
				contentPane = new JPanel();
				contentPane.setToolTipText("hello");
				contentPane.setBackground(new Color(69, 194, 197));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				plusButton.setContentPane(contentPane);
				contentPane.setLayout(null);
				layeredPane.setBounds(5, 5, 265, 184);
				layeredPane.setToolTipText("");
				contentPane.add(layeredPane);
				layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));
				lblSelectTheFeatures.setAlignmentX(0.5f);
				layeredPane.add(lblSelectTheFeatures);

				// Create panel options for user to select 
				final JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"Calories Burned", "Total distance", "Floors climbed", "Steps", "Active minutes", "Sedentary minutes"}));

				// Decide what happens when 'OK" is pressed
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// If Calories Burned is selected
						if (comboBox.getSelectedItem().toString().equals("Calories Burned")) {
							SingleBox singleFirstBox = new SingleBox();
							JPanel firstBox = singleFirstBox.createCaloriesBox();
							// Remove emptyBox, then add Calories Box, and add back the emptyBox
							BaseDashBoardUI.getFrame().remove(paneName);
							BaseDashBoardUI.getFrame().add(firstBox);
							BaseDashBoardUI.getFrame().add(paneName);
							paneName.repaint();
							SwingUtilities.updateComponentTreeUI(paneName);
							plusSignButton.setBackground(null);		
							plusSignButton.setBorder(null);
						} 
					}
				});
				// Add comboBox and "OK" button to pane
				btnNewButton.setAlignmentX(0.5f);
				layeredPane.add(comboBox);
				layeredPane.add(btnNewButton);

			}
		});

		// Set values for paneName
		paneName.setBackground(null);
		paneName.setVisible(true);

		// Add plus sign to emptyBox and emptyBox to paneName
		emptyBox.add(plusSignButton, SwingConstants.CENTER);
		paneName.add(emptyBox);

		return paneName;
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
		calString.setBounds(30,50,500,50);

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
		JLabel nameLabelA = new JLabel("Daily Active Minutes");
		JLabel nameLabelS = new JLabel("Daily Sedentary Minutes");
		JButton xSignButton = new JButton("x");
		final JPanel paneName = new JPanel();

		// Setup how x button looks like
		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		// Setup how Active label looks like
		nameLabelA.setFont(font2);
		nameLabelA.setForeground(blueColour);
		nameLabelA.setBounds(30,-8,500,50);

		// Setup how activeString will look like
		activeString.setFont(apiFont);
		activeString.setForeground(blueColour);
		activeString.setBounds(30,50,500,50);

		// Setup how Sedentary looks like
		nameLabelS.setFont(font2);
		nameLabelS.setForeground(blueColour);
		nameLabelS.setBounds(15,60,500,50);

		// Setup how sedString will look like
		sedString.setFont(apiFont);
		sedString.setForeground(blueColour);
		sedString.setBounds(30,50,500,50);

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
		JLabel bestDay = new JLabel("Best Day: ");
		JLabel lifeTime = new JLabel("Life Time: ");
		JLabel daily = new JLabel("Daily: ");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,30,100,50);

		// Setup how Best distanceString will look like
		distanceBestString.setFont(apiFont);
		distanceBestString.setForeground(blueColour);
		distanceBestString.setBounds(30,50,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,50,100,50);

		// Setup how Total distanceString will look like
		distanceTotalString.setFont(apiFont);
		distanceTotalString.setForeground(blueColour);
		distanceTotalString.setBounds(30,50,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,70,100,50);

		// Setup how Daily distanceString will look like
		distanceDailyString.setFont(apiFont);
		distanceDailyString.setForeground(blueColour);
		distanceDailyString.setBounds(30,50,500,50);

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
		JLabel bestDay = new JLabel("Best Day: ");
		JLabel lifeTime = new JLabel("Life Time: ");
		JLabel daily = new JLabel("Daily: ");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,30,100,50);

		// Setup how Best distanceString will look like
		floorBestString.setFont(apiFont);
		floorBestString.setForeground(blueColour);
		floorBestString.setBounds(30,50,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,50,100,50);

		// Setup how Total distanceString will look like
		floorTotalString.setFont(apiFont);
		floorTotalString.setForeground(blueColour);
		floorTotalString.setBounds(30,50,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,70,100,50);

		// Setup how Daily distanceString will look like
		floorDailyString.setFont(apiFont);
		floorDailyString.setForeground(blueColour);
		floorDailyString.setBounds(30,50,500,50);

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
		JLabel bestDay = new JLabel("Best Day: ");
		JLabel lifeTime = new JLabel("Life Time: ");
		JLabel daily = new JLabel("Daily: ");

		xSignButton.setFont(font);
		xSignButton.setLayout(null);
		xSignButton.setForeground(blueColour);
		xSignButton.setBorder(null);
		xSignButton.setBounds(1,1,30,30);

		nameLabel.setFont(font2);
		nameLabel.setForeground(blueColour);
		nameLabel.setBounds(30,-8,500,50);

		bestDay.setFont(font3);
		bestDay.setLayout(null);
		bestDay.setForeground(blueColour);
		bestDay.setBounds(10,20,100,50);

		// Setup how Best distanceString will look like
		stepBestString.setFont(apiFont);
		stepBestString.setForeground(blueColour);
		stepBestString.setBounds(1,40,500,50);

		lifeTime.setFont(font3);
		lifeTime.setLayout(null);
		lifeTime.setForeground(blueColour);
		lifeTime.setBounds(10,60,100,50);

		// Setup how Total distanceString will look like
		stepTotalString.setFont(apiFont);
		stepTotalString.setForeground(blueColour);
		stepTotalString.setBounds(1,80,500,50);

		daily.setFont(font3);
		daily.setLayout(null);
		daily.setForeground(blueColour);
		daily.setBounds(10,100,100,50);

		// Setup how Daily distanceString will look like
		stepDailyString.setFont(apiFont);
		stepDailyString.setForeground(blueColour);
		stepDailyString.setBounds(1,120,500,50);

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
