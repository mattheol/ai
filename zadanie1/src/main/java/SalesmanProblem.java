import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SalesmanProblem {
    public int sizeOfPopulation;
    public Individual[] population;
    public List<Edge> edges;

    public SalesmanProblem(List<Edge> edges){
        this.edges = edges;
    }

    public Individual generateIndividual(){
        Edge [] edgesInd = new Edge[edges.size()];
        Individual ind =new Individual(edgesInd.length);
        Edge edge = edges.remove(0);
        Collections.shuffle(edges);
        edges.add(0,edge);
        edges.toArray(edgesInd);
        ind.genotype = edgesInd;
        return ind;
    }

}
