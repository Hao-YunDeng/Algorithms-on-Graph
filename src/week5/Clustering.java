package week5;
//
//import java.util.*;
//import java.util.Scanner;
//
//public class Clustering {
//    private static double clustering(int[] x, int[] y, int k) {
//        //write your code here
//    	//Haoyun: this is clearly a Kruskal's algorithm problem
//    	//Initially we start with |V| disjoint sets. After each merging
//    	//the number of sets decreases by one. In this process, we always
//    	//merge the closest sets. Finally, when we've done |V|-k merging,
//    	//we have k disjoint sets, and then the next edge coming out is the answer.
//    	
//    	//This program is a good practice for implementing disjoint set
//    	//using tree structure, including implementing methods find(path compression),    	
//    	//and union.
//    	
//    	//First step: make sets
//    	Subset[] subsets = new Subset[x.length];
//    	for(int i = 0; i < x.length; i++) {
//    		subsets[i] = new Subset(i, 0);
//    	}
//    	
//    	//Second step: sort edges by weight
//    	List<Edge> edges = new ArrayList<>();
//    	double[][] w = new double[x.length][x.length];
//    	for(int i = 0; i < x.length; i++) {
//    		for(int j = 0; j < x.length; j++) {
//    			w[i][j] = computeDist(x[i], y[i], x[j], y[j]);
//    			edges.add(new Edge(i, j, w[i][j]));
//    		}
//    	}  
//    	Collections.sort(edges);
//    	
//    	//Third step: union the sets based on edge weights from short to long 
//    	//for |V|-k times, and then one more time to get the weight of next edge
//    	int unionCount = 0;
//    	for(Edge e : edges) {
//    		if(find(subsets, e.u) != find(subsets, e.v)) {
//    			union(subsets, e.u, e.v);
//    			unionCount++;
//    			if(unionCount > x.length - k) return e.weight;
//    			//To do full Kruskal, carry out the full iteration
//    			// and collect all the connected weights.
//    		}
//    	}
//        return -1.;
//    }
//    
//    private static double computeDist(int x1, int y1, int x2, int y2) {
//    	return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
//    }
//    
//    private static int find(Subset[] subsets, int i) {
//    	//Haoyun: learn! Here we do path compression
//    	//Very beautiful, and very important!!
//    	if(subsets[i].parent != i) {
//    		subsets[i].parent = find(subsets, subsets[i].parent); 
//    	}
//    	return subsets[i].parent;
//    }
//    
//    private static void union(Subset[] subsets, int u, int v) {
//    	int uRoot = find(subsets, u);
//    	int vRoot = find(subsets, v);
//    	if(subsets[uRoot].rank < subsets[vRoot].rank) {
//    		subsets[uRoot].parent = vRoot;
//    	}
//    	else if(subsets[uRoot].rank > subsets[vRoot].rank) {
//    		subsets[vRoot].parent = uRoot;
//    	}
//    	else {
//    		subsets[uRoot].parent = vRoot;
//    		subsets[vRoot].rank++;
//    	}
//    }
//    
//    private static class Subset{
//    	int parent;
//    	int rank;
//    	public Subset(int parent, int rank) {
//    		this.parent = parent;
//    		this.rank = rank;
//    	}
//    }
//    
//    private static class Edge implements Comparable<Edge>{
//    	int u;
//    	int v;//u, v are indices for the starting and ending points
//    	double weight;
//    	public Edge(int u, int v,  double weight) {
//    		this.u = u;
//    		this.v = v;
//    		this.weight = weight;
//    	}
//    	@Override
//    	public int compareTo(Edge that) {
//    		return this.weight > that.weight ? 1 : -1;
//    	}
//    	
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] x = new int[n];
//        int[] y = new int[n];
//        for (int i = 0; i < n; i++) {
//            x[i] = scanner.nextInt();
//            y[i] = scanner.nextInt();
//        }
//        int k = scanner.nextInt();
//        System.out.println(clustering(x, y, k));
//    }
//}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


public class Clustering {
	private static class Subset{
		int parent;
		int rank;
	}
	private static class Edge implements Comparable<Edge>{
		int u;    // src
		int v;    // des
		double weight;    // dist
		public Edge(int u, int v, double weight) {
			// TODO Auto-generated constructor stub
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge that) {
			// TODO Auto-generated method stub
			return this.weight - that.weight > 0? 1: -1;
		}
	}
	private static int find(Subset[] subsets, int i) {
		// TODO path compression
		if(subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}
	private static void union(Subset[] subsets, int u, int v) {
		// TODO union by rank
		// check Data_Structures/week2/MergingTables.java
		int uRoot = find(subsets, u);
		int vRoot = find(subsets, v);
		if(subsets[uRoot].rank < subsets[vRoot].rank) {
			subsets[uRoot].parent = vRoot;
		}
		else if (subsets[uRoot].rank > subsets[vRoot].rank) {
			subsets[vRoot].parent = uRoot;
		}
		else {
			subsets[uRoot].parent = vRoot;
			subsets[vRoot].rank++;
		}
		
	}
	private static double computeDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
    // No need to have countSets
//    private static double countSets(Subset[] subsets) {
//    	HashSet<Integer> set = new HashSet<>();
//    	for(Subset ss: subsets) {
//    		set.add(ss.parent);
//    	}
//    	return set.size();
//    }
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
    	//TODO use Kruskal's algorithm
    	// when we have k subsets, stop and calculate d
    	// TODO implement Kruskal's Algorithm
    	double result = Double.MAX_VALUE;
    	int V = x.length;
    	// make all vertices as subsets
    	Subset[] subsets = new Subset[x.length];
    	for(int u = 0; u < V; ++u) {
    		subsets[u] = new Subset();
    		subsets[u].parent = u;
    		subsets[u].rank = 0;
    	}
    	// TODO create Edges based on inputs
    	// sort them by weights
    	ArrayList<Edge> edges = new ArrayList<>();
    	for(int u = 0; u < V; ++u) {
    		for(int v = u + 1; v < V; ++v) {
    			edges.add(new Edge(u, v, computeDist(x[u], y[u], x[v], y[v])));
    		}
    	}
    	Collections.sort(edges);
    	int unionNumber = 0;
    	for(Edge edge: edges) {
    		if(find(subsets, edge.u) != find(subsets, edge.v)) {
// ref: https://github.com/akueisara/algo-on-graphs/blob/master/week%205/clustering/Clustering.java
        		if(V - unionNumber <= k) return edge.weight;
        		
    			union(subsets, edge.u, edge.v);
    			unionNumber++;
//    			//TODO when there are k sets, stop
//    			if(countSets(subsets) == k) break;
			}
    	}
//    	for(Edge edge: edges) {
//    		if(find(subsets, edge.u) != find(subsets, edge.v))
//    			result = Math.min(result, edge.weight);
//    	}
    	return -1.0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

