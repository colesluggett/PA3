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
    private int[][] dist;
    String infinitySymbol = null;
    char[] header = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Floyd(int[][] amIn) {
        dist = amIn;
        run();
    }
    
    public void run() {
        try {
            infinitySymbol = new String(String.valueOf(Character.toString('\u221E')).getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            infinitySymbol = "?";
        }
        for(int i=0;i<dist.length;i++) {
            for(int j=0;j<dist[i].length;j++) {
                if(dist[i][j] == Integer.MAX_VALUE)
                    dist[i][j] = 99999;
            }
        }
        for(int i=0;i<dist.length;i++) {
            dist[i][i] = 0; 
        } 
        for(int k=0;k<dist.length;k++) {
            for(int i=0;i<dist.length;i++) {
                for(int j=0;j<dist.length;j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        print();
                    }
                }
            }
        }
    }
    
    public void print() {
        System.out.printf("%-3s"," ");
        for(int i=0;i<dist.length;i++) {
            System.out.printf("%-4s",header[i]);
        }
        System.out.println();
        for(int i=0;i<dist.length;i++) {
            System.out.printf("%-3s",header[i]);
            for(int j=0;j<dist[i].length;j++) {  
                if(dist[i][j] == 99999)
                    System.out.printf("%-4s",infinitySymbol);
                else
                    System.out.printf("%-4d", dist[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
