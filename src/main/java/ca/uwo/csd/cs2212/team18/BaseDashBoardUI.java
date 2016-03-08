package ca.uwo.csd.cs2212.team18;

// Import 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

/**
 * BaseDashBoardUI class.
 * This class creates an instance of the BaseDashBoard UI
 * @author Team18
 *
 */
public class BaseDashBoardUI extends JFrame{

	// Initialize Variables
	BaseDashBoard basedashboard = new BaseDashBoard();
	Font font = new Font("Arial", Font.PLAIN, 18);
	Font font2 = new Font("Arial", Font.BOLD, 48);
	Color blueColour = Color.decode("#45C2C5");
	JLabel logoImage = new JLabel(new ImageIcon("src/FitByte Logo.png"));

	JPanel sidePanelLogo = new JPanel();
	JPanel sidePanelUser = new JPanel();
	JLabel sidePanelUserText = new JLabel("Hello User");
	JPanel sidePanelAward = new JPanel();
	JLabel sidePanelAwardText = new JLabel("Awards");
	JPanel sidePanelAwardBox = new JPanel(new GridLayout());
	JPanel[] awardBox = new JPanel[20];

	JButton fitCalcButton = new JButton("FitCalc");
	JButton dailyGoalsButton = new JButton("Daily Goals");
	JButton friendZoneButton = new JButton("Friend Zone");
	JButton heartRateButton = new JButton("Heart Rate");
	JButton dateButton = new JButton("Date");
	JPanel buttonPanel = new JPanel();

	static JLayeredPane boxes = new JLayeredPane();
	SingleBox singleFirstBox = new SingleBox();
	SingleBox singleSecondBox = new SingleBox();
	SingleBox singleThirdBox = new SingleBox();
	SingleBox singleFourthBox = new SingleBox();
	SingleBox singleFifthBox = new SingleBox();
	SingleBox emptybox = new SingleBox();

	JPanel firstBox = singleFirstBox.createCaloriesBox();
	JPanel secondBox = singleSecondBox.createActiveSedentaryBox();
	JPanel thirdBox = singleThirdBox.createDistanceBox();
	JPanel fourthBox = singleFourthBox.createFloorBox();
	JPanel fifthBox = singleFifthBox.createStepBox();
	JPanel emptyBox = emptybox.createEmptyBox();

	private JFrame itself = new JFrame();

