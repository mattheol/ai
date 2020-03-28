import java.util.ArrayList;
import java.util.List;

public class JolkaSolver {
    public List<Node> nodes;
    public List<String> dictionary;
    public List<List<Node>> solutions=new ArrayList<>();
    public int totalVisitedNodes=0;
    public int fstSolVisitedNodes=0;
    public int totalBacks=0;
    public int totalBacksTofstSol=0;

    public JolkaSolver(List<Node> nodes, List<String> dictionary) {
        this.nodes= nodes;
        this.dictionary = dictionary;
    }

    public void solve(List<String> words,List<Node> nodes, int i){
        if(words.isEmpty()){
            if(solutions.isEmpty()){
                fstSolVisitedNodes = totalVisitedNodes;
                totalBacksTofstSol = totalBacks;
            }
            solutions.add(nodes);
            return;
        }
            Node node = nodes.get(i);
            if(!node.isFound) {
                for (int w = 0; w < node.possibleWords.size(); w++) {
                    totalVisitedNodes += 1;
                    String word = node.possibleWords.get(w);
                    if (words.contains(word) && isCorrect(word, node, nodes)) {
                        node.isFound = true;
                        node.word = word;
                        List<String> newWords = new ArrayList<>();
                        for (String orgWord: words) {
                            if (!orgWord.equals(word)) {
                                newWords.add(orgWord);
                            }
                        }
                        solve(newWords, nodes, i+1);
                        node.isFound = false;
                        node.word = node.pattern;
                        totalBacks += 1;
                    }
                }
        }
    }


    public boolean isCorrect(String word, Node node, List<Node> nodes){
        if(!node.isFound && !node.usedWords.contains(word)){
            for(int i=0;i<nodes.size();i++){
                Node currNode = nodes.get(i);
                if(node.isVertical && !currNode.isVertical && currNode.isFound) {
                    if(currNode.y<=node.y && node.y<=currNode.y +currNode.word.length()-1
                            && node.x<=currNode.x && currNode.x<=node.x+node.word.length()-1){
                        if(currNode.word.charAt(node.y - currNode.y)!=word.charAt(currNode.x-node.x)){
                            return false;
                        }
                    }
                }
                if(!node.isVertical && currNode.isVertical && currNode.isFound){
                    if(node.y<=currNode.y && currNode.y<=node.y +node.word.length()-1
                            && currNode.x<=node.x && node.x<=currNode.x+currNode.word.length()-1){
                        if(word.charAt(currNode.y - node.y)!=currNode.word.charAt(node.x-currNode.x)){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void start(){

        solve(dictionary,nodes,0);
    }

    public void printResult(){
        int counter = 0;
        for(int i=0;i<solutions.size();i++){
            List<Node> solution = solutions.get(i);
           /* for(Node node: solution){
                System.out.println(node);
            }
            System.out.println("\n");*/
            counter++;
        }
        System.out.println("\nLiczba rozwiązań: "+counter);
        System.out.println("Liczba odwiedzonych węzłów do 1 rozwiązania: "+fstSolVisitedNodes);
        System.out.println("Liczba nawrotów do 1 rozwiązania: "+totalBacksTofstSol);
        System.out.println("Liczba wszystkich odwiedzonych węzłów: "+totalVisitedNodes);
        System.out.println("Liczba wszystkich nawrotów: "+totalBacks);

    }




}
