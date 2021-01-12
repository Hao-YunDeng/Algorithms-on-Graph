package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
	static boolean[] visited;
	
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
        for(int i = 0; i < adj.length; i++) {
        	if(!visited[i]) {
        		explore(i, adj);
        		result++;
        	}
        }
        return result;
    }
    private static void explore(int v, ArrayList<Integer>[] adj) {
    	visited[v] = true;
    	for(int w : adj[v]) {
    		if(!visited[w]) {
    			explore(w, adj);
    		}
    	}
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
        visited = new boolean[n];
        System.out.println(numberOfComponents(adj));
    }
}