	// Check for args == 0
	private boolean testOrNot;
	public BaseDashBoardUI(boolean testorNot) {
		this.initUI();
		testOrNot = testorNot;
		SingleBox.testBool = testOrNot;
		try {
			this.loadCustomConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return JLayeredFrame of boxes
	 */
	public static JLayeredPane getFrame() {
		return boxes;
	}

	private void initUI() {

		// Set JFrame
		setTitle("Dashboard");
		setSize((500*16)/9, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(blueColour);
		setResizable(false);
		setLayout(null);

		/*
		 * When user exits the app, app will try to create a new files that saves the configuration that was set by the user
		 * at the time they exit the app
		 */
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent wind) {
				try {
					basedashboard.storeClosedTileList();
				}
				catch (Exception e) {
					System.out.println("Failed to save configurations");
				}
				wind.getWindow().dispose();
			}
		});

		// Setup how logo looks like
		logoImage.setVisible(true);
		sidePanelLogo.setBounds(this.getWidth()-250, 0, 250, 75);
		sidePanelLogo.setBackground(Color.WHITE);
		sidePanelLogo.add(logoImage, SwingConstants.CENTER);
		sidePanelLogo.setVisible(true);

		// Setup how side panel looks like
		sidePanelUserText.setFont(font);
		sidePanelUserText.setForeground(blueColour);
		sidePanelUser.add(sidePanelUserText);
		sidePanelUser.setBounds(this.getWidth()-250, 80, 250, 300);
		sidePanelUser.setBackground(Color.WHITE);
		sidePanelUser.setVisible(true);

		// Set up award panel
		sidePanelAwardText.setFont(font);
		sidePanelAwardText.setForeground(blueColour);

		sidePanelAward.add(sidePanelAwardText);
		sidePanelAward.setBounds(this.getWidth()-250, 385, 250, 300);
		sidePanelAward.setBackground(Color.WHITE);
		sidePanelAward.setVisible(true);

		// Setup how the buttons look like
		fitCalcButton.setFont(font);
		fitCalcButton.setForeground(blueColour);
		fitCalcButton.setVisible(true);

		dailyGoalsButton.setFont(font);
		dailyGoalsButton.setForeground(blueColour);
		dailyGoalsButton.setVisible(true);

		friendZoneButton.setFont(font);
		friendZoneButton.setForeground(blueColour);
		friendZoneButton.setVisible(true);

		heartRateButton.setFont(font);
		heartRateButton.setForeground(blueColour);
		heartRateButton.setVisible(true);

		buttonPanel.setVisible(true);
		buttonPanel.setBounds(0,0,this.getWidth()-250,40);
		buttonPanel.setBackground(blueColour);

		buttonPanel.add(fitCalcButton);
		buttonPanel.add(dailyGoalsButton);
		buttonPanel.add(friendZoneButton);
		buttonPanel.add(heartRateButton);

		dateButton.setFont(font);
		dateButton.setForeground(blueColour);
		dateButton.setBounds(225,35,200,30);
		dateButton.setVisible(true);

		// Check what happens when date button is pressed
		dateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				final JDialog dateDialog = new JDialog();
				JPanel contentPane = new JPanel();
				dateDialog.setVisible(true);
				dateDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dateDialog.setBounds(100, 100, 442, 237);

				contentPane.setBackground(new Color(26, 168, 180));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				dateDialog.add(contentPane);
				JLayeredPane layeredPane = new JLayeredPane() ;
				Component layeredPane_1 = new JLayeredPane();
				layeredPane.setLayer(layeredPane_1, 1);
				contentPane.setLayout(null);

				JLayeredPane layeredPane1 = new JLayeredPane();
				layeredPane1.setBounds(0, 11, 444, 32);
				contentPane.add(layeredPane1);
				layeredPane1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

				JLabel lblNewLabel = new JLabel("Please enter the date in the format YYYY/MM/DD");
				layeredPane1.add(lblNewLabel);

				JLayeredPane layeredPane_11 = new JLayeredPane();
				layeredPane_11.setBounds(0, 72, 444, 32);
				contentPane.add(layeredPane_11);
				layeredPane_11.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 5));

				JLabel lblNewLabel_1 = new JLabel("Year");
				layeredPane_11.add(lblNewLabel_1);

				JLabel lblNewLabel_2 = new JLabel("Month");
				layeredPane_11.add(lblNewLabel_2);

				JLabel lblNewLabel_3 = new JLabel("Day");
				layeredPane_11.add(lblNewLabel_3);

				JLayeredPane layeredPane_2 = new JLayeredPane();
				layeredPane_2.setBounds(0, 99, 444, 32);
				contentPane.add(layeredPane_2);
				layeredPane_2.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 5));

				final JTextField textField = new JTextField();
				layeredPane_2.add(textField);
				textField.setColumns(7);

				final JTextField textField_1 = new JTextField();
				layeredPane_2.add(textField_1);
				textField_1.setColumns(7);

				final JTextField textField_2 = new JTextField();
				layeredPane_2.add(textField_2);
				textField_2.setColumns(7);

				JLayeredPane layeredPane_3 = new JLayeredPane();
				layeredPane_3.setBounds(0, 160, 444, 39);
				contentPane.add(layeredPane_3);
				layeredPane_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

				JButton btnOk = new JButton("OK");
				layeredPane_3.add(btnOk);
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String userMessage = basedashboard.checkDateInput(textField.getText(),textField_1.getText(),textField_2.getText());
						if (userMessage == "") {
							singleFirstBox.setString(basedashboard.getSelectedDate());
							singleSecondBox.setString(basedashboard.getSelectedDate());
							singleThirdBox.setString(basedashboard.getSelectedDate());
							singleFourthBox.setString(basedashboard.getSelectedDate());
							singleFifthBox.setString(basedashboard.getSelectedDate());
							dateDialog.dispose();
						}
						else {
							JOptionPane.showMessageDialog(dateDialog,userMessage,"Input warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}});

		///////////////////////////////////////////////////////////////////////

		boxes.setBounds(45,75,550,350);
		boxes.setVisible(true);
		boxes.setLayout(new GridLayout(2,3,20,15));

		boxes.add(firstBox);
		boxes.add(secondBox);
		boxes.add(thirdBox);
		boxes.add(fourthBox);
		boxes.add(fifthBox);
		boxes.add(emptyBox);

		///////////////////////////////////////////////////////////////////////

		this.add(buttonPanel);
		this.add(sidePanelLogo);
		this.add(sidePanelUser);
		this.add(sidePanelAward);
		this.add(boxes);
		this.add(dateButton);
	}

	private void loadCustomConfiguration() throws Exception{
		try {
			ArrayList<String> tileList = BaseDashBoard.loadClosedTileList();

			for (int i = 0; i < tileList.size(); i++) {
				if (tileList.get(i).toString().equals("Calories")) {
					boxes.remove(firstBox);
				}
				if (tileList.get(i).toString().equals("Active Minutes")){
					boxes.remove(secondBox);
				}
				if (tileList.get(i).toString().equals("Distance")) {
					boxes.remove(thirdBox);
				}
				if (tileList.get(i).toString().equals("Floors")){
					boxes.remove(fourthBox);
				}
				if (tileList.get(i).toString().equals("Steps")) {
					boxes.remove(fifthBox);
				}
			}
		}
		catch (IOException e) {
			System.out.println("Loading Error");
		}
		catch (Exception e) {
			System.out.println("Other Error");
		}
	}
}