//package ca.uwo.csd.cs2212.team18;


//Import 
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
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import javax.swing.ScrollPaneConstants;


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

	JLabel logoImage = new JLabel(new ImageIcon("src/images/FitByte Logo.png"));
	JLabel awardA[] = new JLabel[20];
	JLabel awardD[] = new JLabel[20];
	

	{
		awardA[0] =  new JLabel(new ImageIcon("src/images/awards/Activate/burj.png"));
		awardA[1] =  new JLabel(new ImageIcon("src/images/awards/Activate/10_days.png"));
		awardA[2] =  new JLabel(new ImageIcon("src/images/awards/Activate/heaven.png"));
		awardA[3] =  new JLabel(new ImageIcon("src/images/awards/Activate/200_steps.png"));
		awardA[4] =  new JLabel(new ImageIcon("src/images/awards/Activate/1000_floors.png"));
		awardA[5] =  new JLabel(new ImageIcon("src/images/awards/Activate/9000.png"));
		awardA[6] =  new JLabel(new ImageIcon("src/images/awards/Activate/back2back.png"));
		awardA[7] =  new JLabel(new ImageIcon("src/images/awards/Activate/burn_1000.png"));
		awardA[8] =  new JLabel(new ImageIcon("src/images/awards/Activate/cake.png"));
		awardA[9] =  new JLabel(new ImageIcon("src/images/awards/Activate/cereal.png"));
		awardA[10] =  new JLabel(new ImageIcon("src/images/awards/Activate/cold_heart.png"));
		awardA[11] =  new JLabel(new ImageIcon("src/images/awards/Activate/distance_earth.png"));
		awardA[12] =  new JLabel(new ImageIcon("src/images/awards/Activate/earth.png"));
		awardA[13] =  new JLabel(new ImageIcon("src/images/awards/Activate/fat.png"));
		awardA[14] =  new JLabel(new ImageIcon("src/images/awards/Activate/mass.png"));
		awardA[15] =  new JLabel(new ImageIcon("src/images/awards/Activate/mile.png"));
		awardA[16] =  new JLabel(new ImageIcon("src/images/awards/Activate/moon.png"));
		awardA[17] =  new JLabel(new ImageIcon("src/images/awards/Activate/shoe.png"));
		awardA[18] =  new JLabel(new ImageIcon("src/images/awards/Activate/western.png"));
		awardA[19] =  new JLabel(new ImageIcon("src/images/awards/Activate/whiteoaks.png"));
	}
	
	{
		awardD[0] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dburj.png"));
		awardD[1] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/d10_days.png"));
		awardD[2] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dheaven.png"));
		awardD[3] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/d200_steps.png"));
		awardD[4] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/d1000_floors.png"));
		awardD[5] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/d9000.png"));
		awardD[6] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dback2back.png"));
		awardD[7] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dburn_1000.png"));
		awardD[8] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dcake.png"));
		awardD[9] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dcereal.png"));
		awardD[10] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dcold_heart.png"));
		awardD[11] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/ddistance_earth.png"));
		awardD[12] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dearth.png"));
		awardD[13] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dfat.png"));
		awardD[14] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dmass.png"));
		awardD[15] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dmile.png"));
		awardD[16] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dmoon.png"));
		awardD[17] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dshoe.png"));
		awardD[18] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dwestern.png"));
		awardD[19] =  new JLabel(new ImageIcon("src/images/awards/Deactivate/dwhiteoaks.png"));
	}

	JScrollPane scrollPane = new JScrollPane();
	JPanel scrollPanelBorder = new JPanel();

	JPanel sidePanelLogo = new JPanel();
	JPanel sidePanelUser = new JPanel();
	JLabel sidePanelUserText = new JLabel("Hello User");
	JPanel sidePanelAward = new JPanel();
	JLabel sidePanelAwardText = new JLabel("Awards");
	JPanel sidePanelAwardBox = new JPanel(new GridLayout());

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

	JPanel firstBox = singleFirstBox.createCaloriesBox();
	JPanel secondBox = singleSecondBox.createActiveSedentaryBox();
	JPanel thirdBox = singleThirdBox.createDistanceBox();
	JPanel fourthBox = singleFourthBox.createFloorBox();
	JPanel fifthBox = singleFifthBox.createStepBox();

	JPanel emptyBox = new JPanel();
	JButton plusSign = new JButton("+");

	private JFrame itself = new JFrame();

	Data data;
	private boolean testOrNot;
	public BaseDashBoardUI(boolean testorNot) {
		this.initUI();
		testOrNot = testorNot;
		if (testorNot == true) {
			data = new TestData();
		}
		else {
			data = new APIData(basedashboard.getCurrentDate());
		}
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
		getContentPane().setLayout(null);
		
		singleFirstBox.passAPI(data);

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
		sidePanelUser.setBounds(this.getWidth()-250, 81, 250, 150);
		sidePanelUser.setBackground(Color.WHITE);
		sidePanelUser.setVisible(true);
		sidePanelAward.setLayout(null);
		sidePanelAwardText.setHorizontalAlignment(SwingConstants.CENTER);
		sidePanelAwardText.setVerticalAlignment(SwingConstants.TOP);

		// Set up award panel
		sidePanelAwardText.setFont(font);
		sidePanelAwardText.setForeground(blueColour);
		sidePanelAwardText.setBounds(75, 4, 100, 100);

		sidePanelAward.setBounds(this.getWidth()-250, 240, 250, 238);
		sidePanelAward.setBackground(Color.WHITE);
		sidePanelAward.setVisible(true);
		getContentPane().add(sidePanelAward);

		scrollPanelBorder.setBackground(Color.WHITE);
		scrollPanelBorder.setLayout(new GridLayout(0, 3, 5, 5));


		for(int i=0;i<20;i++) {
			scrollPanelBorder.add(awardD[i]);
		}

		scrollPane.setBounds(0, 30, 250, 200);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(scrollPanelBorder);
		scrollPane.setBorder(null);

		sidePanelAward.add(sidePanelAwardText);
		sidePanelAward.add(scrollPane);

		// Setup how the buttons look like
		fitCalcButton.setFont(font);
		fitCalcButton.setForeground(blueColour);
		fitCalcButton.setVisible(true);
		fitCalcButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try {
					FitCalcUI fitCalc = new FitCalcUI(data, testOrNot);
					fitCalc.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		dailyGoalsButton.setFont(font);
		dailyGoalsButton.setForeground(blueColour);
		dailyGoalsButton.setVisible(true);
		dailyGoalsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try {
					DailyGoalsUI daily = new DailyGoalsUI(data, testOrNot);
					daily.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


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
				dateDialog.getContentPane().add(contentPane);
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
							dateDialog.dispose();
						}
						else {
							JOptionPane.showMessageDialog(dateDialog,userMessage,"Input warning",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}});

		///////////////////////////////////////////////////////////////////////

		emptyBox.setBorder(BorderFactory.createDashedBorder(Color.WHITE, 2, 1, 1, false));
		emptyBox.setVisible(true);
		emptyBox.setBackground(null);

		plusSign.setForeground(Color.WHITE);
		plusSign.setFont(font2);

		plusSign.setFont(font2);
		plusSign.setLayout(null);
		plusSign.setForeground(Color.WHITE);
		plusSign.setBorder(null);

		plusSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane;
				final JDialog plusButton = new JDialog();
				plusButton.setVisible(true);
				plusButton.setTitle("Additional Features");
				plusButton.setBounds(100, 100, 276, 217);
				contentPane = new JPanel();
				contentPane.setToolTipText("hello");
				contentPane.setBackground(new Color(69, 194, 197));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				plusButton.setContentPane(contentPane);
				contentPane.setLayout(null);

				JButton btnNewButton = new JButton("OK");

				JLayeredPane layeredPane = new JLayeredPane();
				layeredPane.setBounds(5, 5, 265, 184);
				layeredPane.setToolTipText("");
				contentPane.add(layeredPane);
				layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));

				JLabel lblSelectTheFeatures = new JLabel("Select the feature(s) you wish to view");
				lblSelectTheFeatures.setAlignmentX(0.5f);
				layeredPane.add(lblSelectTheFeatures);

				final JComboBox<String[]> comboBox = new JComboBox<String[]>();
				comboBox.setModel(new DefaultComboBoxModel(basedashboard.getStringArrayList()));

				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (comboBox.getSelectedItem().toString().equals("Calories")) {

							SingleBox singleFirstBox = new SingleBox();
							JPanel firstBox = singleFirstBox.createCaloriesBox();
							BaseDashBoardUI.getFrame().remove(emptyBox);
							BaseDashBoardUI.getFrame().add(firstBox);
							BaseDashBoardUI.getFrame().add(emptyBox);

							boxes.repaint();
							SwingUtilities.updateComponentTreeUI(boxes);
							basedashboard.readdedSelectedTile("Calories");
						}
						if (comboBox.getSelectedItem().toString().equals("Active Minutes")) {

							SingleBox singleSecondBox = new SingleBox();
							JPanel secondBox = singleSecondBox.createActiveSedentaryBox(); 
							BaseDashBoardUI.getFrame().remove(emptyBox);
							BaseDashBoardUI.getFrame().add(secondBox);
							BaseDashBoardUI.getFrame().add(emptyBox);

							boxes.repaint();
							SwingUtilities.updateComponentTreeUI(boxes);
							basedashboard.readdedSelectedTile("Active Minutes");
						}
						if (comboBox.getSelectedItem().toString().equals("Distance")) {

							SingleBox singleThirdBox = new SingleBox();
							JPanel thirdBox = singleThirdBox.createDistanceBox();
							BaseDashBoardUI.getFrame().remove(emptyBox);
							BaseDashBoardUI.getFrame().add(thirdBox);
							BaseDashBoardUI.getFrame().add(emptyBox);

							boxes.repaint();
							SwingUtilities.updateComponentTreeUI(boxes);
							basedashboard.readdedSelectedTile("Distance");
						}
						if (comboBox.getSelectedItem().toString().equals("Floors")) {

							SingleBox singleFourthBox = new SingleBox();
							JPanel fourthBox = singleFourthBox.createFloorBox();
							BaseDashBoardUI.getFrame().remove(emptyBox);
							BaseDashBoardUI.getFrame().add(fourthBox);
							BaseDashBoardUI.getFrame().add(emptyBox);

							boxes.repaint();
							SwingUtilities.updateComponentTreeUI(boxes);

							basedashboard.readdedSelectedTile("Floors");
						}
						if (comboBox.getSelectedItem().toString().equals("Steps")) {

							SingleBox singleFifthBox = new SingleBox();
							JPanel fifthBox = singleFifthBox.createStepBox();
							BaseDashBoardUI.getFrame().remove(emptyBox);
							BaseDashBoardUI.getFrame().add(fifthBox);
							BaseDashBoardUI.getFrame().add(emptyBox);

							boxes.repaint();
							SwingUtilities.updateComponentTreeUI(boxes);

							basedashboard.readdedSelectedTile("Steps");
						}
						plusButton.dispose();
					}
				});
				btnNewButton.setAlignmentX(0.5f);
				layeredPane.add(comboBox);
				layeredPane.add(btnNewButton);

			}
		});
		emptyBox.setLayout(new BorderLayout());
		emptyBox.add(plusSign);


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

		getContentPane().add(buttonPanel);
		getContentPane().add(sidePanelLogo);
		getContentPane().add(sidePanelUser);
		getContentPane().add(boxes);
		getContentPane().add(dateButton);
	}

	private void loadCustomConfiguration() throws Exception{
		try {
			ArrayList<String> tileList = BaseDashBoard.loadClosedTileList();

			for (int i = 0; i < tileList.size(); i++) {
				if (tileList.get(i).toString().equals("Calories")) {
					BaseDashBoardUI.getFrame().remove(firstBox);
				}
				if (tileList.get(i).toString().equals("Active Minutes")){
					BaseDashBoardUI.getFrame().remove(secondBox);
				}
				if (tileList.get(i).toString().equals("Distance")) {
					BaseDashBoardUI.getFrame().remove(thirdBox);
				}
				if (tileList.get(i).toString().equals("Floors")){
					BaseDashBoardUI.getFrame().remove(fourthBox);
				}
				if (tileList.get(i).toString().equals("Steps")) {
					BaseDashBoardUI.getFrame().remove(fifthBox);
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
