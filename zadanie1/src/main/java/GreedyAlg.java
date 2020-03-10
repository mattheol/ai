import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GreedyAlg {

    public Individual solve(Edge[] edges){
        List<Integer> list= new ArrayList<Integer>();
        Edge[] result = new Edge[edges.length];
        for(int k=1;k<edges.length;k++){
            list.add(edges[k].id);
        }
        result[0] = edges[0];
        int bestIndex = 0;
        double bestDistance = 0;
        double tempDistance = 0;
        int position = 1;
        while(position < edges.length){
            for(int i=0;i<list.size();i++){
                if(i==0){
                    bestDistance = Math.sqrt(Math.pow((double)edges[list.get(0) - 1].x - (double)result[position-1].x, 2) +
                                     Math.pow((double)edges[list.get(0) - 1].y - (double)result[position-1].y, 2));
                    bestIndex = 0;
                }else{
                    tempDistance = Math.sqrt(Math.pow((double)edges[list.get(i)-1].x - (double)result[position-1].x, 2) +
                            Math.pow((double)edges[list.get(i)-1].y - (double)result[position-1].y, 2));
                    if(tempDistance < bestDistance){
                        bestDistance = tempDistance;
                        bestIndex = i;
                    }
                }
            }
            result[position] = edges[list.get(bestIndex)-1];
            list.remove(bestIndex);
            position++;
        }

        Individual ind = new Individual(edges.length);
        ind.genotype = result;
        return ind;
    }
}
