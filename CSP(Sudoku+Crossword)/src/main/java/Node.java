import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Cloneable{
    public int x, y;
    public String word;
    public List<String> usedWords;
    public List<String> possibleWords;
    public boolean isVertical;
    public boolean isFound;
    public String pattern;


    public Node(int x, int y, String word,boolean isVertical) {
        this.x = x;
        this.y = y;
        this.word = word;
        this.pattern = word;
        isFound = false;
        this.isVertical=isVertical;
        usedWords = new ArrayList<>();
        possibleWords = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", word='" + word + '\'' +
                ", isFound="+isFound+", "+"isV="+isVertical+
                "}";
    }

    public Node clone(){
        Node newNode = new Node(x,y,word,isVertical);
        newNode.usedWords = new ArrayList<>();
        newNode.possibleWords = new ArrayList<>();
        for(String words:this.usedWords){
            newNode.usedWords.add(words);
        }
        for(String word: possibleWords){
            newNode.possibleWords.add(word);
        }
        newNode.isFound = this.isFound;
        return newNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y &&
                isVertical == node.isVertical &&
                isFound == node.isFound &&
                word.equals(node.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, word, isVertical, isFound);
    }
}
