
public class Haversine {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    
    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
    
    public double calculateDistanceBetween(Node node1, Node node2) {
    	double startLat = node1.latitude;
    	double startLong = node1.longitude;
    	double endLat = node2.latitude;
    	double endLong = node2.longitude;
    	
    	double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }
}