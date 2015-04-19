/*
 *    KMeans.java
 *    Copyright (C) 2010 RWTH Aachen University, Germany
 *    @author Jansen (moa@cs.rwth-aachen.de)
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program. If not, see <http://www.gnu.org/licenses/>.
 *    
 */

package com.acs.calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.acs.pojo.ClusterList;
import com.acs.pojo.ClusterPoint;
import com.acs.pojo.MartaCluster;

import moa.cluster.CFCluster;
import moa.cluster.Cluster;
import moa.cluster.Clustering;
import moa.cluster.SphereCluster;

public class KMeans {
    public static ArrayList<ClusterList<ClusterPoint>> kMeansPoints(Cluster[] centers, List<? extends Cluster> data ) {
        int k = centers.length;

        int dimensions = centers[0].getCenter().length;

        ArrayList<ArrayList<Cluster>> clustering = new ArrayList<ArrayList<Cluster>>();
        ArrayList<ClusterList<ClusterPoint>> ret = new ArrayList<ClusterList<ClusterPoint>>();
        for ( int i = 0; i < k; i++ ) {
            clustering.add( new ArrayList<Cluster>() );
            ret.add( new ClusterList<ClusterPoint>(((MartaCluster)centers[i]).getStation()) );
        }
        for(int i = 0; i<centers.length;i++)
        	ret.get(i).add(new ClusterPoint(centers[i]));

        int repetitions = 56;
        while ( repetitions-- >= 0 ) {
            // Assign points to clusters
            for ( Cluster point : data ) {
                double minDistance = distance( point.getCenter(), centers[0].getCenter() );
                int closestCluster = 0;
                for ( int i = 1; i < k; i++ ) {
                    double distance = distance( point.getCenter(), centers[i].getCenter() );
                    if ( distance < minDistance ) {
                        closestCluster = i;
                        minDistance = distance;
                    }
                }

                clustering.get( closestCluster ).add( point );
            }

            // Calculate new centers and clear clustering lists
            SphereCluster[] newCenters = new SphereCluster[centers.length];
            for ( int i = 0; i < k; i++ ) {
            	SphereCluster sc = calculateCenter( clustering.get( i ), dimensions );
            	sc.setId(i+1);
                newCenters[i] = sc;
                clustering.get( i ).clear();
            }
//            System.out.println("Repition: "+repetitions);
            //System.out.println("Old Centers: "+Arrays.toString(centers));
            //System.out.println("New Centers: "+Arrays.toString(newCenters));
            String out= "{\"repition\":\""+(56-repetitions)+"\", \"clusters\":[";
            for (SphereCluster s:newCenters)
            	out+="{\"id\":\""+s.getId()+"\", \"Lat\":\""+s.getCenter()[0]+"\", \"Long\":\""+s.getCenter()[1]+"\", \"radius\":\""+s.getRadius()+"\"},";
            out = out.substring(0, out.length()-1);
            out+= "]}";
            
            
            System.out.println(out);
            
            for(int i = 0; i<newCenters.length;i++)
            	ret.get(i).add(new ClusterPoint(newCenters[i]));
            
            centers = newCenters;
        }

        return ret;
    }

