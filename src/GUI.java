import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JPanel;

public class GUI extends JPanel {
	static HashMap<String, List<String>> intersections = new HashMap<>();
	static HashMap<String, List<String>> roads = new HashMap<>();
	static double x1;
	static double y1;
	static double x2;
	static double y2;
	static double w1, z1, w2, z2;

	static double maxLat;
	static double maxLong;
	static double minLat;
	static double minLong;

	static ArrayList<String> inputs = new ArrayList<>();

	public GUI(List<Node> path, String inputFile) throws IOException {
		cases(inputFile);
		placeIntoHashMaps(inputFile);
		if (path.equals(null)) {
			highlight("");
		} else {
			for (Node intersection : path) {
				inputs.add(intersection.intersectionID);
			}
		}
	}

	public static void highlight(String input) {
		StringTokenizer st = new StringTokenizer(input);
		while (st.hasMoreTokens()) {
			inputs.add(st.nextToken());
		}
		System.out.println(inputs.toString());
	}

	public static void cases(String file) {
		if (file.equals("ur.txt")) {
			maxLat = 43.131704;
			minLat = 43.125214;
			maxLong = -77.625301;
			minLong = -77.632098;
		} else if (file.equals("monroe.txt")) {
			maxLat = 43.365846;
			minLat = 42.940061;
			maxLong = -77.37142;
			minLong = -77.99713699999998;
		} else if (file.equals("nys.txt")) {
			maxLat = 45.010458;
			minLat = 40.518203;
			maxLong = -71.858619;
			minLong = -79.762145;
		} else {
			return;
		}
	}

	public static void placeIntoHashMaps(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String input = "";
		while ((input = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(input);

			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				if (temp.equals("i")) {
					String val = st.nextToken();
					intersections.put(val, new ArrayList<>());
					intersections.get(val).add(st.nextToken());
					intersections.get(val).add(st.nextToken());
				} else if (temp.equals("r")) {
					String val = st.nextToken();
					roads.put(val, new ArrayList<>());
					roads.get(val).add(st.nextToken());
					roads.get(val).add(st.nextToken());
				}
			}
		}
		br.close();
	}

	// draws all of the points and the possible routes
	public void paintComponent(Graphics g) {
		double xScaler = (getHeight()) / (maxLat - minLat);
		double yScaler = (getWidth()) / (maxLong - minLong);
		double Scaler = Math.min(xScaler, yScaler);
		Graphics2D graphics = (Graphics2D) g;

		for (String a : roads.keySet()) {
			String x1String = intersections.get(roads.get(a).get(0)).get(0);
			String y1String = intersections.get(roads.get(a).get(0)).get(1);
			String x2String = intersections.get(roads.get(a).get(1)).get(0);
			String y2String = intersections.get(roads.get(a).get(1)).get(1);
			x1 = (maxLat - Double.parseDouble(x1String)) * Scaler;
			y1 = (Double.parseDouble(y1String) - minLong) * Scaler;
			x2 = (maxLat - Double.parseDouble(x2String)) * Scaler;
			y2 = (Double.parseDouble(y2String) - minLong) * Scaler;
			graphics.setColor(Color.BLACK);
			graphics.draw(new Line2D.Double(y1, x1, y2, x2));
		}

		for (int i = 0; i < inputs.size() - 1; i++) {
			String w1String = intersections.get(inputs.get(i)).get(0);
			String z1String = intersections.get(inputs.get(i)).get(1);
			String w2String = intersections.get(inputs.get(i + 1)).get(0);
			String z2String = intersections.get(inputs.get(i + 1)).get(1);
			w1 = (maxLat - Double.parseDouble(w1String)) * Scaler;
			z1 = (Double.parseDouble(z1String) - minLong) * Scaler;
			w2 = (maxLat - Double.parseDouble(w2String)) * Scaler;
			z2 = (Double.parseDouble(z2String) - minLong) * Scaler;
			graphics.setColor(Color.CYAN);
			graphics.draw(new Line2D.Double(z1, w1, z2, w2));
		}
	}
}