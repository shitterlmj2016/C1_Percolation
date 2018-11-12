import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private QuickUnionUF uf;
    private static int length;
    private int[] openSite;

    public Percolation(int n) throws java.lang.IllegalArgumentException {   //Check legal
        if (n < 1)
            throw new IllegalArgumentException("info " + n + " is smaller than 1");
        length=n;
        int total = n * n + 2;
        openSite = new int[total];
        for (int i = 0; i < openSite.length; i++) {
            openSite[i] = 0;
        }
        openSite[0] = 1;
        openSite[total - 1] = 1;
        //Create a recorder
        uf=new QuickUnionUF(total);
        for(int i=1; i<length;i++){
            uf.union(0,i);
            uf.union(total-1,total-1-i);
        }
        //connect all items
    }

    ;               // create n-by-n grid, with all sites blocked


    public void open(int row, int col) throws java.lang.IllegalArgumentException{
        //Check if input is legal

    }// open site (row, col) if it is not open already

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return true;
    }

    public boolean isFull(int row, int col) {
        return true;
    }// is site (row, col) full?

    public int numberOfOpenSites() {
        return 0;
    }// number of open sites

    public boolean percolates() {
        return true;
    }// does the system percolate?

    public static int getIndex(int row, int col){
        return (row-1)*length+col;
    }


    public static void main(String[] args) {
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(8);
        wuf.union(0, 1);
        wuf.union(0, 2);

        int a = 1;

        Percolation p = new Percolation(4);
        System.out.println(getIndex(2,3));
    }// test client (optional)
}