    public static Clustering kMeans(Cluster[] centers, List<? extends Cluster> data ) {
        int k = centers.length;

        int dimensions = centers[0].getCenter().length;

        ArrayList<ArrayList<Cluster>> clustering =
                new ArrayList<ArrayList<Cluster>>();
        for ( int i = 0; i < k; i++ ) {
            clustering.add( new ArrayList<Cluster>() );
        }

        int repetitions = 100;
        while ( repetitions-- >= 0 ) {
            // Assign points to clusters
            for ( Cluster point : data ) {
                double minDistance = distance( point.getCenter(), centers[0].getCenter() );
                int closestCluster = 0;
                for ( int i = 1; i < k; i++ ) {
                    double distance = distance( point.getCenter(), centers[i].getCenter() );
                    if ( distance < minDistance ) {
                        closestCluster = i;
                        minDistance = distance;
                    }
                }

                clustering.get( closestCluster ).add( point );
            }

            // Calculate new centers and clear clustering lists
            SphereCluster[] newCenters = new SphereCluster[centers.length];
            for ( int i = 0; i < k; i++ ) {
            	SphereCluster sc = calculateCenter( clustering.get( i ), dimensions );
            	sc.setId(i+1);
                newCenters[i] = sc;
                clustering.get( i ).clear();
            }
//            System.out.println("Repition: "+repetitions);
            //System.out.println("Old Centers: "+Arrays.toString(centers));
            //System.out.println("New Centers: "+Arrays.toString(newCenters));
            String out= "{\"repition\":\""+(100-repetitions)+"\", \"clusters\":[";
            for (SphereCluster s:newCenters)
            	out+="{\"id\":\""+s.getId()+"\", \"Lat\":\""+s.getCenter()[0]+"\", \"Long\":\""+s.getCenter()[1]+"\", \"radius\":\""+s.getRadius()+"\"},";
            out = out.substring(0, out.length()-1);
            out+= "]}";
            
            System.out.println(out);
            centers = newCenters;
        }

        return new Clustering( centers );
    }

    private static double distance(double[] pointA, double [] pointB){
        return DistanceCalc.getM(pointA[0], pointA[1], pointB[0], pointB[1]);
    }


    private static SphereCluster calculateCenter( ArrayList<Cluster> cluster, int dimensions ) {
        double[] res = new double[dimensions];
        for ( int i = 0; i < res.length; i++ ) {
            res[i] = 0.0;
        }

        if ( cluster.size() == 0 ) {
            return new SphereCluster( res, 0.0 );
        }

        for ( Cluster point : cluster ) {
            double [] center = point.getCenter();
            for (int i = 0; i < res.length; i++) {
               res[i] += center[i];
            }
        }

        // Normalize
        for ( int i = 0; i < res.length; i++ ) {
            res[i] /= cluster.size();
        }

        // Calculate radius
        double radius = 0.0;
        for ( Cluster point : cluster ) {
            double dist = distance( res, point.getCenter() );
            if ( dist > radius ) {
                radius = dist;
            }
        }

        return new SphereCluster( res, radius );
    }

    public static Clustering gaussianMeans(Clustering gtClustering, Clustering clustering) {
        ArrayList<CFCluster> microclusters = new ArrayList<CFCluster>();
        for (int i = 0; i < clustering.size(); i++) {
            if (clustering.get(i) instanceof CFCluster) {
                microclusters.add((CFCluster)clustering.get(i));
            }
            else{
                System.out.println("Unsupported Cluster Type:"+clustering.get(i).getClass()
                        +". Cluster needs to extend moa.cluster.CFCluster");
            }
        }
        Cluster[] centers = new Cluster[gtClustering.size()];
        for (int i = 0; i < centers.length; i++) {
            centers[i] = gtClustering.get(i);

        }

        int k = centers.length;
        if ( microclusters.size() < k ) {
            return new Clustering( new Cluster[0]);
        }

        Clustering kMeansResult = kMeans( centers, microclusters );

        k = kMeansResult.size();
        CFCluster[] res = new CFCluster[ k ];

        for ( CFCluster microcluster : microclusters) {
            // Find closest kMeans cluster
            double minDistance = Double.MAX_VALUE;
            int closestCluster = 0;
            for ( int i = 0; i < k; i++ ) {
                double distance = distance( kMeansResult.get(i).getCenter(), microcluster.getCenter() );
                if ( distance < minDistance ) {
                    closestCluster = i;
                    minDistance = distance;
                }
            }

            // Add to cluster
            if ( res[closestCluster] == null ) {
                res[closestCluster] = (CFCluster)microcluster.copy();
            } else {
                res[closestCluster].add(microcluster);
            }
        }

        // Clean up res
        int count = 0;
        for ( int i = 0; i < res.length; i++ ) {
            if ( res[i] != null )
                ++count;
        }

        CFCluster[] cleaned = new CFCluster[count];
        count = 0;
        for ( int i = 0; i < res.length; i++ ) {
            if ( res[i] != null )
                cleaned[count++] = res[i];
        }

        return new Clustering( cleaned );
    }

}
