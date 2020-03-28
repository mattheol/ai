import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SudokuLoader {
    private static String dataFile = new File("src/main/resources/Sudoku.csv").getAbsolutePath();

    public static int[][] loadSudoku(int puzzleNumber) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile));
        String line;
        String splittedline[];
        int counter=0;
        while ((line = bufferedReader.readLine()) != null && counter !=puzzleNumber) {
            counter++;
        }
        splittedline = line.split(";");
        String sudokuString = splittedline[2];
        int board[][] = new int[9][9];
        int position = 0;
        for(int i=0;i<9;i++){
            for(int j=0; j<9;j++){
                if(sudokuString.charAt(position)=='.')
                {
                    board[i][j]=0;
                }else{
                    board[i][j]=Character.getNumericValue(sudokuString.charAt(position));
                }
                position++;
            }
        }
        return board;
    }
}
