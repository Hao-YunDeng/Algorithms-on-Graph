package week4;

import java.util.*;

class Vertex implements Comparable<Vertex>{
	int index;
	int dist;
	public Vertex(int index, int dist) {
		this.index = index;
		this.dist = dist;
	}
	@Override
	public int compareTo(Vertex that) {
		return this.dist - that.dist;
	}
}

public class Dijkstra {
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	int[] dist = new int[adj.length];
    	//int max = adj.length;
    	//The edges are weighted! You cannot use num of vertices!
    	for(int i = 0; i < dist.length; i++) {
    		dist[i] = Integer.MAX_VALUE / 2;
    		//Integer.MAX_VALUE may have over flow. See below
    	}
    	dist[s] = 0;
    	
    	PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    	//Haoyun: Chujie's idea. Use set to record whether a vertex added/removed
    	//This is only for change priority
    	HashSet<Integer> set = new HashSet<>();
    	for(int i = 0; i < adj.length; i++) {
    		Vertex v = new Vertex(i, dist[i]);
    		pq.add(v);
    		set.add(i);
    	}

    	
    	while(!set.isEmpty()) {
    		Vertex u = pq.poll();
    		//u is now removed from pq!
    		
    		//Even u's that are not connected to s at all will be extracted at last
    		//Then dist[u] + cost will over flow and become negative!
    		//Then this negative value will be passed to dist v!
    		//To prevent this, use long for dist to accept int max
    		//Or use int max / 2
    		
    		//System.out.println(u.index + 1);
    		//We only process vertex u if u is in the set
    		//otherwise it is just an extra copy
    		if(set.contains(u.index)) {
    			set.remove(u.index);
        		for(int i = 0; i < adj[u.index].size(); i++) {
        			int v =  adj[u.index].get(i);
        			if(dist[v] > dist[u.index] + cost[u.index].get(i)) {
        				dist[v] = dist[u.index] + cost[u.index].get(i);
        				pq.add(new Vertex(v, dist[v]));
        				//Now, when you add to pq, v is duplicated in pq;
        				//the smaller one us valid while the larger one is an extra copy
        				//After we processed the smaller one, we remove it from set
        				//Later we will encounter the extra copy, and just discard it from pq
        			}
        		}
    		}   		
    	}
    	//Haoyun: remember 
    	return (dist[t] < Integer.MAX_VALUE / 2) ? dist[t]: - 1;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

