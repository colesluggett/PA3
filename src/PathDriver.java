import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PathDriver {

	public static void main(String[] args) {

		Path p = Paths.get("input/input.txt");
		Charset charset = Charset.forName("US-ASCII");

		ArrayList<String[]> lines = new ArrayList<String[]>();
		int a = 0;
		{
			try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
				String line = "";

				while ((line = reader.readLine()) != null) {
					String[] arr = line.split(",");
					lines.add(arr);
					a++;
				}

			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}

		String[][] fullArr = new String[a][a - 1];
		for (int i = 0; i < a; i++)
			fullArr[i] = lines.get(i);
		String[] vertices = fullArr[0];
		int[][] adjMat = new int[fullArr.length - 1][fullArr[0].length];
		for (int r = 1; r < fullArr.length; r++) {
			for (int c = 0; c < fullArr[r].length; c++) {
				if ((int) fullArr[r][c].charAt(0) < 48 || (int) fullArr[r][c].charAt(0) > 57)
					adjMat[r - 1][c] = Integer.MAX_VALUE;
				else
					adjMat[r - 1][c] = Integer.parseInt(fullArr[r][c]);
			}
		}

		// adjMat is integer values of adjacency matrix, and vertices is string
		// names of vertices

		// to view the matrix
		for (int r = 0; r < adjMat.length; r++) {
			//System.out.print("\n" + vertices[r] + ": ");
			for (int c = 0; c < adjMat[r].length; c++) {
				//System.out.print(adjMat[r][c] + ", ");
			}
		}
		
		Prims prim = new Prims(vertices, adjMat);
		System.out.println();
		prim.getPath();
		Kruskals kruskals = new Kruskals(vertices, adjMat);
		System.out.println();
		Floyd floyd = new Floyd(adjMat);

	}

}
