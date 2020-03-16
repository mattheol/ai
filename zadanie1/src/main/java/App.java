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
        System.out.println(ind3.fitness());

        SalesmanProblem pro = new SalesmanProblem(edges,1000,0.5,0.85,"wynik1");
        pro.startEvolutionAlghoritm(1000,5);
        /*
        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik2");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik3");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik4");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik5");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik6");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik7");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik8");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik9");
        pro.startEvolutionAlghoritm(200,5);

        pro = new SalesmanProblem(edges,5000,0.006,0.8,"wynik10");
        pro.startEvolutionAlghoritm(200,5);
        */
    }
}
