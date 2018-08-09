import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Graph {
	// r id intersectionid1 intersectionid2
	// i id latitude longitude

	// map of i; key = id, value = list with 2 values latitude and longitude
	// r = edges; hashmap of key = rid to iid
	public static HashMap<String, List<String>> intersections = new HashMap<>();
	public static HashMap<String, List<String>> roads = new HashMap<>();

	public class Node {
		int key;

		public Node(int key) {
			this.key = key;
		}
	}

	public class Edge {

		public Edge() {

		}
	}

	public static void main(String[] args) throws IOException {
		read("ur.txt");

	}

	public static void read(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String input = "";

		while (!((input = br.readLine()) == null)) {
			StringTokenizer st = new StringTokenizer(input);
			while (st.hasMoreTokens()) {
				String temp = st.nextToken();
				if (temp.equals("i")) {
					String next = st.nextToken();
					intersections.put(next, new ArrayList<String>());
					intersections.get(next).add(st.nextToken());
					intersections.get(next).add(st.nextToken());
				} else if (temp.equals("r")) {
					String next = st.nextToken();
					roads.put(next, new ArrayList<String>());
					roads.get(next).add(st.nextToken());
					roads.get(next).add(st.nextToken());
				}
			}

		}
	}

}
