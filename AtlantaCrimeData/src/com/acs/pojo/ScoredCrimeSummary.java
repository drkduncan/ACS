package com.acs.pojo;

import java.util.Date;

public class ScoredCrimeSummary extends CrimeSummary {
	double score;

	public ScoredCrimeSummary(int offenceId, Date reportDate, Date occurDate,
			Date possDate, int beat, String address, int uCR, int dispoCode,
			int numVictims, int locType, String crimeType, String nPU,
			double longitude, double latitude, double score) {
		super(offenceId, reportDate, occurDate, possDate, beat, address, uCR,
				dispoCode, numVictims, locType, crimeType, nPU, longitude,
				latitude);
		this.score = score;
	}
	public ScoredCrimeSummary(int offenceId, Date reportDate, Date occurDate,
			Date possDate, int beat, String address, int uCR, int dispoCode,
			int numVictims, int locType, String crimeType, String nPU,
			double longitude, double latitude) {
		super(offenceId, reportDate, occurDate, possDate, beat, address, uCR,
				dispoCode, numVictims, locType, crimeType, nPU, longitude,
				latitude);
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
