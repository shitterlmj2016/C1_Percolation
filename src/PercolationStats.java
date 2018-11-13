import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] records;
    private int length;
    private int trials;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1)
            throw new java.lang.IllegalArgumentException("info " + n + " is smaller than 1");
        this.trials = trials;
        this.length = n;
        records = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(length);

            while (!p.percolates()) {
                int row = StdRandom.uniform(1, length + 1);
                int col = StdRandom.uniform(1, length + 1);
                p.open(row, col);
                //System.out.println(p.numberOfOpenSites());
            }

            records[i] = p.numberOfOpenSites()/(double)(n*n);
        }


        mean=StdStats.mean(records);
        stddev=StdStats.stddev(records);
    }// perform trials independent experiments on an n-by-n grid

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        double x = stats.mean();
        double s = stats.stddev();
        double low = stats.confidenceLo();
        double hi = stats.confidenceHi();
        System.out.printf("mean=%f\n", x);
        System.out.printf("stddev=%f\n", s);
        System.out.printf("%f %f\n", low, hi);


    }// test client (described below)

    public double mean() {
        if(mean!=0)
            return mean;
        return StdStats.mean(records);
    }// sample mean of percolation threshold

    public double stddev() {
        if(stddev!=0)
            return stddev;
        return StdStats.stddev(records);
    }// sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean()-1.96*stddev()/Math.sqrt(length);
    }// low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean()-1.96*stddev()/Math.sqrt(length);
    }// high endpoint of 95% confidence interval
}
