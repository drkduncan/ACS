package com.acs.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import moa.cluster.Cluster;

import com.acs.calc.DistanceCalc;
import com.acs.calc.KMeans;
import com.acs.parse.DataParser;
import com.acs.pojo.ClusterList;
import com.acs.pojo.ClusterPoint;
import com.acs.pojo.CrimeCluster;
import com.acs.pojo.MartaCluster;
import com.acs.uitl.Props;

public class ProcessKMeans {
	public static void run() {
		DataParser p = new DataParser();

		try {
			ArrayList<MartaCluster> stations = p.getStationClusters();
			ArrayList<CrimeCluster> crimes = p.getCrimeClusters();

			for (MartaCluster m : stations) {
				for (CrimeCluster c : crimes) {
					if (DistanceCalc.getM(m.getLatitude(), m.getLongitude(),
							c.getLatitude(), c.getLongitude()) < Props.radius) {
						m.getCrimes().add(c);
					}
				}
			}
			Cluster[] a = {};
			long start = System.currentTimeMillis();
			ArrayList<ClusterList<ClusterPoint>> c = KMeans.kMeansPoints(
					stations.toArray(a), crimes);
			long end = System.currentTimeMillis();

			System.err.println("Complete in " + (end - start) + " Milli's");

//			String JSON1 = "{  \"type\": \"Feature\",  \"geometry\": ";
//			String JSON2 = ",  \"properties\": {    \"name\": \"";
//			String JSON3 = "\"  }}";
			for (ClusterList<ClusterPoint> l:c)
				p.writeJSONLines(l);
			
			for (ClusterList<ClusterPoint> l:c){
				ClusterPoint last = l.get(l.size()-1);
				System.out.println("cluster['"+l.getName()+"'] = {  center: new google.maps.LatLng("+last.getLatitude()+", "+last.getLongitude()+"),  radius: "+last.getRadius()+"};");
			}
			
			
			System.out.println(c);

			// for (MartaStationWithCrimes m : stations) {
			// if (m.getCity().equalsIgnoreCase("Atlanta"))
			// System.out.println("The " + m.getStation() + "("
			// + m.getCity() + ") had " + m.getCrimes().size()
			// + " crimes reported within " + distance
			// + " Meters.");
			// }
			//
			// for (MartaStationWithCrimes m : stations)
			// for (MartaStationWithCrimes o : stations) {
			// double dist = DistanceCalc
			// .getM(m.getLatitude(), m.getLongitude(),
			// o.getLatitude(), o.getLongitude());
			// if (dist <= distance && dist != 0.0)
			// System.out.println(m.getStation() + " is " + dist
			// + " Meters from " + o.getStation());
			// }
			//
			// DataOutput.outputData(stations);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ProcessKMeans.run();

	}
}
