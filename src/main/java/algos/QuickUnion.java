package algos;

public class QuickUnion {
    private int[] ids;

    public QuickUnion(int n) {
        this.ids = new int[n];
        for(int i = 0 ; i < n ; i++) {
            this.ids[i] = i;
        }
    }

    private int root(int i) {
        while(i != root(i)) {
            i = root(i);
        }
        return i;
    }

    public boolean connected(int p , int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        this.ids[rootP] = rootQ;
    }
}
