
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class main {

	public static Map<String, Node> intersectionMap;
	public static Haversine haversine;
	public static PriorityQueue<Node> queue;
	public static boolean connected;
	public static Map<String, Boolean> intersectionVisitStage;
	
	public static void addEdge(String i, String j) {
		Node intersection1 = intersectionMap.get(i);//get the corresponding Node for ID i
		Node intersection2 = intersectionMap.get(j);//get the corresponding Node for ID j
		
		
		if (!intersection1.allNeighborsID.contains(j)) intersection1.allNeighborsID.add(j);//add j to i adjacency list
		if  (!intersection2.allNeighborsID.contains(i)) intersection2.allNeighborsID.add(i);//add i to j adjacency list
	}
	
	public static List<Node> getShortestPath(String start, String destination) {
		
		ArrayList<Node> shortestPath = new ArrayList<>();
		dijkstra(start);
		
		Node startingIntersection = intersectionMap.get(start);
		Node destinateIntersection = intersectionMap.get(destination);
		Stack<Node> stack = new Stack<>();
		
		while(destinateIntersection.parent!=null && destinateIntersection!=startingIntersection) {
			stack.push(destinateIntersection);
			destinateIntersection = destinateIntersection.parent;
		}
		
		if (destinateIntersection!=startingIntersection) {
			connected = false;//there is no path between 2 intersections
		}
		else stack.push(startingIntersection);//add the beginning intersection
		while (!stack.isEmpty()) shortestPath.add(stack.pop());//pop the order of the node from the lastest added to the oldest
		return shortestPath;
	}
	
	public static void print(List<Node> path) {
		if (!connected) {
			System.out.println("There is no path");
		} 
		else {
			System.out.println("Path");
			for (Node eachIntersection: path) {//for each intersection in the shortest path
				System.out.print(eachIntersection.intersectionID + " ");
			}
			System.out.println();
			System.out.println("Travel distance " + path.get(path.size()-1).dist);
		}
	}
	
	public static void dijkstra(String startingPointID) {
		Node startingIntersection = intersectionMap.get(startingPointID);
		startingIntersection.allNeighborsID.add(startingPointID);
		
		startingIntersection.dist = 0;
		
		queue.offer(startingIntersection);//add the starting intersection into the queue
		
		while (!queue.isEmpty()) {
			//for the queue to adjust its elements' order
			
			
			Node smallestDistance = queue.poll();
			smallestDistance.visited = true;
			while (!queue.isEmpty() && smallestDistance.intersectionID.equals(queue.peek().intersectionID)) {
				queue.poll();
			}
			intersectionMap.put(smallestDistance.intersectionID, smallestDistance);
			
		//	System.out.println(smallestDistance.intersectionID + " " + smallestDistance.visited);
			for (String neighborID: smallestDistance.allNeighborsID) {//iterate through all neighbors of the smallest distance node
				if (!intersectionMap.get(neighborID).visited) {

			//		System.out.println(intersectionMap.get(neighborID).intersectionID + " " + intersectionMap.get(neighborID).visited);
					
					relax(smallestDistance, intersectionMap.get(neighborID));
					if(!intersectionMap.get(neighborID).visited) {
						queue.offer(intersectionMap.get(neighborID));

						//System.out.println("ohh okay!");

					}
				}
			}
			
		}

	}
	
	public static void relax(Node u, Node v) {
		double distance = Math.abs(haversine.calculateDistanceBetween(u, v));
		if (u.dist + distance < v.dist) {
			v.dist = u.dist + distance;
			v.parent = u;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Inp command = new Inp();
		intersectionMap = new HashMap<>();
		
		//put the haversine formula into a class called Haversine
		haversine = new Haversine();
		
		//initiate a priority queue
		queue = new PriorityQueue<>();
		
		//initate the boolean value that indicates the connectedness of the graph
		connected = true;
		
		intersectionVisitStage = new HashMap<>();
		
		while (!command.exit) {//repeat until the user wants to exit program: works
			//test input method: works
			File file = new File(command.inputFile);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine())!=null) {
				if (line.charAt(0) == 'i') {//if it is an intersection
					String[] wordsInCommandLine = line.split("\\s+");//split
					
					//take out the intersectionID from the command line
					String intersectionID = wordsInCommandLine[1];
					
					//change the latitude and longitude from string to Double
					Double latitude = Double.valueOf(wordsInCommandLine[2]);
					Double longitude = Double.valueOf(wordsInCommandLine[3]);
					
					//create a new node with information taken from the command line
					Node newNode = new Node(latitude, longitude, null);
					newNode.dist = Math.abs(Double.MAX_VALUE);
					newNode.intersectionID = intersectionID;
					
					//add the new node to the map that connects the intersectionID with the node
					intersectionMap.put(intersectionID, newNode);
					intersectionVisitStage.put(intersectionID, false);
				}
				else if (line.charAt(0) == 'r') {//if it is a road
					String[] wordsInCommandLine = line.split("\\s+");
					
					//extract the intersection1 and intersection 2 from the command line
					String Intersection1ID = wordsInCommandLine[2];
					String Intersection2ID = wordsInCommandLine[3];
					
					addEdge(Intersection1ID, Intersection2ID);
				}
			}
			/*
			for (String ID: intersectionMap.keySet()) {
				System.out.println(ID + " " +intersectionMap.get(ID).dist);
			}
			*/
			
			if (command.directions && command.show) {
				List<Node> path = getShortestPath(command.startAt, command.destination);
				GUIFrame frame = new GUIFrame(path, command.inputFile);
			}
			else if (command.directions && !command.show) {
				print(getShortestPath(command.startAt, command.destination));
				
			}
			else if (!command.directions && command.show) {
				GUIFrame frame = new GUIFrame(null, command.inputFile);
			}
			/*
			for (String id: intersectionMap.keySet()) {
				System.out.println(id + " " + intersectionMap.get(id).visited);
			}*/
			br.close();
			command = new Inp();
		}
	}
	
}
