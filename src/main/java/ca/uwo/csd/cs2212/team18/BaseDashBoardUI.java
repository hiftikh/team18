import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Container;

import javax.swing.GroupLayout;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class BaseDashBoardUI extends JFrame{

	Font font = new Font("Arial", Font.PLAIN, 12);
	Color blueColour = Color.decode("#45C2C5");

	public JLayeredPane lpane = new JLayeredPane();

	public BaseDashBoardUI() {
		this.initUI();
	}

	private void initUI() {
		/*
		//Create BaseDashBoard & APIData instances
		BaseDashBoard baseDashBoard = new BaseDashBoard();
		APIData api = new APIData();

		//Get the current date
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		//Format date variable so that it could be added to the 
		api.getData()
		 */

		setTitle("Dashboard");
		setSize((500*16)/9, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(blueColour);
		setResizable(false);

		JFrame jFrame = new JFrame();
		JButton b = new JButton("Hello");
		b.setSize(50, 50);;

		this.add(b);





	}

	public void dateSelectionError(String str) {
		JOptionPane.showMessageDialog(frame,str,"Input warning",JOptionPane.WARNING_MESSAGE);
	}

	public void noDataOnDate() {
		JOptionPane.showMessageDialog(frame, "No FitBit data on the specified date", "Error", JOptionPane.ERROR_MESSAGE);
	}
}