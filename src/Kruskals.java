/*
 * Authors: Cole Sluggett, John Dickson, Zachariah Fahsi
 * Date: 4/10/18
 * Overview: Runs the Kruskals’s Algorithm tm to find
 * the minimum spanning tree for a weighted graph
 *
 * Uses input1.txt to match the given example
 */

public class Kruskals{
    private Vert[] arr;     //array for vertices
    private int[][] mat;    //double array for matrix

    public Kruskals(String[] v, int[][] array) {
        this.buildArray(v);
        this.buildMatrix(array);
        this.kruskal();
    }

    public void buildArray(String[] arr) {
        this.arr = new Vert[arr.length];                //creates new Vert

        for (int i = 0; i < arr.length; ++i) {
            this.arr[i] = new Vert(arr[i].charAt(0));   //sets all characters to array
        }

    }

    public void buildMatrix(int[][] arr) {
        this.mat = new int[arr.length][arr.length];     //creates new matrix

        for(int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr.length; ++j) {
                if (arr[i][j] == 0) {
                    this.mat[i][j] = Integer.MAX_VALUE; //if there is no value, set it to max integer
                } else {
                    this.mat[i][j] = arr[i][j];         //else set it to its current integer
                }
            }
        }
    }

    public void kruskal() {
        System.out.println("Kruskals:");
        int[][] checkArray = new int[this.mat.length][this.mat.length];     //array to check paths

        int s;  //start destination
        for(int i = 0; i < this.mat.length; ++i) {
            for(s = 0; s < this.mat.length; ++s) {
                checkArray[i][s] = 0;       //sets all values of check array to 0
            }
        }

        s = -1;
        int f = -1;

        for(int t = 0; t < this.arr.length - 1; t++) {
            int min = Integer.MAX_VALUE;    //sets min to max value

            for(int i = 0; i < this.mat.length; ++i) {
                for(int j = 0; j < this.mat.length; ++j) {
                    if (this.mat[i][j] < min && (checkArray[i][j] != 1 || checkArray[j][i] != 1)) { //checks to see if smaller and if it has been used
                        min = this.mat[i][j];   //sets min to vert
                        s = i;                  //sets start to i
                        f = j;                  //sets finish to j
                    }
                }
            }

            checkArray[s][f] = 1;   //puts path in checked array
            checkArray[f][s] = 1;
            for(int i = 0; i < this.mat.length; ++i) {
                if(checkArray[s][i] == 1){
                    checkArray[f][i] = 1;   //checks if more paths have been created
                    checkArray[i][f] = 1;   //
                }
                if(checkArray[f][i] == 1){
                    checkArray[s][i] = 1;   //
                    checkArray[i][s] = 1;   //
                }
            }
            System.out.print(this.arr[s].vertex + "" + this.arr[f].vertex + " ");
        }
    }
}

class Vert {
    char vertex;

    Vert(char v) {
        this.vertex = v;    //assigns the vertexes
    }
}
