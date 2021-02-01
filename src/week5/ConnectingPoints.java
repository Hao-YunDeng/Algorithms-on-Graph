package week5;

import java.util.*;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        //Haoyun: in this class we implement Prim's algorithm
        result = Prim(x, y);
        return result;
    }
    
    private static double Prim(int[] x, int[] y) {
    	double[] cost = new double[x.length];
    	for(int i = 0; i < cost.length; i++) {
    		cost[i] = Integer.MAX_VALUE;
    	}
    	cost[0] = 0;
    	PriorityQueue<Vertex> pq = new PriorityQueue<>();
    	//Haoyun: just like Dijkstra's algorithm, we use set to record to-be-processed vertices
    	HashSet<Integer> set = new HashSet<>();
    	for(int i = 0; i < x.length; i++) {
    		Vertex v = new Vertex(i, cost[i]);
    		pq.add(v);
    		set.add(i);
    	}
    	//Now, we pre-compute the weights
    	double[][] w = new double[x.length][x.length];
    	for(int i = 0; i < x.length; i++) {
    		for(int j = 0; j < x.length; j++) {
    			w[i][j] = computeDist(x[i], y[i], x[j], y[j]);
    		}
    	}
    	while(!set.isEmpty()) {
    		Vertex vertex = pq.poll();
    		int v = vertex.index;
    		//if(set.contains(v)) { //this condition is not really necessary
        		set.remove(v);
        		for(int z = 0; z < x.length; z++) {
        			if(set.contains(z) && cost[z] > w[v][z]) {
        				cost[z] = w[v][z];
        				pq.add(new Vertex(z, cost[z]));
        			}
        		}
    		//} 		
    	}
    	double res = 0;
    	for(int i = 0; i < cost.length; ++i) {
    		res += cost[i];
    	}
    	return res;
    }
    
    private static double computeDist(int x1, int y1, int x2, int y2) {
    	return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    private static class Vertex implements Comparable<Vertex>{
    	int index;
    	double cost;
    	public Vertex(int index, double cost) {
    		this.index = index;
    		this.cost = cost;
    	}
    	@Override
    	public int compareTo(Vertex that) {
    		return (this.cost - that.cost > 0? 1: -1);
    		//Haoyun: Important!! The below compare method gives wrong result when the difference is less than one!
    		//return (int)(this.cost - that.cost);
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

