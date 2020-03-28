import java.util.List;
import java.util.Timer;

public class Main {


    public static void main(String args[]) throws Exception{
        /*int[][] board=SudokuLoader.loadSudoku(1);
        SudokuSolver solver = new SudokuSolver(board);
        solver.start();
        solver.printResult();

        SudokuFC solverFC = new SudokuFC(board);
        solverFC.start();
        solverFC.printResult();*/

        JolkaSolver solverJ = JolkaLoader.loadCrosswordProblem();
        solverJ.start();
        solverJ.printResult();

        JolkaFC solverJFC = JolkaLoader.loadCrosswordProblemFC();
        solverJFC.start();
        solverJFC.printResult();
    }



}
