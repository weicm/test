package cn.julong.disjointset;

/**
 * Created by Think on 2016/7/14.
 */
public class DisjointSet {
	private static final int SCALE = 10;
	private int[] father;
	private int[] rank;

	public DisjointSet() {
		father = new int[SCALE];
		rank = new int[SCALE];

		for (int i=0; i<SCALE; i++) {
			father[i] = i;
			rank[i] = 1;
		}
	}

	public int getFather(int x) {
		if(father[x] == x) {
			return father[x];
		}
		return getFather(father[x]);
	}

	public void union(int x, int y) {
		int xf = getFather(x);
		int yf = getFather(y);
		if(xf == yf) {
			return;
		}
		if(rank[xf] > rank[yf]) {
			father[yf] = xf;
		}else {
			if(rank[xf] > rank[yf]) {
				rank[yf]++;
			}
			father[xf] = yf;
		}
	}

	public boolean isSameSet(int x, int y) {
		return getFather(x) == getFather(y);
	}
}
