package com.acs.parse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.acs.calc.DistanceCalc;
import com.acs.pojo.CrimeSummary;
import com.acs.pojo.MartaStationWithCrimes;

public class DataOutput {
	public static void outputData(ArrayList<MartaStationWithCrimes> mc) {
		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		for (MartaStationWithCrimes s : mc)
			for (CrimeSummary c : s.getCrimes())
				System.out.println(s.getStation()
						+ ","
						+ year.format(c.getOccurDate())
						+ ","
						+ c.getCrimeType()
						+ ","
						+ c.getNumVictims()
						+ ","
						+ DistanceCalc.getM(s.getLatitude(), s.getLongitude(),
								c.getLatitude(), c.getLongitude()));
	}

	public static void main(String[] args) {

	}
}
