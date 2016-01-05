package genetics;

import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;

public class CellBreeder {
    final static double mutationRate = 0.1;
    
    Cell breedCells(Cell mom, Cell dad) {
        int cutoff = generateCutoff(mom.path.length());
        StringBuilder offspring = new StringBuilder();
        offspring.append(mom.path.substring(0, cutoff));
        offspring.append(dad.path.substring(cutoff, dad.path.length()));
        Random rand = new Random();
        //Mutate
        for(int i = 0; i < offspring.length(); i++)
        {
            if(rand.nextDouble() <= mutationRate)
                offspring.setCharAt(i, (char)(rand.nextInt(4)+48));
        }
        Cell cell = new Cell();
        cell.path = offspring.toString();
        return cell;
    }
    
    //Cutoff point determines the the ratio of how many characters
    //the new offspring will inherit from parents. It is a normal
    //distribution centered at half the path length because most of the
    //time the offspring will get about an equal amount from each parent.
    //Normal distribution extends infinitely so I just use the tails as 
    //the edge values.
    int generateCutoff(int pathLength) {
        double mean = (pathLength-1)/2;
        double std = (pathLength-1)/6;
        NormalDistribution normal = new NormalDistribution(mean, std);
        double sample = normal.sample();
        if (sample < 0)
            return 0;
        else if (sample > pathLength-1)
            return pathLength-1;
        else
            return (int)Math.round(sample); 
    }

}
