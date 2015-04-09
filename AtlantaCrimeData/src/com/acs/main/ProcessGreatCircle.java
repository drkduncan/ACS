package com.acs.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.acs.calc.DistanceCalc;
import com.acs.parse.DataOutput;
import com.acs.parse.DataParser;
import com.acs.pojo.CrimeSummary;
import com.acs.pojo.MartaStation;
import com.acs.pojo.MartaStationWithCrimes;

public class ProcessGreatCircle {
	public static void run() {
		DataParser p = new DataParser();
		int distance = 250;

		try {
			ArrayList<MartaStationWithCrimes> stations = p.readStations();
			ArrayList<CrimeSummary> crimes = p.readCrimes();

			for (MartaStationWithCrimes m : stations) {
				for (CrimeSummary c : crimes) {
					if (DistanceCalc.getM(m.getLatitude(), m.getLongitude(),
							c.getLatitude(), c.getLongitude()) < distance) {
						m.getCrimes().add(c);
					}
				}
			}

			for (MartaStationWithCrimes m : stations) {
				if (m.getCity().equalsIgnoreCase("Atlanta"))
					System.out.println("The " + m.getStation() + "("
							+ m.getCity() + ") had " + m.getCrimes().size()
							+ " crimes reported within " + distance
							+ " Meters.");
			}

			for (MartaStationWithCrimes m : stations)
				for (MartaStationWithCrimes o : stations) {
					double dist = DistanceCalc
							.getM(m.getLatitude(), m.getLongitude(),
									o.getLatitude(), o.getLongitude());
					if (dist <= distance && dist != 0.0)
						System.out.println(m.getStation() + " is " + dist
								+ " Meters from " + o.getStation());
				}

			DataOutput.outputData(stations);

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
		ProcessGreatCircle.run();

	}

}
