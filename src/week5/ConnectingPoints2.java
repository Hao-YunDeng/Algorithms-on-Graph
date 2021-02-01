package week5;

import java.util.*;

public class ConnectingPoints2 {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        //Haoyun: in this class we copied Chujie's Kruskal algorithm
        result = Kruskal(x, y);
        return result;
    }
    
    private static double Kruskal(int[] x, int[] y) {   	
    	Subset[] subsets = new Subset[x.length];
    	for(int u = 0; u < x.length; ++u) {
    		subsets[u] = new Subset();
    		subsets[u].parent = u;
    		subsets[u].rank = 0;
    	}
    	ArrayList<Edge> edges = new ArrayList<>();
    	for(int u = 0; u < x.length; ++u) {
    		for(int v = u + 1; v < x.length; ++v) {
    			edges.add(new Edge(u, v, computeDist(x[u], y[u], x[v], y[v])));
    		}
    	}
    	
    	Collections.sort(edges);
    	
    	double result = 0;
    	for(Edge edge: edges) {
    		if(find(subsets, edge.u) != find(subsets, edge.v)) {
    			result = result + edge.weight;
    			union(subsets, edge.u, edge.v);
    		}
    	}
    	return result;
    }
    
	private static double computeDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
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
			return this.weight - that.weight > 0? 1: -1;
		}
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
        System.out.println(minimumDistance(x, y));
    }
}

