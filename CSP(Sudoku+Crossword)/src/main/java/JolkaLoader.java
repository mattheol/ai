import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JolkaLoader {
    private static String wordsFile = new File("src/main/resources/jolka/words1").getAbsolutePath();
    private static String crosswordFile = new File("src/main/resources/jolka/puzzle1").getAbsolutePath();

    public static List<String> loadDictionary() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(wordsFile));
        String line;
        String splittedline[];
        List<String> dictionary = new ArrayList<String>();
        while ((line = bufferedReader.readLine()) != null) {
            dictionary.add(line);
        }
        return dictionary;
    }

    public static JolkaSolver loadCrosswordProblem() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(crosswordFile));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        List<Node> horizontalNodes = new ArrayList<Node>();
        StringBuilder builder;
        int startIndex=0;
        String ln;
        for(int k=0; k<lines.size();k++){
            ln = lines.get(k);
            builder = new StringBuilder();
            for(int i=0;i<ln.length();i++){
                if(ln.charAt(i)!='#'){
                    if(builder.toString().length()==0){
                        startIndex = i;
                    }
                    builder.append(ln.charAt(i));
                }else{
                    if(builder.toString().length()>1){
                        horizontalNodes.add(new Node(k, startIndex, builder.toString(),false));
                        builder = new StringBuilder();
                    }else{
                        builder = new StringBuilder();
                    }
                }

                if(i== ln.length()-1 && builder.toString().length()>1){
                    horizontalNodes.add(new Node(k, startIndex, builder.toString(),false));
                }
            }
        }

        List<Node> verticalNodes = new ArrayList<Node>();
        for(int i=0;i<lines.get(0).length();i++){
            builder=new StringBuilder();
            for(int j=0;j<lines.size();j++){
                ln = lines.get(j);
                if(ln.charAt(i)!='#'){
                    if(builder.toString().length()==0){
                        startIndex = j;
                    }
                    builder.append(ln.charAt(i));
                }else{
                    if(builder.toString().length()>1){
                        verticalNodes.add(new Node(startIndex,i, builder.toString(),true));
                        builder = new StringBuilder();
                    }else{
                        builder = new StringBuilder();
                    }
                }

                if(j== lines.size()-1 && builder.toString().length()>1){
                    verticalNodes.add(new Node(startIndex,i, builder.toString(),true));
                }
            }
        }
        List<Node> nodes=new ArrayList<>();
        for(Node node:horizontalNodes){
            nodes.add(node);
        }
        for(Node node:verticalNodes){
            nodes.add(node);
        }
        List<String> words = loadDictionary();
        for(String word:words){
            for(Node node:nodes){
                if(word.length() == node.word.length()){
                    node.possibleWords.add(word);
                }
            }
        }
        return new JolkaSolver(nodes, words);
    }

    public static JolkaFC loadCrosswordProblemFC() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(crosswordFile));
        String line;
        List<String> lines = new ArrayList<String>();
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        List<Node> horizontalNodes = new ArrayList<Node>();
        StringBuilder builder;
        int startIndex=0;
        String ln;
        for(int k=0; k<lines.size();k++){
            ln = lines.get(k);
            builder = new StringBuilder();
            for(int i=0;i<ln.length();i++){
                if(ln.charAt(i)!='#'){
                    if(builder.toString().length()==0){
                        startIndex = i;
                    }
                    builder.append(ln.charAt(i));
                }else{
                    if(builder.toString().length()>1){
                        horizontalNodes.add(new Node(k, startIndex, builder.toString(),false));
                        builder = new StringBuilder();
                    }else{
                        builder = new StringBuilder();
                    }
                }

                if(i== ln.length()-1 && builder.toString().length()>1){
                    horizontalNodes.add(new Node(k, startIndex, builder.toString(),false));
                }
            }
        }

        List<Node> verticalNodes = new ArrayList<Node>();
        for(int i=0;i<lines.get(0).length();i++){
            builder=new StringBuilder();
            for(int j=0;j<lines.size();j++){
                ln = lines.get(j);
                if(ln.charAt(i)!='#'){
                    if(builder.toString().length()==0){
                        startIndex = j;
                    }
                    builder.append(ln.charAt(i));
                }else{
                    if(builder.toString().length()>1){
                        verticalNodes.add(new Node(startIndex,i, builder.toString(),true));
                        builder = new StringBuilder();
                    }else{
                        builder = new StringBuilder();
                    }
                }

                if(j== lines.size()-1 && builder.toString().length()>1){
                    verticalNodes.add(new Node(startIndex,i, builder.toString(),true));
                }
            }
        }
        List<Node> nodes=new ArrayList<>();
        for(Node node:horizontalNodes){
            nodes.add(node);
        }
        for(Node node:verticalNodes){
            nodes.add(node);
        }
        List<String> words = loadDictionary();
        for(String word:words){
            for(Node node:nodes){
                if(word.length() == node.word.length()){
                    node.possibleWords.add(word);
                }
            }
        }
        return new JolkaFC(nodes, words);
    }


}
