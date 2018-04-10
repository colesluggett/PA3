/* 
* Authors: Cole Sluggett, John Dickson, Zachariah Fahsi
* Date: 4/10/18
* Overview: Runs the Floyd-Warshall’s Algorithm to find the 
* length of the shortest path between all pair of vertices 
* in a weighted connected simple graph
* 
* Uses input1.txt to match the given example
*/
import java.io.UnsupportedEncodingException;
//BEGIN  
//    for each vertex v   
//        d[v][v] ← 0 
//    for each edge (u,v) 
//        d[u][v] ← w(u,v) 
//    for k from 1 to |V|   
//        for i from 1 to |V|
//            for j from 1 to |V|     
//                if d[i][j] > d[i][k] + d[k][j] 
//                    d[i][j] ← d[i][k] + d[k][j]    
//                end if 
//END
public class Floyd {
    private int[][] dist; // Initilize dist, "d" in pseudocode
    String infinitySymbol = null; //Initilize infinity symbol
    //Creates char array, so input file can have 26 rows and columns
    char[] header = "abcdefghijklmnopqrstuvwxyz".toCharArray();  

    public Floyd(int[][] amIn) { //Constructor
        dist = amIn;
        run();
    }
    
    public void run() {
        try { //Get infinity symbol
            infinitySymbol = new String(String.valueOf(Character.toString('\u221E')).getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            infinitySymbol = "?"; //If can't show infinity symbol, show "?"
        }
        for(int i=0;i<dist.length;i++) {
            for(int j=0;j<dist[i].length;j++) {
                if(dist[i][j] == Integer.MAX_VALUE) //Checks if value is max_value
                    dist[i][j] = 99999; //Sets max_value to 9999 to avoid overflow
            }
        }
        for(int i=0;i<dist.length;i++) {
            dist[i][i] = 0; //Sets the diagnal to 0, as the length to itself is 0
        } 
        for(int k=0;k<dist.length;k++) { 
            for(int i=0;i<dist.length;i++) {
                for(int j=0;j<dist.length;j++) {
                    //Compares the current distance to the going through other points
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {  
                        dist[i][j] = dist[i][k] + dist[k][j]; //Sets new shorter distance
                        print(); //Prints table
                    }
                }
            }
        }
    }
    
    public void print() {
        System.out.printf("%-3s"," "); //Prints empty space at header start
        for(int i=0;i<dist.length;i++) {
            System.out.printf("%-4s",header[i]); //Prints header
        }
        System.out.println();
        for(int i=0;i<dist.length;i++) {
            System.out.printf("%-3s",header[i]); //Print row header
            for(int j=0;j<dist[i].length;j++) {  
                if(dist[i][j] == 99999)
                    System.out.printf("%-4s",infinitySymbol); //Prints infinity symbol
                else
                    System.out.printf("%-4d", dist[i][j]); //Prints number in array
            }
            System.out.println();
        }
        System.out.println();
    }
}
