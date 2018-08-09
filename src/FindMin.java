import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindMin {
	static ArrayList<String> lats = new ArrayList<>();
	static ArrayList<String> longs = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		GUI a = new GUI();
		HashMap<String, List<String>> roads = a.roads;
		HashMap<String, List<String>> intersections = a.intersections;
		lats = latUpdate(intersections);
		longs = longUpdate(intersections);

		System.out.println("Max Lat: " + findMax(lats));
		System.out.println("Min Lat: " + findMin(lats));
		System.out.println("Max Long: " + findMax(longs));
		System.out.println("Min Long: " + findMin(longs));
	}

	// takes in intersections
	public static ArrayList<String> latUpdate(HashMap<String, List<String>> input) {
		ArrayList<String> vals = new ArrayList<>();
		
		for (String a : input.keySet()) {
			vals.addAll(input.get(a));
		}

		for (int i = 0; i < vals.size(); i++) {
			if (i % 2 == 0) {
				lats.add(vals.get(i));
			}
		}
		return lats;
	}

	public static ArrayList<String> longUpdate(HashMap<String, List<String>> input) {
		ArrayList<String> vals = new ArrayList<>();

		for (String a : input.keySet()) {
			vals.addAll(input.get(a));
		}

		for (int i = 0; i < vals.size(); i++) {
			if (i % 2 != 0) {
				longs.add(vals.get(i));
			}
		}
		return longs;
	}

	public static double findMax(ArrayList<String> input) {
		double max = Double.NEGATIVE_INFINITY;
		for (String a : input) {
			double change = Double.parseDouble(a);
			if (change > max) {
				max = change;
			}
		}
		return max;
	}
	
	public static double findMin(ArrayList<String> input) {
		double min = Double.POSITIVE_INFINITY;
		for(String a : input) {
			double change = Double.parseDouble(a);
			if(change < min) {
				min = change;
			}
		}
		return min;
	}

}
