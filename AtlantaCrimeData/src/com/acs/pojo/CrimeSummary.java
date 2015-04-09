package com.acs.pojo;

import java.util.Date;

public class CrimeSummary {
	int OffenceId;
	Date reportDate;
	Date OccurDate;
	Date PossDate;
	int beat;
	String Address;
	int UCR;
	int dispoCode;
	int numVictims;
	int LocType;
	String CrimeType;
	String NPU;
	double Longitude;
	double Latitude;

	public CrimeSummary(int offenceId, Date reportDate, Date occurDate,
			Date possDate, int beat, String address, int uCR, int dispoCode,
			int numVictims, int locType, String crimeType, String nPU,
			double longitude, double latitude) {
		super();
		OffenceId = offenceId;
		this.reportDate = reportDate;
		OccurDate = occurDate;
		PossDate = possDate;
		this.beat = beat;
		Address = address;
		UCR = uCR;
		this.dispoCode = dispoCode;
		this.numVictims = numVictims;
		LocType = locType;
		CrimeType = crimeType;
		NPU = nPU;
		Longitude = longitude;
		Latitude = latitude;
	}

	public int getOffenceId() {
		return OffenceId;
	}

	public void setOffenceId(int offenceId) {
		OffenceId = offenceId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getOccurDate() {
		return OccurDate;
	}

	public void setOccurDate(Date occurDate) {
		OccurDate = occurDate;
	}

	public Date getPossDate() {
		return PossDate;
	}

	public void setPossDate(Date possDate) {
		PossDate = possDate;
	}

	public int getBeat() {
		return beat;
	}

	public void setBeat(int beat) {
		this.beat = beat;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getUCR() {
		return UCR;
	}

	public void setUCR(int uCR) {
		UCR = uCR;
	}

	public int getDispoCode() {
		return dispoCode;
	}

	public void setDispoCode(int dispoCode) {
		this.dispoCode = dispoCode;
	}

	public int getNumVictims() {
		return numVictims;
	}

	public void setNumVictims(int numVictims) {
		this.numVictims = numVictims;
	}

	public int getLocType() {
		return LocType;
	}

	public void setLocType(int locType) {
		LocType = locType;
	}

	public String getCrimeType() {
		return CrimeType;
	}

	public void setCrimeType(String crimeType) {
		CrimeType = crimeType;
	}

	public String getNPU() {
		return NPU;
	}

	public void setNPU(String nPU) {
		NPU = nPU;
	}

	@Override
	public String toString() {
		return "CrimeSummary [OffenceId=" + OffenceId + ", reportDate="
				+ reportDate + ", OccurDate=" + OccurDate + ", PossDate="
				+ PossDate + ", beat=" + beat + ", Address=" + Address
				+ ", UCR=" + UCR + ", dispoCode=" + dispoCode + ", numVictims="
				+ numVictims + ", LocType=" + LocType + ", CrimeType="
				+ CrimeType + ", NPU=" + NPU + ", Longitude=" + Longitude
				+ ", Latitude=" + Latitude + "]";
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
}
