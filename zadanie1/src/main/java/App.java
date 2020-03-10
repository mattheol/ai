import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static String dataFile = new File("src/main/resources/kroA100.tsp").getAbsolutePath();

    public static List<Edge> loadEdges() throws Exception{
        List<Edge> edges = new ArrayList<Edge>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile));
        String line;
        int counter = 0;
        String splittedline[];
        while ((line = bufferedReader.readLine()) != null && !line.equals("EOF")) {
            counter++;
            if(counter>6){
                splittedline= line.split(" ");
                edges.add(new Edge((int)Double.parseDouble(splittedline[0]),
                        (int)Double.parseDouble(splittedline[1]),
                        (int)Double.parseDouble(splittedline[2])));
            }
        }
        return edges;
    }

    public static void main(String args[]) throws Exception {
        List<Edge> edges = loadEdges();
        Edge[] edgesTab=new Edge[edges.size()];
        edges.toArray(edgesTab);
        GreedyAlg greedyAlg = new GreedyAlg();
        Individual ind3 = greedyAlg.solve(edgesTab);
        ind3.showGenotype();
        System.out.println(ind3.fitness());

        SalesmanProblem pro = new SalesmanProblem(edges);

        Individual ind = pro.generateIndividual();
        Individual ind2 = pro.generateIndividual();

        ind.showGenotype();
        ind2.showGenotype();
        System.out.println(ind.cross(ind2).fitness());


    }
}
