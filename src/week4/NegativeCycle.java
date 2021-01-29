package week4;

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
    	int[] dist = new int[adj.length];
    	for(int i = 0; i < dist.length; i++) {
    		dist[i] = Integer.MAX_VALUE / 2;
    	}
    	//Just take vertex 0 as the starting point
    	dist[0] = 0;
    	for(int i = 0; i < adj.length; i++) {
    		for(int u = 0; u < adj.length; u++) {
    			for(int vIdx = 0; vIdx < adj[u].size(); vIdx++) {
    				int v = adj[u].get(vIdx);
    				if(dist[v] > dist[u] + cost[u].get(vIdx)) {
    					dist[v] = dist[u] + cost[u].get(vIdx);
    					if(i == adj.length - 1) {
    						return 1;
    					}
    				}
    			}
    		}
    	}
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

