import java.util.Arrays;
import java.util.ArrayList;

/**
 * Write a description of class Main11 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main11
{
    public static void main(String[] args)
    {
        int[] A = {1,4,3,5,2};
        bubbleSort(A);
        int[] B = {1,4,3,5,2};
        bubbleSortReverse(B);
        int[] C = {5,1,4,3,2};        
        selectionSort(C);
        int[] D = {1,4,3,5,2};
        int[] E = {5,1,4,3,2};
        System.out.println(Arrays.toString(mergeSortReverse(D)));
        System.out.println(Arrays.toString(mergeSortReverse(E)));
        int[] F = {1,2,3};
        System.out.println(Arrays.deepToString(powerSet(F).toArray()));
        int[] G = {1,2,3,4,5};
        System.out.println(subsetSum(G, 15));
        int[][] H = {{0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0},
                     {0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(travelingKnight(H, 7, 7));
        System.out.println(Arrays.deepToString(H));
    }
    
    // Take the array [1,4,3,5,2]
    // Show the resulting array after 1 element is bubbled through (an entire iteration, not just one switch)
    // [1, 3, 4, 2, 5]
    // Show the resulting array after 2 elements are bubbled through
    // [1, 3, 2, 4, 5]
    // Show the sorted array
    // [1, 2, 3, 4, 5]
    public static void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
    
    public static void bubbleSort(int[] A) {
        for (int i=0; i<A.length-1; i++) {
            for (int j=0; j<A.length - 1 - i; j++) {
                if (A[j] > A[j+1]) {
                    swap(A, j, j+1);
                }
            }
            System.out.println(Arrays.toString(A));
        }
    }
    
    // bubbleSortReverse
    // Inputs: int[] A
    // Output: int[]
    // This function should use bubbleSort to sort array A but instead of sorting from least to greatest, it should be sorted from greatest to least.
    public static int[] bubbleSortReverse(int[] A) {
        for (int i=0; i<A.length-1; i++) {
            for (int j=0; j<A.length - 1 - i; j++) {
                if (A[j] < A[j+1]) {
                    swap(A, j, j+1);
                }
            }
            System.out.println(Arrays.toString(A));
        }
        return A;
    }
    
    // selectionSort
    // Inputs: int[] A
    // Output: int[]
    // This function should use selectionSort to sort array A.
    // Selection sort is uniquely identified by its few switches (whereas bubble Sort is defined by its many switches). 
    // Take the list [5,1,4,3,2], the way selection sort would work is that it would parse through the list and identify the minimum element in the array (in this case 1) and switch it with the first element in the array. 
    // The list now looks like [1,5,4,3,2]
    // Next, we will identify the second smallest element (essentially the smallest element past index 0) and switch it with index 1
    // The list now looks like [1,2,4,3,5]
    // Continue until the list is complete. 
    public static int[] selectionSort(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(A, i, min);
            }
            System.out.println(Arrays.toString(A));
        }
        return A;
    }
    
    // mergeSortReverse
    // Inputs: int[] A
    // Output: int[]
    // This function should use mergesort to sort the int[] A but from greatest to least instead of least to greatest. 
    public static int[] mergeReverse(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int indexA = 0, indexB = 0, indexC = 0;
        
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA] < B[indexB]) {
                C[indexC] = B[indexB];
                indexC += 1;
                indexB += 1;
            } else {
                C[indexC] = A[indexA];
                indexC += 1;
                indexA += 1;                
            }
        }
        
        if (indexA < A.length) {
            for (int i = indexA; i < A.length; i++) {
                C[indexC] = A[i];
                indexC += 1;
            }
        } else { // B has remaining items
            for (int i = indexB; i < B.length; i++) {
                C[indexC] = B[i];
                indexC += 1;
            }
        }
        return C;
    }
    
    public static int[] mergeSortReverse(int[] A) {
        if (A.length <= 1) {
            return A;    
        }
        int mid = A.length / 2;
        int[] left  = Arrays.copyOfRange(A, 0, mid);
        int[] right = Arrays.copyOfRange(A, mid, A.length);
        
        int[] leftSorted  = mergeSortReverse(left);
        int[] rightSorted = mergeSortReverse(right);
        int[] merged = mergeReverse(leftSorted, rightSorted);
        return merged;
    }
    
    // powerSet
    // Inputs: int[] A
    // Output: ArrayList<ArrayList<Integer>>
    // Trust me on the ArrayList (it will make your life 1000 times easier)
    // Compute all possible subsets of an int list (order does not matter)
    // Ex. [1,2,3] ⇒ [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]
    public static ArrayList<int[]> powerSetHelper(int[] A, int index, int[] current) {
        ArrayList<int[]> output = new ArrayList();
        
        if (index >= A.length) {
            output.add(current);
            return output;
        }
        
        ArrayList<int[]> alpha = powerSetHelper(A, index + 1, current);
        current = Arrays.copyOf(current, current.length + 1);
        current[current.length-1] = A[index];
        ArrayList<int[]> beta  = powerSetHelper(A, index + 1, current);
        
        // System.out.println(Arrays.deepToString(alpha.toArray()));
        // System.out.println(Arrays.deepToString(beta.toArray()));
        
        output.addAll(alpha);
        output.addAll(beta);
        return output;
    }
    
    public static ArrayList<int[]> powerSet(int[] A) {
        return powerSetHelper(A, 0, new int[]{});
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // I cannot make the following way work using ArrayList<ArrayList<Integer>> it has to do with how I am adding things into the ArrayList
    //
    // public static ArrayList<ArrayList<Integer>> powerSetHelper(int[] A, int index, ArrayList<Integer> current) {
        // ArrayList<ArrayList<Integer>> output = new ArrayList();
        
        // if (index >= A.length) {
            // output.add(current);
            // return output;
        // }
        
        // ArrayList<ArrayList<Integer>> alpha = powerSetHelper(A, index + 1, current);
        // current.add(A[index]);
        // ArrayList<ArrayList<Integer>> beta  = powerSetHelper(A, index + 1, current);
        
        // System.out.println(Arrays.deepToString(alpha.toArray()));
        // System.out.println(Arrays.deepToString(beta.toArray()));
        
        // output.addAll(alpha);
        // output.addAll(beta);
        // return output;
    // }
    
    // public static ArrayList<ArrayList<Integer>> powerSet(int[] A) {
        // return powerSetHelper(A, 0, new ArrayList<Integer>());
    // }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // subsetSum
    // Inputs: int[] A, target
    // Output: boolean
    // Compute whether there exist some subset of integers from A that sum to target
    public static boolean subsetSumHelper(int[] current, int index, int[] A, int target) {
        if (index >= A.length) {
            return Arrays.stream(current).sum() == target;
        }
        
        boolean alpha = subsetSumHelper(current, index+1, A, target);
        
        current = Arrays.copyOf(current, current.length + 1);
        current[current.length-1] = A[index];
        
        boolean beta  = subsetSumHelper(current, index+1, A, target);
        
        return alpha || beta;
    }
    
    public static boolean subsetSum(int[] A, int target) {
        return subsetSumHelper(new int[]{}, 0, A, target);
    }
    
    // travelingKnight
    // Inputs: int[][] A, int row, int col
    // Output: boolean
    // Given a rectangular, two dimensional board A, and a starting position A[row][col], return whether or not a chess knight can reach all of the squares in A. 
    // You can assume all integers in A start at 0.
    // A chess knight moves in an L shape, 2 squares in one direction and one square in a direction perpendicular to the first direction
    // You cannot travel off the board
    /////////////////////////////////////////////////////////////////////////////////////
    // 8 valide knight moves
    public static int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 }; // 8 valid x moves
    public static int[] yMoves = { 1, 2, 2, 1, -1, -2, -2, -1 }; // 8 valid y moves
    
    // helper function to determine if A[row][col] is valid
    public static boolean isValidMove(int[][] A, int row, int col) {
        return (row >=0 && row < A.length && col >=0 && col < A[0].length && A[row][col] == 0);
    }

    // helper function with move count 
    public static boolean knightsTour(int[][] A, int nextMove, int row, int col) {
        if (nextMove > A.length * A[0].length) {
            return true; // all squares filled if nextMove is larger
        } 
        
        for (int i = 0; i < 8; i++) { // 8 possible moves, we will check one by one
            int newRow = row + xMoves[i];
            int newCol = col + yMoves[i];
            if (isValidMove(A, newRow, newCol)) {
                A[newRow][newCol] = nextMove;
                if (knightsTour(A, nextMove+1, newRow, newCol)) {
                    return true;
                } else {
                    A[newRow][newCol] = 0; // backtracking here
                }
            }
        }
        return false;
    }
    
    public static boolean travelingKnight(int[][] A, int row, int col) {
        A[row][col] = 1; // move 1 is the starting position
        return knightsTour(A, 2, row, col);
    }
    
    // famous knight tour problem.. watch this video https://www.youtube.com/watch?v=pwlxQeHchFQ
    // sample code from https://github.com/eMahtab/knights-tour
    // watch video here https://www.youtube.com/watch?v=D8KFwjohDNg
    /*
    class KnightTour {
	int[] xMoves = { 2, 1, -1, -2, -2, -1, 1, 2 };
	int[] yMoves = { 1, 2, 2, 1, -1, -2, -2, -1 };
	
	public void solveKnightTourProblem(int chessboardSize) {	
		int[][] visited = new int[chessboardSize][chessboardSize];
		visited[0][0] = 1;
		// start knight's tour from top left corner square (0, 0)
		if( solveProblem(visited, 2, 0, 0)) {
			printSolution(visited);
		} else {
			System.out.println("No feasible solution found...");
		}			
	}

	public boolean solveProblem(int[][] visited, int moveCount, int x, int y) {
		// Base Case : We were able to move to each square exactly once
		if (moveCount > visited.length * visited.length) {
			return true;
		}

		for (int i = 0; i < xMoves.length; ++i) {
			int nextX = x + xMoves[i];
			int nextY = y + yMoves[i];

			// check if new position is a valid and not visited yet
			if ( isValidMove(visited, nextX, nextY) && visited[nextX][nextY] == 0) {
				visited[nextX][nextY] = moveCount;
				if ( solveProblem(visited, moveCount + 1, nextX, nextY) ) {
					return true; 
				}
				visited[nextX][nextY] = 0;
			}
		}
	   return false;
	}

	public boolean isValidMove(int[][] visited, int x, int y) {
		if (x < 0 || x >= visited.length || y < 0 || y >= visited.length) {
			return false;
		} else {
			return true;	
		}
	}
	
	public void printSolution(int[][] visited) {
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}
    }
    */
}
