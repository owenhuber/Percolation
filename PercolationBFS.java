import java.util.Queue;
import java.util.LinkedList;

public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int n){
        super(n);
    }

    @Override
    protected void search(int row, int col) {

        if (!inBounds(row, col)){
            return;
        }
        myGrid[row][col] = FULL;
		Queue<int[]> qp = new LinkedList<>();
        qp.add(new int[] {row, col});

        while (qp.size() > 0){
            int[] sub = qp.remove();
            int theRow = sub[0];
            int theCol = sub[1];

            if (inBounds(theRow - 1, theCol) && isOpen(theRow - 1, theCol) && !isFull(theRow - 1, theCol)){
                myGrid[theRow -1][theCol] = FULL;
                qp.add(new int[] {theRow - 1, theCol});
            }
            if (inBounds(theRow, theCol - 1) && isOpen(theRow, theCol - 1) && !isFull(theRow, theCol - 1)){
                myGrid[theRow][theCol - 1] = FULL;
                qp.add(new int[] {theRow, theCol - 1});
            }
            if (inBounds(theRow + 1, theCol) && isOpen(theRow + 1, theCol) && !isFull(theRow + 1, theCol)){
                myGrid[theRow + 1][theCol] = FULL;
                qp.add(new int[] {theRow + 1, theCol});
            }
            if (inBounds(theRow, theCol + 1) && isOpen(theRow, theCol + 1) && !isFull(theRow, theCol + 1)){
                myGrid[theRow][theCol + 1] = FULL;
                qp.add(new int[] {theRow, theCol + 1});
            }
        }

	}
}
