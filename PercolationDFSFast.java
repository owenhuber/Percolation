public class PercolationDFSFast extends PercolationDFS{

    public PercolationDFSFast(int n){
        super(n);
    }

    @Override
    protected void updateOnOpen(int row, int col){
        int k = 0;
        if (row == 0){
            k = 1;
        }
        if (row != 0 && myGrid[row - 1][col] == FULL){
            k = 1;
        }
        if (col != 0 && myGrid[row][col - 1] == FULL){
            k = 1;
        }
        if (myGrid[row].length - 1 != row && myGrid[row + 1][col] == FULL){
            k = 1;
        }
        if (myGrid[col].length - 1 != col &&myGrid[row][col + 1] == FULL){
            k = 1;
        }
        if (k == 1){
            search(row, col);
        }
    }

}