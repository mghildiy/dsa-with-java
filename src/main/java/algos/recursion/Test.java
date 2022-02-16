package algos.recursion;

import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // reverse a string
        String input = "The simple engineer";
        System.out.println("Input:"+ input);
        System.out.println("Reversed:"+ reverseString(input));

        // palindrome check
        System.out.println(palindromeCheck("racecar"));

        // decimal to binary
        List<Integer> binary = new ArrayList<>();
        decimalToBinary(233, binary);
        binary.forEach(i -> System.out.println(i));

        // unique paths in a grid
        Cell[][] grid = createGrid(2,4);
        System.out.println("Number of unique paths:"+ numUniquePaths(grid));
    }

    public static int pathCounter = 0;
    public static int numUniquePaths(Cell[][] grid) {
        if(grid.length == 0)
            return 0;

        pathCounter += 1;
        //return traverseGridRecursively(grid[0][0], 1, grid);
        traverseGridRecursively(grid[0][0], grid);
        return pathCounter;
    }
    //private static int traverseGridRecursively(Cell cell, int pathsSoFar, Cell[][] grid) {
    private static void traverseGridRecursively(Cell cell, Cell[][] grid) {
        /*if(cell.down == -1 && cell.right == -1)
            return pathsSoFar;

        if(cell.down != -1 && cell.right != -1) {
            pathsSoFar =  traverseGridRecursively(grid[cell.right][cell.m], pathsSoFar + 1, grid);
            pathsSoFar =  traverseGridRecursively(grid[cell.down][cell.n], pathsSoFar + 1, grid);
        }
        if(cell.down == -1) {
            pathsSoFar =  traverseGridRecursively(grid[cell.right][cell.m], pathsSoFar, grid);
        } else {
            pathsSoFar =  traverseGridRecursively(grid[cell.n][cell.down], pathsSoFar, grid);
        }*/

        Tuple2<Cell, Cell> neighbours = findNeighbours(grid, cell.n, cell.m);
        if(neighbours._1 == null && neighbours._2 == null)
            //return pathsSoFar;
            return;

        if(neighbours._1 != null && neighbours._2 != null) {
            //pathsSoFar =  traverseGridRecursively(neighbours._2, pathsSoFar + 1, grid);
            //pathsSoFar =  traverseGridRecursively(neighbours._1, pathsSoFar, grid);
            pathCounter += 1;
            traverseGridRecursively(neighbours._2, grid);
            traverseGridRecursively(neighbours._1, grid);
        }
        else {
            if(neighbours._1 != null) {
                //pathsSoFar =  traverseGridRecursively(neighbours._1, pathsSoFar, grid);
                traverseGridRecursively(neighbours._1, grid);
            }
            if(neighbours._2 != null) {
                //pathsSoFar =  traverseGridRecursively(neighbours._2, pathsSoFar, grid);
                traverseGridRecursively(neighbours._2, grid);
            }
        }
        //return pathsSoFar;
    }

    // down, right
    private static Tuple2<Cell, Cell> findNeighbours(Cell[][] grid, int n, int m) {
        int numRows = grid.length;
        int numColumns = grid[0].length;
        if(n >= numRows - 1 && m >= numColumns - 1) {
            return new Tuple2<Cell, Cell> (null, null);
        }
        if(n == numRows - 1) {
            return new Tuple2<Cell, Cell> (null, grid[n][m+1]);
        }
        if(m == numColumns - 1) {
            return new Tuple2<Cell, Cell> (grid[n+1][m], null);
        }
        return new Tuple2<Cell, Cell> (grid[n+1][m], grid[n][m+1]);
    }

    public static String  reverseString(String input) {
        if(input.length() == 1)
            return input;
        return input.charAt(input.length() - 1) + reverseString(input.substring(0, input.length()-1));
    }

    public static boolean palindromeCheck(String input) {
        if(input.length() <= 1)
            return true;

        if(input.charAt(0) == input.charAt(input.length() - 1)) {
            return palindromeCheck(input.substring(1, input.length() - 1));
        }

        return false;
    }

    public static void decimalToBinary(int input, List<Integer> soFar) {
        soFar.add(input % 2);
        if(input / 2 == 0) {
            return;
        }
        decimalToBinary(input / 2, soFar);
    }

    public static Cell[][] createGrid(int n, int m) {
        Cell[][] grid = new Cell[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                grid[i][j] = (new Cell(i,j));
                /*if(i < n -1) {
                    grid[i][j].right = i + 1;
                }
                if(j < m - 1) {
                    grid[i][j].down = j + 1;
                }*/
            }
        }

        return grid;
    }

    static class Cell {
        int n = -1;
        int m = -1;
        //int right = -1;
        //int down = -1;
        Cell(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }
}
