package ca.uwo.csd.cs2212.team18;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;

public class Dialog extends JFrame {

	/**
	 * JPanel that stores the content
	 */
	private JPanel contentPane;

	/**
	 * JFrame to store/display content
	 */
	private JFrame theFrame;

	/**
	 * Create the frame.
	 */
	public Dialog(JFrame frame) {
		setTitle("Additional Features");
		theFrame = frame;
		setBounds(100, 100, 276, 217);
		contentPane = new JPanel();
		contentPane.setToolTipText("hello");
		contentPane.setBackground(new Color(69, 194, 197));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Calories burned", "Total distance", "Floors climbed", "Steps", "Active minutes", "Sedentary minutes"}));


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString() == "Calories burned") {
					SingleBox singleFirstBox = new SingleBox();
					JPanel firstBox = singleFirstBox.createCaloriesBox();
					BaseDashBoardUI.getFrame().add(firstBox);
					theFrame.validate();
					theFrame.repaint();
				} 
			}
		});
		btnNewButton.setAlignmentX(0.5f);
		layeredPane.add(comboBox);
		layeredPane.add(btnNewButton);

	}
}