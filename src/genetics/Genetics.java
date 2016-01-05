package genetics;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Genetics {
    public static final int populationSize = 100;
    static final int pathLength = 30;
    
    public static void main(String[] args) {
        Maze maze = new Maze();
        CellCreator cellCreator = new CellCreator();
        CellBreeder cellBreeder = new CellBreeder();
        ArrayList<Cell> population = 
                cellCreator.createFirstGen(populationSize, pathLength);
        
        for(Cell c : population) {
            maze.traversePath(c.path);
            c.fitness = maze.computeFitness();
            maze.Reset();
        }
        Collections.sort(population);
        
        //for(Cell c : population) {
        //  System.out.println(c.fitness);
        //}
        
        
        
        
       
    }

}
