import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

public class GUIFrame extends JFrame {
	// public static void main(String[] args) throws IOException {
	// JFrame jframe = new JFrame();
	// GUI a = new GUI();
	// jframe.add(a);
	//
	// jframe.setSize(800, 600);
	// jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// jframe.setVisible(true);
	// }

	public GUIFrame(List<Node> path, String inputFile) throws IOException {
		JFrame frame = new JFrame();
		GUI a = new GUI(path, inputFile);
		frame.add(a);

		frame.setSize(800, 600);
		;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
