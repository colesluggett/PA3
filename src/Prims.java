import java.util.ArrayList;

/* 
* Authors: Cole Sluggett, John Dickson, Zachariah Fahsi
* Date: 4/10/18
* Overview: Runs the Prim’s Algorithm to find the 
* minimum spanning tree on a weighted, undirected graph
* 
* Uses input1.txt to match the given example
*/

public class Prims {

	private int[][] am;
	private int size;
	private ArrayList<String> vertices = new ArrayList<>();
	private int totalWeight = 0;
	private String[] path;

	public Prims(String[] vertices, int[][] am) {
		this.am = am;
		for (int i = 0; i < vertices.length; i++)
			this.vertices.add(vertices[i]);
		size = this.vertices.size();
		this.path = findPath();
	}

	private String[] findPath() {
		ArrayList<String> visited = new ArrayList<>();
		String[] path = new String[vertices.size() - 1];
		visited.add(vertices.get(0));
		for (int i = 0; i < path.length; i++) {
			int min = Integer.MAX_VALUE;
			String to = null;
			String from = null;
			for (int j = 0; j < visited.size(); j++) {
				for (int k = 0; k < size; k++) {
					if (am[vertices.indexOf(visited.get(j))][k] < min && !visited.contains(vertices.get(k))) {
						min = am[vertices.indexOf(visited.get(j))][k];
						from = visited.get(j);
						to = vertices.get(k);
					}
				}
			}
			path[i] = from + to;
			totalWeight += min;
			visited.add(to);
		}
		return path;
	}
	
	public void getPath(){
		for (int i = 0; i< path.length; i++)
			System.out.println(path[i]);
		System.out.println("Total Weight: " + totalWeight);
	}

}
