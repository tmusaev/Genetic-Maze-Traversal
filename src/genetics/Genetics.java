package genetics;

import java.util.ArrayList;
import java.util.Collections;

public class Genetics {
    static final int populationSize = 100;
    static final int pathLength = 30;
    static final int numOfGenerations = 50;
    
    public static void main(String[] args) {
        Maze maze = new Maze();
        CellCreator cellCreator = new CellCreator();
        CellBreeder cellBreeder = new CellBreeder();
        ArrayList<Cell> population = 
                cellCreator.createFirstGen(populationSize, pathLength);
        
        for (Cell c : population) {
            maze.traversePath(c.path);
            c.fitness = maze.computeFitness();
            maze.Reset();
        }
        Collections.sort(population);
        for (int i = 1; i <= populationSize/4; i++)
            population.remove(populationSize-i);
        
        for (int j = 0; j < numOfGenerations; j++) {
            for (int i = 0; i < populationSize/2; i+=2) {
                Cell offspring = cellBreeder.breedCells(population.get(i), population.get(i+1));
                maze.traversePath(offspring.path);
                offspring.fitness = maze.computeFitness();
                maze.Reset();
                population.add(offspring);
            }
            Collections.sort(population);
        }
            
        for (Cell c : population) {
          System.out.println(c.fitness+"  "+c.path);
        }
        
    }

}
