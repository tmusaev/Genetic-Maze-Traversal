package genetics;

public class Cell implements Comparable{
    String path;
    double fitness;
    //int age;
    
    @Override
    public int compareTo(Object o) {
        if (fitness > ((Cell)o).fitness)
            return -1;
        else if (fitness < ((Cell)o).fitness)
            return 1;
        else
            return 0;
    }
    

}
