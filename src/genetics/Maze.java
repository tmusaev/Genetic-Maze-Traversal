package genetics;

public class Maze {
    
    //0 = free space, 1 = obstacle, 8 = exit, 5 = character
    int[][] maze = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 8, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
        {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1},
        {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 5, 1},
        {1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} 
    };
    int startRow, startCol, currRow, currCol, finalRow, finalCol;
    
    //Constructor
    public Maze() {
        initStartPos();
    }
    
    //Finds the character and initializes starting positions
    void initStartPos() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 5) {
                    startRow = currRow = i; 
                    startCol = currCol = j; 
                }
                if (maze[i][j] == 8) {
                    finalRow = i; finalCol = j;
                }
            }
        }   
    }
    
    //Traverse the maze with commands from the input path
    void traversePath(String path) {
        for(int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '0') { //left
                if (!isBlocked(currRow, currCol-1)) {
                    Swap(currRow, currCol, currRow, currCol-1);
                    currCol--;
                }
            }
            else if (c == '1') { //right
                if (!isBlocked(currRow, currCol+1)) {
                    Swap(currRow, currCol, currRow, currCol+1);
                    currCol++;
                }
            }
            else if (c == '2') { //up
                if (!isBlocked(currRow-1, currCol)) {
                    Swap(currRow, currCol, currRow-1, currCol);
                    currRow--;
                }
            }
            else if (c == '3') { //down
                if (!isBlocked(currRow+1, currCol)) {
                    Swap(currRow, currCol, currRow+1, currCol);
                    currRow++;
                }
            }
            if (currRow == finalRow && currCol == finalCol)
                return;
        }
    }
    
    //Returns true if element is obstacle, false otherwise
    boolean isBlocked(int i, int j) {
        if (maze[i][j] == 1)
            return true;
        else
            return false;   
    }
    
    //Swaps two elements in the maze
    void Swap(int i, int j, int x, int y) {
        int temp = maze[i][j];
        maze[i][j] = maze[x][y];
        maze[x][y] = temp;
    }
    
    //Prints the maze to the console
    void Print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    //Resets the maze back to start positions
    void Reset() {
        maze[currRow][currCol] = 0;
        maze[startRow][startCol] = 5;
        maze[finalRow][finalCol] = 8;
        currRow = startRow;
        currCol = startCol;
    }
    
    //Compute the fitness score of the current position
    //It's the distance away from the exit
    double computeFitness() {
        double rowDiff = Math.abs(currRow - finalRow);
        double colDiff = Math.abs(currCol - finalCol);
	return 1/(rowDiff + colDiff + 1);
    }

}
