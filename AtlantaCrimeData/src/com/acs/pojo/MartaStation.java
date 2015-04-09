package com.acs.pojo;

/**
 * Pojo Class that represents a MARTA Station
 * 
 * @author dd576v
 *
 */
public class MartaStation {
	private String Station;
	private String Address;
	private String City;
	private int Zip;
	private double Latitude;
	private double Longitude;

	@Override
	public String toString() {
		return "MartaStation [Station=" + Station + ", Address=" + Address
				+ ", City=" + City + ", Zip=" + Zip + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + "]";
	}

	public MartaStation(String station, String address, String city, int zip,
			double latitude, double longitude) {
		super();
		Station = station;
		Address = address;
		City = city;
		Zip = zip;
		Latitude = latitude;
		Longitude = longitude;
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
		MartaStation ms;

		// try to parse
		try {
			ms = (MartaStation) obj;
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

}
