import java.util.List;

public class SudokuSolver {
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

    public SudokuSolver(int[][] board) {
        this.board = board;
        this.length = board.length;
        this.foundInRow = new int[board.length][board.length];
        this.foundInColumn = new int[board.length][board.length];
        this.foundInQuad = new int[board.length][board.length];
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
            printBoard();
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
            for(int i=1; i<=board.length;i++){
                totalVisitedNodes+=1;
                if(foundInRow[row][i-1] == 0 && foundInColumn[column][i-1]==0
                        && foundInQuad[q][i-1] == 0){
                    board[row][column] = i;
                    foundInRow[row][i-1] = 1;
                    foundInColumn[column][i-1] = 1;
                    foundInQuad[q][i-1] = 1;
                    solve(row, column + 1);
                    board[row][column] = 0;
                    foundInRow[row][i-1] = 0;
                    foundInColumn[column][i-1] = 0;
                    foundInQuad[q][i-1] = 0;
                    totalBacks+=1;
                }
            }
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
