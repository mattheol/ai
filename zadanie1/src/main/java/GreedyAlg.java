import java.util.ArrayList;
import java.util.List;

public class GreedyAlg {

    public Individual solve(Edge[] edges){
        List<Edge> toFind = new ArrayList<Edge>();
        Edge[] result = new Edge[edges.length];
        int bestIndex=0;
        double distance=0;
        double tempDistance;
        for(int k=0;k<edges.length;k++){
            toFind.add(edges[k]);
        }
        result[0] = edges[0];
        toFind.remove(0);
        int currentEdgeIndex;
        int bestJ=0;
        for(int i=1; i<edges.length;i++){
            currentEdgeIndex = i-1;
            for(int j=0; j<toFind.size();j++){
                if(j == 0){
                    bestIndex =toFind.get(0).id - 1;
                    distance = Math.sqrt(Math.pow((double)result[currentEdgeIndex].x - (double)edges[bestIndex].x, 2) +
                            Math.pow((double)result[currentEdgeIndex].y - (double)edges[bestIndex].y, 2));
                    bestJ = 0;
                }else{
                    tempDistance = Math.sqrt(Math.pow((double)result[currentEdgeIndex].x - (double)edges[toFind.get(j).id - 1].x, 2) +
                            Math.pow((double)result[currentEdgeIndex].y - (double)edges[toFind.get(j).id - 1].y, 2));
                    if(tempDistance<distance){
                        bestIndex = toFind.get(j).id -1;
                        bestJ = j;
                    }

                }
                if(j == toFind.size() - 1){
                    toFind.remove(bestJ);
                    result[i] = edges[bestIndex];
                }
            }
        }

        Individual ind = new Individual(edges.length);
        ind.genotype = result;
        return ind;
    }
}
