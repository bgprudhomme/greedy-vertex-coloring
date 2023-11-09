package prelim;

import java.util.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Matrix m = randGraph(6, 0.2);
		System.out.println(m);
		
		int[] coloring = {0, 0, 0, 1, 1, 1};
		
		System.out.println(legalColoring(m, coloring));
		
	}
	
	public static Matrix randGraph(int n, double p) {
		Matrix graph = new Matrix(n);
		Random rand = new Random();
		
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if(rand.nextFloat() < p) {
					graph.addEdge(i, j);
				}
			}
		}
		
		return graph;
	}
	
	public static Matrix randBipartiteGraph(int ka, int kb, double p) {
		Matrix graph = new Matrix(ka+kb);
		Random rand = new Random();
		
		HashSet<Integer> setA = new HashSet<Integer>();
		for(int i=0; i<ka+kb; i++)
			setA.add(i);
		
		int nextB;
		HashSet<Integer> setB = new HashSet<Integer>();
		while(setB.size()<kb) {
			nextB = rand.nextInt(ka+kb);
			if(setA.remove(nextB))
				setB.add(nextB);
		}
		
		System.out.println("A:" + setA);
		System.out.println("B:" + setB);
		
		for(int a : setA) {
			for(int b : setB) {
				if(rand.nextDouble() < p)
					graph.addEdge(a, b);
			}
		}
		
		return graph;
	}
	
	public static boolean legalColoring(Matrix graph, int[] coloring) {
		for(int i=0; i<graph.size()-1; i++) {
			for(int j=i+1; j<graph.size(); j++) {
				if(graph.get(i, j) == 1 && coloring[i] == coloring[j])
					return false;
			}
		}
		return true;
	}
	
//	public static int[] optimalColoring(Matrix graph) {
//		
//	}

}
