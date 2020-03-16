import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Individual implements  Cloneable{
    public static Random random = new Random() ;
    public Edge genotype[];
    public int sizeOfGenotype;

    public Individual(int sizeOfGenotype){
        this.genotype = new Edge[sizeOfGenotype];
        this.sizeOfGenotype = sizeOfGenotype;
    }

    public double fitness(){
        double distance=0;

        for(int i=0;i<sizeOfGenotype-1;i++){
            distance += Math.sqrt(Math.pow((double)genotype[i+1].x - (double)genotype[i].x, 2) +
                    Math.pow((double)genotype[i+1].y - (double)genotype[i].y, 2)) ;
        }
        distance += Math.sqrt(Math.pow((double)genotype[sizeOfGenotype-1].x - (double)genotype[0].x, 2) +
                Math.pow((double)genotype[sizeOfGenotype-1].y - (double)genotype[0].y, 2)) ;
        return distance;
    }
/*
    public void mutate(int position){
        int position2 = 0;
        while(position2==0){
            position2 = random.nextInt(sizeOfGenotype);
        }
        Edge toSwap = genotype[position];
        genotype[position] = genotype[position2];
        genotype[position2] = toSwap;
    }
*/
    public void mutate(){
        int position1 = 0;
        while(position1==0){
            position1 = random.nextInt(sizeOfGenotype);
        }
        int position2 = 0;
        while(position2==0 || position1 == position2){
            position2 = random.nextInt(sizeOfGenotype);
        }
        if(position1 >position2){
            int pos = position1;
            position1 = 2;
            position2 = pos;
        }
        while(position1 != position2){
            Edge toSwap = genotype[position1];
            genotype[position1] = genotype[position2];
            genotype[position2] = toSwap;
            position1++;
        }
    }

    public Individual cross(Individual partner){
        Individual child = new Individual(sizeOfGenotype);
        Set<Integer> set = new HashSet<Integer>();
        int point = 0;
        while(point == 0)
            point = random.nextInt(sizeOfGenotype);
        child.genotype[0]=this.genotype[0];
        set.add(this.genotype[0].id);
        for(int i = 0; i< sizeOfGenotype/2; i++){
            set.add(this.genotype[point].id);
            child.genotype[point] = this.genotype[point];
            if(point + 1 == sizeOfGenotype){
                point = 1;
            }else{
                point++;
            }
        }
        int k;
        boolean found;
        while(set.size() < sizeOfGenotype){
            if(!set.contains(partner.genotype[point].id)){
                child.genotype[point] = partner.genotype[point];
                set.add(partner.genotype[point].id);
                if(point + 1 == sizeOfGenotype){
                    point = 1;
                }else{
                    point++;
                }
            }else{
                found = false;
                k = point;
                while(!found){
                    if(!set.contains(partner.genotype[k].id)){
                        found = true;
                        child.genotype[point] = partner.genotype[k];
                        set.add(partner.genotype[k].id);
                    }else{
                        if( k + 1 ==sizeOfGenotype){
                            k=1;
                        }
                        else{
                            k++;
                        }
                    }
                }
                if(point + 1 == sizeOfGenotype){
                    point = 1;
                }else{
                    point++;
                }
            }
        }
        return child;
    }

    public void showGenotype(){
        System.out.print("[ ");
        for(Edge e : genotype){
            System.out.print(e.id+" ");
        }
        System.out.print("]");
        System.out.println();

    }

    @Override
    public Individual clone(){
        Individual ind = new Individual(sizeOfGenotype);
        for(int i=0;i<sizeOfGenotype;i++){
            ind.genotype[i] = this.genotype[i];
        }
        return ind;
    }
}
