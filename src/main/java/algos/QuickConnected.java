package algos;

public class QuickConnected {
    private int[] ids;

    QuickConnected(int n) {
        this.ids = new int[n];
        for(int i = 0 ; i < n ; i++) {
            this.ids[i] = i;
        }
    }

    public boolean connected(int p , int q) {
        return this.ids[p] == this.ids[q];
    }

    public void union(int p, int q) {
        int pid = this.ids[p];
        int qid = this.ids[q];

        for(int i = 0; i < this.ids.length; i++) {
            if(this.ids[i] == pid) {
                this.ids[i] = qid;
            }
        }
    }

}
