import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int length;
    private WeightedQuickUnionUF uf;
    private int[] openSite;
    private int openCount;

    public Percolation(int n) {   //Check legal
        if (n < 1)
            throw new IllegalArgumentException("info " + n + " is smaller than 1");
        length = n;
        int total = n * n + 2;
        openSite = new int[total];
        for (int i = 0; i < openSite.length; i++) {
            openSite[i] = 0;
        }
        openSite[0] = 1;
        openSite[total - 1] = 1;
        //Create a recorder
        uf = new WeightedQuickUnionUF(total);
        for (int i = 1; i <= length; i++) {
            uf.union(0, i);
            uf.union(total - 1, total - 1 - i);
        }
        //connect all items
    }

    // create n-by-n grid, with all sites blocked

    private int getIndex(int row, int col) {
        return (row - 1) * length + col;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        Percolation p2 = new Percolation(2);
        System.out.println(p2.length);
        System.out.println(p.isFull(1, 1));
        System.out.println(p.percolates());
        p.open(1, 1);
        System.out.println(p.percolates());
//        p.open(1,2);
//        System.out.println(p.percolates());
//        p.open(2,2);
//        p.open(3,2);
        System.out.println(p.numberOfOpenSites());

        //System.out.println(p.uf.connected(getIndex(1,1),1));*/

    }// test client (optional)

    public void open(int row, int col) {
        //Check if input is legal
        if (row < 1 || row > length || col > length || col < 1) {
            throw new IllegalArgumentException("info " + row + " or " + col + " is smaller than 1");
        }
        int index = getIndex(row, col);
        //需要检测是否已经open
        if (openSite[index] != 1) {
            openSite[index] = 1;
            openCount++;
        }

        //UP
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                int upIndex = getIndex(row - 1, col);
                uf.union(upIndex, index);
            }
        }
        //DOWN
        if (row < length) {
            if (isOpen(row + 1, col)) {
                int downIndex = getIndex(row + 1, col);
                uf.union(downIndex, index);
            }
        }

        //LEFT
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                int leftIndex = getIndex(row, col - 1);
                uf.union(leftIndex, index);
            }
        }

        //RIGHT
        if (col < length) {
            if (isOpen(row, col + 1)) {
                int rightIndex = getIndex(row, col + 1);
                uf.union(rightIndex, index);
            }
        }

    }// open site (row, col) if it is not open already

    public boolean isOpen(int row, int col)// is site (row, col) open?
    {
        if (row < 1 || row > length || col > length || col < 1) {
            throw new IllegalArgumentException("info " + row + " or " + col + " is smaller than 1");
        }
        return (openSite[getIndex(row, col)] == 1);
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > length || col > length || col < 1) {
            throw new IllegalArgumentException("info " + row + " or " + col + " is smaller than 1");
        }
        int index = getIndex(row, col);
        return (isOpen(row, col) && uf.connected(0, index));
    }// is site (row, col) full?

    public int numberOfOpenSites() {
        return openCount;
    }// number of open sites

    public boolean percolates() {
        if(length==1)
        {
            return openCount==1;
        }
        return uf.connected(0, length * length + 1);
    }// does the system percolate?

}
