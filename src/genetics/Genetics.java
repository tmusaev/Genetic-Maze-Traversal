package genetics;

import java.util.ArrayList;

public class Genetics {
    static final int populationSize = 100;
    static final int pathLength = 30;
    
    public static void main(String[] args) {
        Maze maze = new Maze();
        CellCreator cellCreator = new CellCreator();
        ArrayList<Cell> population = 
                cellCreator.createFirstGen(populationSize, pathLength);
        
        for(Cell c : population) {
            maze.traversePath(c.path);
            c.fitness = maze.computeFitness();
            maze.Reset();
        }
        
        for(Cell c : population) {
            System.out.println(c.fitness);
        }
    }

}
