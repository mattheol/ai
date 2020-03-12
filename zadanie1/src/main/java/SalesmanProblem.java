import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SalesmanProblem {
    public int sizeOfPopulation;
    public Individual[] population;
    public List<Edge> edges;
    public double mutationProb;
    public double crossProb;
    BufferedWriter writer;
    private static String SCORE_FILE_PATH = new File("src/main/resources/wyniki1.txt").getAbsolutePath();


    public SalesmanProblem(List<Edge> edges, int sizeOfPopulation, double mutationProb, double crossProb){
        this.edges = edges;
        this.sizeOfPopulation = sizeOfPopulation;
        this.mutationProb = mutationProb;
        this.crossProb = crossProb;
        try{
            writer = new BufferedWriter(new FileWriter(SCORE_FILE_PATH, false));
        }catch (Exception e){
            e.printStackTrace();
        }
        population = new Individual[sizeOfPopulation];
        generatePopulation();
    }

    public void generatePopulation(){
        for(int i=0;i<sizeOfPopulation;i++){
            population[i] = generateIndividual();
        }
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

    public void startEvolutionAlghoritm(int numberOfGenerations, int sizeOfTour){
        Individual[] newPopulation = new Individual[sizeOfPopulation];
        int newPopSize;
        Random random = new Random();
        int randomNumber;
        double randomProb;
        int groupSize;
        Individual[] parents = new Individual[2];
        Individual[] group = new Individual[sizeOfTour];
        int generationNumber = 0;
        while(generationNumber < numberOfGenerations){
            groupSize = 0;
            newPopSize = 0;
            while(newPopSize < sizeOfPopulation){
                //Random individuals selected from population
                while(groupSize < sizeOfTour){
                    randomNumber = random.nextInt(sizeOfPopulation);
                    group[groupSize] = population[randomNumber];
                    groupSize++;
                }
                //first parent from tournament
                parents[0] = selectBestFromTour(group);
                groupSize = 0;
                while(groupSize < sizeOfTour){
                    randomNumber = random.nextInt(sizeOfPopulation);
                    group[groupSize] = population[randomNumber];
                    groupSize++;
                }
                //second parent from tournament
                parents[1] = selectBestFromTour(group);
                //cross
                randomProb = random.nextDouble();
                if(randomProb < crossProb){
                    newPopulation[newPopSize] = parents[0].cross(parents[1]);
                    newPopSize++;
                    newPopulation[newPopSize] = parents[0].cross(parents[1]);
                    newPopSize++;

                }else{
                    newPopulation[newPopSize] = parents[0];
                    newPopSize++;
                    newPopulation[newPopSize] = parents[1];
                    newPopSize++;
                }
                //mutation
                randomProb = random.nextDouble();
                if(randomProb < mutationProb){
                    newPopulation[newPopSize-1].mutate();
                }
            }
            population = newPopulation;
            //Write population score to file
            writeToFile(generationNumber);
            generationNumber++;
        }try{
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeToFile(int generationNumber){
        double average = 0;
        double bestFitness = population[0].fitness();
        double worstFitness = population[0].fitness();

        for(int i=0;i<sizeOfPopulation;i++){
            if(population[i].fitness() < bestFitness){
                bestFitness = population[i].fitness();
            }
            if(population[i].fitness() > worstFitness){
                worstFitness = population[i].fitness();
            }
            average += population[i].fitness();
        }
        average /= sizeOfPopulation;
        String gen = String.valueOf(generationNumber + 1);
        try{
            writer.write(gen+"; "+bestFitness+"; "+average+"; "+worstFitness);
            writer.newLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Individual selectBestFromTour(Individual[] group){
        double bestFitness = group[0].fitness();
        Individual bestIndividual = group[0];
        for(int i=1;i<group.length;i++ ){
            if(group[i].fitness() < bestFitness){
                bestFitness = group[i].fitness();
                bestIndividual = group[i];
            }
        }
        return bestIndividual;
    }

    public void showPopulation(){
        for(Individual ind: population){
            ind.showGenotype();
        }
    }

}
