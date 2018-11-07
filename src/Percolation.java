import edu.princeton.cs.algs4.*;

public class Percolation {
    private int [][] grid;

    QuickUnionUF uf=new QuickUnionUF(7);

    public Percolation(int n)
    {   //Check legal
        grid = new int[n][n];
        for(int i=0;i<n;i++){
            for( int j=0; j<n;j++)
                grid[i][j]=0;
        }
    };               // create n-by-n grid, with all sites blocked

    public void open(int row, int col)
    {
        //Check if input is legal
        grid[row][col]=0;
    }// open site (row, col) if it is not open already

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return grid[row][col]==1;
    }
    public boolean isFull(int row, int col)
    {
        return true;
    }// is site (row, col) full?
    public int numberOfOpenSites()
    {
        return 0;
    }// number of open sites
    public boolean percolates()
    {
        return true;
    }// does the system percolate?

    public static void main(String[] args)
    {
    }// test client (optional)
}
