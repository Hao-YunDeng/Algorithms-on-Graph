package week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
	static boolean[] visited;
	static boolean[] inStack;
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	for(int i = 0; i < adj.length; i++) {
    		if(!visited[i] && explore(adj, i)) {
    			return 1;
    		}
    	}
        return 0;
    }
    private static boolean explore(ArrayList<Integer>[] adj, int v) {
    	visited[v] = true;
    	inStack[v] = true;
    	for(int w : adj[v]) {
    		if(inStack[w]) {
    			return true;
    		}
    		if(!visited[w]) {
    			if(explore(adj, w)) return true;  
    		}
    	}
    	inStack[v] = false;
    	return false;
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
        }
        visited = new boolean[n];
        inStack = new boolean[n];
        System.out.println(acyclic(adj));
    }
}

