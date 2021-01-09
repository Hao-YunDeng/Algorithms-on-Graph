package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
	//Haoyun: visited has to be defined out of the explore method
	static boolean[] visited;
    private static int reach(ArrayList<Integer>[] adj, int x, int y) { 	
        //write your code here
    	//Haoyun: initialize visited when you actually construct/use it  
    	//x is the vertex, and y is the other vertex(exit)   
    	//adj[x] is the neighbors of vertex x
    	
//    	if(visited[y] == true) {
//    		System.out.println("returned from the outer return 1");
//    		return 1;
//    	}
    	
    	visited[x] = true;
    	//System.out.println("I just visited " + (x+1));
    	for(int w : adj[x]) {
    		if(!visited[w]) {
    			reach(adj, w, y);
    			//System.out.println("I just visited " + w);  			
    	    	if(visited[y] == true) {    	    		
    	    		//System.out.println("returned from the inner return 1");
    	    		return 1;
    	    	}
    		}
    	 }
    	
//    	if(visited[y] == true) {
//    		System.out.println("returned from the second outer return 1");
//    		return 1;
//    	}
    	//System.out.println("returned 0");
        return 0;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        visited = new boolean[n];
        System.out.println(reach(adj, x, y));
    }
}

