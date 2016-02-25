import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BaseDashBoardUI window = new BaseDashBoardUI();
				window.setVisible(true);
			}
		}); }
}