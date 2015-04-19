package com.acs.pojo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import com.acs.calc.DistanceCalc;
import com.acs.parse.DataParser;
import com.acs.uitl.Props;

import weka.core.Instance;
import moa.cluster.Cluster;

public class MartaCluster extends Cluster {
	private String Station;
	private String Address;
	private String City;
	private int Zip;
	private double Latitude;
	private double Longitude;
	private ArrayList<CrimeCluster> crimes;

	@Override
	public String toString() {
		return "MartaStation [Station=" + Station + ", Address=" + Address
				+ ", City=" + City + ", Zip=" + Zip + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + "]";
	}

	public MartaCluster(String station, String address, String city, int zip,
			double latitude, double longitude,ArrayList<CrimeCluster> crimes) {
		super();
		Station = station;
		Address = address;
		City = city;
		Zip = zip;
		Latitude = latitude;
		Longitude = longitude;
		this.crimes = crimes;
		
	}

	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public int getZip() {
		return Zip;
	}

	public void setZip(int zip) {
		Zip = zip;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	@Override
	public boolean equals(Object obj) {
		MartaCluster ms;

		// try to parse
		try {
			ms = (MartaCluster) obj;
		} catch (Exception e) {
			return false;
		}

		if (!this.Address.equalsIgnoreCase(ms.Address))
			return false;
		else if (!this.Station.equalsIgnoreCase(ms.Station))
			return false;
		else if (!this.City.equalsIgnoreCase(ms.City))
			return false;
		else if (this.Zip != ms.Zip)
			return false;
		else if (this.Latitude != ms.Latitude)
			return false;
		else if (this.Longitude != ms.Longitude)
			return false;
		else
			return true;
	}

	@Override
	public double[] getCenter() {
		double[] ret = {Latitude,Longitude};
		return ret;
	}

	@Override
	public double getWeight() {
		return crimes.size();
	}

	@Override
	public double getInclusionProbability(Instance instance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Instance sample(Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<CrimeCluster> getCrimes() {
		// TODO Auto-generated method stub
		return crimes;
	}

}
