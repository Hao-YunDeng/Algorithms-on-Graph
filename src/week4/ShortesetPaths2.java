package week4;

import java.util.*;
//This is by Chujie
public class ShortesetPaths2 {
    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        //write your code here
      	// TODO by LCC on 12/26/2019
      	// distance: dist from s to curr
      	// reachable: 0 if no path
      	// shortest: 0 if -Inf
      	// TODO do V - 1 iterations of Bellman-Ford
      	int V = adj.length;
      	for(int i = 0; i < distance.length; ++i) {
      		distance[i] = Long.MAX_VALUE / 2; 
      	}
      	distance[s] = 0;
      	reachable[s] = 1;
      	for(int i = 0; i < V; ++i) {
      		for(int u = 0; u < V; ++u) {
      			for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
      				Relax(adj, cost, distance, reachable, shortest, u, vIdx);
      			}
      		}
      	}
      	/**
      	 * WRONG!!! This would fail when the starting node
      	 * itself is in a negative cycle. Use BFS insead!!!
      	// TODO do V more iterations, if change, 
      	// those vertices have 0 shortest
      	// TODO note that one iteration is not enough
      	for(int i = 0; i < V; ++i) {
  	    	for(int u = 0; u < V ; ++u) {
  	    		for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
  	    			boolean changed = Relax(adj, cost, distance, reachable, shortest, u, vIdx);
//  	    			System.out.println(changed);
  	    			if(changed) {
  	    				shortest[adj[u].get(vIdx)] = 0;
  	    			}
  	    		}
  	    	}
      	}
      	*/
      	// TODO V-th iteration of Bellman-Ford
      	// will give us relaxed nodes (they do not have shortest)
      	// do BFS on them will get all nodes that have ZERO shortest
      	HashSet<Integer> set = new HashSet<>();
      	for(int u = 0; u < V; ++u) {
      		boolean changed = false;
  			for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
  				changed = Relax(adj, cost, distance, reachable, shortest, u, vIdx);
  				if(changed) {
  					shortest[adj[u].get(vIdx)] = 0;
  					set.add(adj[u].get(vIdx));
  					}
  			}
  		}
      	Queue<Integer> queue = new LinkedList<Integer>();
      	for(int v: set) {
      		queue.offer(v);
      	}
      	// TODO do BFS on those vertices
      	// all visited notes should be marked with zero shortest
      	while (!queue.isEmpty()) {
  			int u = queue.poll();
  			for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
  				int v = adj[u].get(vIdx);
  				if(shortest[v] == 1) {
  					shortest[v] = 0;
  					queue.offer(v);
  				}
//  				System.out.print("#");
//  				System.out.println(adj[u].get(vIdx)+1);
  			}
  		}
      }
      
      private static boolean Relax(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, long[] distance, int[] reachable,
  			int[] shortest, int u, int vIdx) {
  		// TODO Auto-generated method stub
  		int v = adj[u].get(vIdx);
  		// TODO this is important
  		// A negative cycle that is away from s can otherwise be relaxed
  		if(distance[u] == Long.MAX_VALUE / 2) return false;
//  		System.out.print(v+1);
  		if(distance[v] > distance[u] + cost[u].get(vIdx)) {
  			reachable[v] = 1; 
  			distance[v] = distance[u] + cost[u].get(vIdx);
  			return true;
  		}
  		return false;
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
