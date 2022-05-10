public class PercolationUF implements IPercolate{
    
    private IUnionFind myFinder;
    private boolean[][] myGrid;
	private final int VTOP;
	private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize(size*size + 2);
        VTOP = size*size;
		VBOTTOM = size*size + 1;
		myOpenCount = 0;
    }

    private int getVal(boolean[][] myGrid, int row, int col){
        return row*myGrid.length + col;
    }

    private boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

    @Override
    public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}


    @Override
    public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myFinder.connected(getVal(myGrid, row, col), VTOP);
	}

    @Override
    public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
        if (isOpen(row, col)){
            return ;
        }
        myGrid[row][col] = true;
        myOpenCount++;

        if (inBounds(row - 1, col) && isOpen(row - 1, col)){
            myFinder.union(getVal(myGrid, row, col), getVal(myGrid, row - 1, col));
        }
        if (inBounds(row, col - 1) && isOpen(row, col - 1)){
            myFinder.union(getVal(myGrid, row, col), getVal(myGrid, row, col - 1));
        }
        if (inBounds(row + 1, col) && isOpen(row + 1, col)){
            myFinder.union(getVal(myGrid, row, col), getVal(myGrid, row + 1, col));
        }
        if (inBounds(row, col + 1) && isOpen(row, col + 1)){
            myFinder.union(getVal(myGrid, row, col), getVal(myGrid, row, col + 1));
        }
        if (row == 0){
            myFinder.union(getVal(myGrid, row, col), VTOP);
        }
        if (row == myGrid.length - 1){
            myFinder.union(getVal(myGrid, row, col), VBOTTOM);
        }
    }

}
