package com.acs.pojo;

import java.util.ArrayList;

public class ClusterList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5112843313274883543L;
	String name;

	public ClusterList(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getGeoJSONLineString(){
		String ret = "{ \"type\": \"LineString\", \"coordinates\": [";
		for (Object o:this.toArray()){
			ClusterPoint p = (ClusterPoint)o;
			ret+=p.getCoorJSON()+",";
		}
		ret = ret.substring(0,ret.length()-1);
		ret+="]}";
		
		
//		[100.0, 0.0], [101.0, 1.0] ] }
		return ret;
	}

	@Override
	public String toString() {
		String ret="ClusterList {name=" + name +"} [";
		for (Object o:this.toArray())
			ret+=o.toString();
		ret+="]";
		return ret;
	}
}
