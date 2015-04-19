package com.acs.pojo;

import moa.cluster.Cluster;
import moa.cluster.SphereCluster;

public class ClusterPoint {
	double longitude;
	double latitude;
	double radius;
	double weight;

	public ClusterPoint(double longitude, double radius, double latitude,double weight) {
		super();
		this.longitude = longitude;
		this.radius = radius;
		this.latitude = latitude;
		this.weight=weight;
	}
	public ClusterPoint (SphereCluster s){
		this.latitude=s.getCenter()[0];
		this.longitude=s.getCenter()[1];
		this.radius=s.getRadius();
		this.weight=s.getWeight();
	}
	public ClusterPoint (Cluster s){
		this.latitude=s.getCenter()[0];
		this.longitude=s.getCenter()[1];
		this.radius=0.0;
		this.weight=s.getWeight();
	}

	@Override
	public String toString() {
		return "ClusterPoint [longitude=" + longitude + ", latitude="
				+ latitude + ", radius=" + radius + ", weight=" + weight + "]";
	}
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public String getCoorJSON(){
		return "["+this.longitude+","+this.latitude+"]";
	}
}
