package genetics;

import genetics.Cell;
import java.util.ArrayList;
import java.util.Random;

public class CellCreator {
    
    ArrayList<Cell> createFirstGen(int populationSize, int pathLength) {
        Random rand = new Random();
        ArrayList<Cell> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < pathLength; j++) {
                builder.append(rand.nextInt(4));
            }
            Cell cell = new Cell();
            cell.path = builder.toString();
            population.add(cell);
        }
        return population;
    }

}
