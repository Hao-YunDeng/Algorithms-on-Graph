package week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	//Haoyun: We first make a G^R
    	ArrayList<Integer>[] revAdj = new ArrayList[adj.length];
    	for(int i = 0; i < adj.length; i++) {
    		revAdj[i] = new ArrayList<Integer>();
    	}
    	for(int i = 0; i < adj.length; i++) {
    		for(int w : adj[i]) {
    			revAdj[w].add(i);
    		}
    	}
    	//Haoyun: Now we make the order
        ArrayList<Integer> order = new ArrayList<Integer>();
        dfs(revAdj, order);
        //The largest postOrder of G^R is the source of G^R, the sink of G!!!
        //Reverse the post so the first of G^R is the source of G^R, sink of G!!!
        Collections.reverse(order);
        
        //Haoyun: now visited to count
        int count = 0;
        boolean visited[] = new boolean[revAdj.length]; //Just for exploring again to count
        
        for(int v : order) {
        	if(!visited[v]) {
        		explore(adj, visited, v);
        		count++;
        	}
        }
        return count;
    }
 
    private static void explore(ArrayList<Integer>[] adj, boolean[] visited, int v) {
    	visited[v] = true;
    	for(int w : adj[v]) {
    		if(!visited[w]) {
    			explore(adj, visited, w);
    		}
    	}
    }
    
    private static void dfs(ArrayList<Integer>[] adj, ArrayList<Integer> order) {
    	//write your code here
    	boolean[] visited = new boolean[adj.length];
    	for(int i = 0; i < adj.length; i++) {
    		if(!visited[i]) {
    			explore(adj, visited, order, i);
    		}
    	}
    }
    
    private static void explore(ArrayList<Integer>[] adj, boolean[] visited, ArrayList<Integer> order, int v) {
    	//write your code here
    	visited[v] = true;
    	for(int w : adj[v]) {
    		if(!visited[w]) {
    			explore(adj, visited, order, w);
    		}
    	}
    	order.add(v);
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

