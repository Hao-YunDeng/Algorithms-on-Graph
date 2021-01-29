package week4;

import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        // write your code here
    	for(int i = 0; i < distance.length; i++) {
    		distance[i] = Long.MAX_VALUE / 2;
    	}
    	
    	HashSet<Integer> set = new HashSet<>();
    	// Do |V| passes of Bellman - Ford
    	// and save the vertices relaxed in the last pass into set 
    	distance[s] = 0;
    	reachable[s] = 1;
    	for(int i = 0; i < adj.length; i++) {
    		for(int u = 0; u < adj.length; u++) {
    			for(int vIdx = 0; vIdx < adj[u].size(); vIdx++) {
    				int v = adj[u].get(vIdx);
    				if(distance[u] < Long.MAX_VALUE / 2 && distance[v] > distance[u] + cost[u].get(vIdx)) {
    					// Having distance[u] < Long.MAX_VALUE / 2 is crucial to tell if v is reachable from s
    					// Remember: v is reachable from s only if u is reachable from s
    					// Why whether reachable from s or not is so crucial:
    					// only vertices/negative loops reachable from s can be added to the set!
    					// and if a cycle is not reachable it shouldn't affect other points at all!
    					// Try this test case: 
    					// 6 8
    					// 2 3 -1
    					// 3 2 -1
    					// 1 4 1
    					// 1 5 1
    					// 1 6 1
    					// 2 4 1
    					// 2 5 1
    					// 2 6 1
    					// 1
    					//The correct output should be 0 * * 1 1 1, not 0 - - - - - 
    					reachable[v] = 1;
    					distance[v] = distance[u] + cost[u].get(vIdx);
    					if(i == adj.length - 1) {
    						set.add(v);
    						shortest[v] = 0;//Doesn't seem to be necessary 
    					}
    				}
    			}
    		}
    	}
    	// Now do BFS for vertices in the set
    	Queue<Integer> q = new LinkedList<Integer>();
    	for(int v : set) {
    		q.offer(v);
    	}
    	while(!q.isEmpty()) {
    		int u = q.poll();
    		for(int v : adj[u]) {
    			if(shortest[v] == 1) {
    				shortest[v] = 0;
    				q.offer(v);
    			}
    		}
    	}
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

