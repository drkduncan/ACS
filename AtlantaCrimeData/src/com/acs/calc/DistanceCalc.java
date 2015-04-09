package com.acs.calc;

/**
 * Great Circle distance calculation.
 * 
 * The following formula assumes that sin, cos, and arcos are comptued in
 * degrees, so need to convert back and forth between radians.
 *
 * d = 60 * acos (sin(L1)*sin(L2) + cos(L1)*cos(L2)*cos(G1 - G2))
 * 
 * Based on GreatCircle.java by Robert Sedgewick and Kevin Wayne
 * 
 * 
 * @author Dan Duncan and Brad Thomas
 * @version 1.0
 * @since 2015-03-31
 * @see <a
 *      href="http://introcs.cs.princeton.edu/java/12types/GreatCircle.java.html">GreatCircle.java</a>
 */
public class DistanceCalc {
	/**
	 * Given the latitude and longitude (in degrees) of two points compute the
	 * great circle distance (in nautical miles) between them.
	 * 
	 * @param lat1
	 *            latitude of source point
	 * @param long1
	 *            longitude of source point
	 * @param lat2
	 *            latitude of target point
	 * @param long2
	 *            longitude of target point
	 * @return Distance between 2 points in nautical miles (double)
	 */
	public static double getNMiles(double lat1, double long1, double lat2,
			double long2) {
		double x1 = Math.toRadians(lat1);
		double y1 = Math.toRadians(long1);
		double x2 = Math.toRadians(lat2);
		double y2 = Math.toRadians(long2);

		/*************************************************************************
		 * Compute using law of cosines
		 *************************************************************************/
		// great circle distance in radians
		double angle1 = Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1)
				* Math.cos(x2) * Math.cos(y1 - y2));

		// convert back to degrees
		angle1 = Math.toDegrees(angle1);

		// each degree on a great circle of Earth is 60 nautical miles
		double distance1 = 60 * angle1;

		//System.out.println(distance1 + " nautical miles");

		/*************************************************************************
		 * Compute using Haverside formula
		 *************************************************************************/
		double a = Math.pow(Math.sin((x2 - x1) / 2), 2) + Math.cos(x1)
				* Math.cos(x2) * Math.pow(Math.sin((y2 - y1) / 2), 2);

		// great circle distance in radians
		double angle2 = 2 * Math.asin(Math.min(1, Math.sqrt(a)));

		// convert back to degrees
		angle2 = Math.toDegrees(angle2);

		// each degree on a great circle of Earth is 60 nautical miles
		double distance2 = 60 * angle2;
		return distance2;
	}

	/**
	 * Get distance in Miles
	 * 
	 * @see DistanceCalc#getNMiles
	 * @see DistanceCalc#nMileToMile(double)
	 */
	public static double getUSMiles(double lat1, double long1, double lat2,
			double long2) {
		// 1 nautical mile = 1.15078 US miles
		return nMileToMile(getNMiles(lat1, long1, lat2, long2));
	}

	/**
	 * Get distance in Kilometers
	 * 
	 * @see DistanceCalc#DistanceCalc()
	 * @see DistanceCalc#nMileTokilometer(double)
	 */
	public static double getKM(double lat1, double long1, double lat2,
			double long2) {
		// 1 nautical mile = 1.852 kilometers
		return nMileToKilometer(getNMiles(lat1, long1, lat2, long2));
	}

	/**
	 * Get distance in Meters
	 * 
	 * @see DistanceCalc#DistanceCalc()
	 * @see DistanceCalc#nMileToMeter(double)
	 */
	public static double getM(double lat1, double long1, double lat2,
			double long2) {
		// 1 nautical mile = 1852 meters
		return nMileToMeter(getNMiles(lat1, long1, lat2, long2));
	}

	/**
	 * Convert Nautical Miles to US Miles (1 NM = 1.15078 Miles)
	 * 
	 * @param
	 * @return US Miles (double)
	 */
	public static double nMileToMile(double nautilcalMiles) {
		return nautilcalMiles * 1.15078;
	}

	/**
	 * Convert Nautical Miles to Kilometers (1 NM = 1.852 Kilometers)
	 * 
	 * @param
	 * @return Kilometers (double)
	 */
	public static double nMileToKilometer(double nautilcalMiles) {
		return nautilcalMiles * 1.852;
	}

	/**
	 * Convert Nautical Miles to Meters (1 NM = 1852 Meters)
	 * 
	 * @param
	 * @return Meters (double)
	 */
	public static double nMileToMeter(double nautilcalMiles) {
		return nautilcalMiles * 1852;
	}
}
