import java.util.ArrayList;
import java.util.List;

public class SudokuFC {
    private int[][] board;
    private int[][] foundInRow;
    private int[][] foundInColumn;
    private int[][] foundInQuad;
    private int length;
    public int nrOfSolutions=0;
    public int totalVisitedNodes=0;
    public int fstSolVisitedNodes=0;
    public int totalBacks=0;
    public int totalBacksTofstSol=0;
    public List<Integer>[][] domains;


    public SudokuFC(int[][] board) {
        this.board = board;
        this.length = board.length;
        this.foundInRow = new int[board.length][board.length];
        this.foundInColumn = new int[board.length][board.length];
        this.foundInQuad = new int[board.length][board.length];
        domains = new ArrayList[9][9];
        for(int i=0; i<9;i++){
            for(int j=0; j<9;j++){
                domains[i][j]=new ArrayList<>();
            }
        }
        for(int i=0; i<9;i++){
            for(int j=0; j<9;j++){
                for(int z=1;z<10;z++)
                domains[i][j].add(z);
            }
        }
    }

    public int quad(int x, int y) {
        return 3*(x/3) + y/3;
    }


    public void start(){
        for(int i=0; i<length;i++){
            for(int j=0; j<length;j++){
                if(board[i][j] != 0){
                    foundInRow[i][board[i][j]-1]=1;
                    foundInColumn[j][board[i][j]-1]=1;
                    int q = quad(i, j);
                    foundInQuad[q][board[i][j]-1] = 1;
                }
            }
        }
        solve(0,0);
    }

    public void solve(int row, int column){
        if(row==board.length){
            if(nrOfSolutions==0){
                fstSolVisitedNodes = totalVisitedNodes;
                totalBacksTofstSol = totalBacks;
            }
            nrOfSolutions++;
            //printBoard();
            return;
        }
        if(column==board.length){
            row++;
            column=0;
            solve(row, column);
            return;
        }
        if(board[row][column]!=0){
            column++;
            solve(row, column);
        }else{
            int q = quad(row,column);
            List<Integer> dom = domains[row][column];
            for(int i=0;i<dom.size();i++){
                int value = dom.get(i);
                totalVisitedNodes+=1;
                if(foundInRow[row][value-1] == 0 && foundInColumn[column][value-1]==0
                        && foundInQuad[q][value-1] == 0){
                    boolean isCorrect = forwardCheckLine(row, column, value);
                    if(isCorrect){
                        board[row][column] = value;
                        foundInRow[row][value-1] = 1;
                        foundInColumn[column][value-1] = 1;
                        foundInQuad[q][value-1] = 1;
                        solve(row, column + 1);
                        board[row][column] = 0;
                        foundInRow[row][value-1] = 0;
                        foundInColumn[column][value-1] = 0;
                        forwardFix(row,column,value);
                        foundInQuad[q][value-1] = 0;
                        totalBacks+=1;
                    }
                }
            }
        }
    }

    public boolean forwardCheckLine(int row, int column, int value){
        for(int rr=row+1;rr<9;rr++){
            if(board[rr][column]==0){
                List<Integer> currDomain = domains[rr][column];
                if(currDomain.isEmpty() || (currDomain.contains(value) && currDomain.size()==1)){
                    return false;
                }
            }
        }
        for(int cc=column+1;cc<9;cc++){
            if(board[row][cc]==0){
                List<Integer> currDomain = domains[row][cc];
                if(currDomain.isEmpty() || (currDomain.contains(value) && currDomain.size()==1)){
                    return false;
                }
            }
        }
        for(int rr=row+1;rr<9;rr++){
            if(board[rr][column]==0) {
                List<Integer> currDomain = domains[rr][column];
                if (currDomain.contains(value)) currDomain.remove(Integer.valueOf(value));
            }
        }
        for(int cc=column+1;cc<9;cc++){
            if(board[row][cc]==0) {
                List<Integer> currDomain = domains[row][cc];
                if (currDomain.contains(value)) currDomain.remove(Integer.valueOf(value));
            }
        }
        return true;
    }

    public void forwardFix(int row, int column, int value){
        for(int rr=row;rr<9;rr++){
            List<Integer> currDomain = domains[rr][column];
            if(!currDomain.contains(Integer.valueOf(value))) currDomain.add(value);
        }
        for(int cc=column;cc<9;cc++){
            List<Integer> currDomain = domains[row][cc];
            if(!currDomain.contains(Integer.valueOf(value))) currDomain.add(value);
        }
    }

    public void printBoard() {
        System.out.println();
        for(int i=0;i<board.length;++i) {
            for(int j=0;j<board.length;++j) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }



    public void printResult(){
        System.out.println("\nLiczba rozwiązań: "+nrOfSolutions);
        System.out.println("Liczba odwiedzonych węzłów do 1 rozwiązania: "+fstSolVisitedNodes);
        System.out.println("Liczba nawrotów do 1 rozwiązania: "+totalBacksTofstSol);
        System.out.println("Liczba wszystkich odwiedzonych węzłów: "+totalVisitedNodes);
        System.out.println("Liczba wszystkich nawrotów: "+totalBacks);
    }
}
