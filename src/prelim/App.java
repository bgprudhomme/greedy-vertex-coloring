package prelim;

import java.util.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int l = 100;
		double avgColors;
		
		double[] probs = {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.1, 0.11, 0.12, 0.13, 0.14, 0.15, 0.16, 0.17, 0.18, 0.19, 0.2};
		
		for(double p : probs) {
			avgColors = 0.0;
			for(int s=0; s<l; s++) {
				avgColors += (double) color_ff(randBipartiteGraph(500, 500, p, s)) / l;
			}
			System.out.println("p = " + p + ", n = 1000: " + avgColors + " avg colors");
		}
		
//		Matrix g = randBipartiteGraph(5, 5, 0.6, 0);
//		
//		System.out.println(g);
//		
//		System.out.println(color_ffa(g));
		
	}
	
	public static Matrix randGraph(int n, double p, int seed) {
		Matrix graph = new Matrix(n);
		Random rand = new Random(seed);
		
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				if(rand.nextDouble() < p) {
					graph.addEdge(i, j);
				}
			}
		}
		
		return graph;
	}
	
	public static Matrix randBipartiteGraph(int ka, int kb, double p, int seed) {
		Matrix graph = new Matrix(ka+kb);
		Random rand = new Random(seed);
		
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
		
//		System.out.println(setA);
//		System.out.println(setB);
		
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
	
	// Fix to consider vertices in alphabetical order
	public static int color_ff(Matrix graph) {
		int[] result = new int[graph.size()];
		int numColors = 0;
		
		int nextColor;
		HashSet<Integer> neighborColors;
				
		for(int i=0; i<graph.size(); i++) {
			
			neighborColors = new HashSet<Integer>();
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors: " + graph.neighbors(degreeSortedNodes.get(i)));
			for(int j : graph.neighbors(i)) {
				neighborColors.add(result[j]);
			}
			
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors' colors: " + neighborColors);
			
			nextColor = 1;
			while(neighborColors.contains(nextColor))
				nextColor++;
			
			result[i] = nextColor;
			
//			System.out.println(degreeSortedNodes.get(i) + "'s color: " + nextColor);
			
			if(numColors < nextColor)
				numColors = nextColor;
			
//			System.out.println("Coloring array: " + Arrays.toString(result));
		}
		
		return numColors;
	}
	
	public static int color_ffa(Matrix graph) {
		int[] result = new int[graph.size()];
		int numColors = 0;
		
		int nextColor;
		HashSet<Integer> neighborColors;
		ArrayList<Integer> degreeSortedNodes = graph.degreeSortedNodes();
		
//		System.out.println(degreeSortedNodes);
		
		for(int i=0; i<graph.size(); i++) {
			
			neighborColors = new HashSet<Integer>();
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors: " + graph.neighbors(degreeSortedNodes.get(i)));
			for(int j : graph.neighbors(degreeSortedNodes.get(i))) {
				neighborColors.add(result[j]);
			}
			
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors' colors: " + neighborColors);
			
			nextColor = 1;
			while(neighborColors.contains(nextColor))
				nextColor++;
			
			result[degreeSortedNodes.get(i)] = nextColor;
			
//			System.out.println(degreeSortedNodes.get(i) + "'s color: " + nextColor);
			
			if(numColors < nextColor)
				numColors = nextColor;
			
//			System.out.println("Coloring array: " + Arrays.toString(result));
		}
		
		return numColors;
	}
	
	public static int color_ffd(Matrix graph) {
		int[] result = new int[graph.size()];
		int numColors = 0;
		
		int nextColor;
		HashSet<Integer> neighborColors;
		ArrayList<Integer> degreeSortedNodes = graph.degreeSortedNodes();
		
//		System.out.println(degreeSortedNodes);
		
		for(int i=graph.size()-1; i>=0; i--) {
			
			neighborColors = new HashSet<Integer>();
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors: " + graph.neighbors(degreeSortedNodes.get(i)));
			for(int j : graph.neighbors(degreeSortedNodes.get(i))) {
				neighborColors.add(result[j]);
			}
			
//			System.out.println(degreeSortedNodes.get(i) + "'s neighbors' colors: " + neighborColors);
			
			nextColor = 1;
			while(neighborColors.contains(nextColor))
				nextColor++;
			
			result[degreeSortedNodes.get(i)] = nextColor;
			
//			System.out.println(degreeSortedNodes.get(i) + "'s color: " + nextColor);
			
			if(numColors < nextColor)
				numColors = nextColor;
			
//			System.out.println("Coloring array: " + Arrays.toString(result));
		}
		
		return numColors;
	}
	
}
