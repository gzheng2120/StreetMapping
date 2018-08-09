
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node implements Comparable<Node>{

	public double latitude, longitude;
	public Node parent;
	public Set<String> allNeighborsID;//adjacency list
	public boolean visited;
	public double dist = 1;
	public String intersectionID = "";
	
	public Node(double latitude, double longitude, Node parent) {
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.parent = parent;
		visited = false;
		intersectionID = "";
		dist = 1;
		
		allNeighborsID = new HashSet<>();//list of all other intersections that can go to this intersection
	}

	@Override
	public int compareTo(Node another) {
		// TODO Auto-generated method stub
		if (this.dist > another.dist) return 1;
		else if (this.dist < another.dist) return -1;
		else return 0;
	}
	
	//moi lan them 1 dinh vao thi update cai priorty queue trong node de lay ra canh nho nhat
	
}
