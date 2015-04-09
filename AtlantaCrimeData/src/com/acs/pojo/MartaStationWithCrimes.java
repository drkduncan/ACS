package com.acs.pojo;

import java.util.ArrayList;

public class MartaStationWithCrimes extends MartaStation {

	ArrayList<CrimeSummary> crimes;

	public MartaStationWithCrimes(String station, String address, String city,
			int zip, double latitude, double longitude,
			ArrayList<CrimeSummary> crimes) {
		super(station, address, city, zip, latitude, longitude);
		this.crimes = crimes;
	}

	public ArrayList<CrimeSummary> getCrimes() {
		return crimes;
	}

	public void setCrimes(ArrayList<CrimeSummary> crimes) {
		this.crimes = crimes;
	}

	@Override
	public String toString() {
		return "MartaStationWithCrimes [crimes=" + crimes + "]";
	}
	

}
