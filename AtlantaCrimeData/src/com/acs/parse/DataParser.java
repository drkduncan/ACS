package com.acs.parse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.acs.pojo.CrimeSummary;
import com.acs.pojo.MartaStation;
import com.acs.pojo.MartaStationWithCrimes;

public class DataParser {

	public ArrayList<MartaStationWithCrimes> readStations() throws NumberFormatException,
			IOException {

		String token = ",";
		String csv = "data/friends.csv";
		BufferedReader br = null;
		ArrayList<MartaStationWithCrimes> stations = new ArrayList<MartaStationWithCrimes>();

		br = new BufferedReader(new FileReader(csv));
		String line = "";

		br.readLine(); // get rid of the header

		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split(token);
			for (String s : lineArray)
				if (s == null || s.equalsIgnoreCase("NULL"))
					s = "";
			stations.add(new MartaStationWithCrimes(lineArray[0], lineArray[1],
					lineArray[2], Integer.parseInt(lineArray[3]), Double
							.parseDouble(lineArray[4]), Double
							.parseDouble(lineArray[5]),new ArrayList<CrimeSummary>()));
		}
		br.close();
		return stations;
	}

	public ArrayList<CrimeSummary> readCrimes() throws NumberFormatException,
			IOException, ParseException {
		String token = ",";
		String csv = "data/cobra_data.csv";
		BufferedReader br = null;
		ArrayList<CrimeSummary> crimes = new ArrayList<CrimeSummary>();

		br = new BufferedReader(new FileReader(csv));
		String line = "";

		br.readLine(); // get rid of the header

		while ((line = br.readLine()) != null) {
			String[] lineArray = line.split(token);
			if (lineArray.length == 23) {
				for (String s : lineArray)
					if (s == null || s.equalsIgnoreCase("NULL"))
						s = "";

				int offenceId = getInt(lineArray[1]);
				Date reportDate = getDate(lineArray[2]);
				Date occurDate = getDateTime(lineArray[3], lineArray[4]);
				Date possDate = getDateTime(lineArray[5], lineArray[6]);
				int beat = getInt(lineArray[7]);
				String address = lineArray[10];
				int uCR = getInt(lineArray[11]);
				int dispoCode = getIntOrZero(lineArray[13]);
				int numVictims = getIntOrZero(lineArray[14]);
				int locType = getIntOrZero(lineArray[17]);
				String crimeType = lineArray[18];
				String nPU = lineArray[20];
				double longitude = Double.parseDouble(lineArray[21]);
				double latitude = Double.parseDouble(lineArray[22]);

				CrimeSummary c = new CrimeSummary(offenceId, reportDate,
						occurDate, possDate, beat, address, uCR, dispoCode,
						numVictims, locType, crimeType, nPU, longitude,
						latitude);
				crimes.add(c);
			}
			// System.err.println(c);
		}
		br.close();
		return crimes;
	}

	public int getInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			//System.out.println("Error trying to parse int from \"" + s + "\"");
			if (s.contains(" ")) {
//				System.out
//						.println("String has a space, trying to scrub and re-call");
				String s2 = s.replaceAll("\\s", "");
//				System.out.println("Tried to scrub \"" + s
//						+ "\" resulting in \"" + s2 + "\"");
				return getInt(s2);
			} else {
				System.out.println("Can't Parse, returning -1");
				return -1;
			}
		}
	}

	public Date getDateTime(String date, String time) throws ParseException {
		SimpleDateFormat dateAndTime = new SimpleDateFormat("M/d/yyyy HH:mm:ss");
		if (time == null || time.equalsIgnoreCase("null") || time.equals(""))
			return getDate(date);
		else
			return dateAndTime.parse(date + " " + time);
	}

	public Date getDate(String date) throws ParseException {
		SimpleDateFormat dateOnly = new SimpleDateFormat("M/d/yyyy");
		if (date == null || date.equals("") || date.equalsIgnoreCase("null"))
			return null;
		return dateOnly.parse(date);

	}

	public int getIntOrZero(String s) {
		try {
			if (s == null)
				return 0;
			else if (s.equals(""))
				return 0;
			else if (s.equalsIgnoreCase("null"))
				return 0;
			else
				return Integer.parseInt(s);
		} catch (NumberFormatException e) {
//			System.out.println("Got " + s
//					+ " while trying to get an Int. Returning 0");
			return 0;
		}

	}

	public static void main(String[] args) {
		DataParser mr = new DataParser();
		try {
			// System.out.println(mr.readStations());
			mr.readCrimes();
			//System.out.println(mr.readCrimes());
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

}
