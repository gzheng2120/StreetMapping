
public class Edge implements Comparable<Edge> {

	public double distance;
	public String endIntersection;
	
	@Override
	public int compareTo(Edge another) {
		// TODO Auto-generated method stub
		if (this.distance > another.distance) return 1;
		else if (this.distance < another.distance) return -1;
		else return 0;
	}
	
	public Edge(double distance, String endIntersection) {
		this.distance = distance;
		this.endIntersection = endIntersection;
	}
	
}
