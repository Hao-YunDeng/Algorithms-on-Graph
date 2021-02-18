package week3;

//Haoyun: Below is Chujie's work. Does not pass either.

//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class Bipartite {
//    private static int bipartite(ArrayList<Integer>[] adj) {
//        //write your code here
//    	int[] color = new int[adj.length];
//    	color[0] = 1;
//    	Queue<Integer> q = new LinkedList<>();
//    	q.add(0);
//    	while(!q.isEmpty()) {
//    		int v = q.remove();
//    		for(int u : adj[v]) {
//    			if(color[u] == 0) {
//    				q.add(u);
//    				color[u] = - color[v];
//    			}
//    			else if(color[u] == color[v]) {
//    				return 0;
//    			}
//    		}
//    	}
//        return 1;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
//        for (int i = 0; i < n; i++) {
//            adj[i] = new ArrayList<Integer>();
//        }
//        for (int i = 0; i < m; i++) {
//            int x, y;
//            x = scanner.nextInt();
//            y = scanner.nextInt();
//            adj[x - 1].add(y - 1);
//            adj[y - 1].add(x - 1);
//        }
//        System.out.println(bipartite(adj));
//    }
//}


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
    	// TODO by LCC on 12/21/2019
    	// explored: record all parents
    	// color: record all colors (0, 1, -1) non, b, w
//    	boolean[] explored = new boolean[adj.length];
//    	boolean[] visited = new boolean[adj.length];
    	int[] color = new int[adj.length];
    	for(int i = 0; i < adj.length; ++i) {
//    		explored[i] = false;
//    		visited[i] = false; 
    		color[i] = 0; 
    	}
    	// disconnected graph - multiple components
    	for(int s = 0; s < adj.length; s++) {
    		if(color[s] != 0) {
    			continue;
    		}
			// queue to store all vertices
	    	Queue<Integer> queue = new LinkedList<>();
	    	queue.offer(s);
//	    	explored[s] = true;
			color[s] = 1;
			while(!queue.isEmpty()) {
				int u = queue.poll();
				for(int i = 0; i < adj[u].size(); ++i) {
					int v = adj[u].get(i);
					// if uncolored
					if(color[v] == 0) {
						queue.offer(v);
						color[v] = -1 * color[u];
					}
					// if wrongly colored
					else if (color[v] == color[u]) {
						return 0;
					}
					// if correctly colored
					else {
						continue;
					}
				}	
			}
		}
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

