package cn.julong.disjointset;

/**
 * Created by Think on 2016/7/14.
 */
public class DisjointSet {
	//规模
	private static int SCALE = 10;
	//当前节点的符节点
	private int[] father;
	//当前节点的秩（以当前节点为根的子树高度）
	private int[] rank;
	//当前节点是否使用过（为应用添加的字段，不是必须）
	private int[] used;

	public DisjointSet(Integer scale) {
		if(null != scale) {
			SCALE = scale;
		}
		father = new int[SCALE];
		rank = new int[SCALE];
		used = new int[SCALE];

		for (int i=0; i<SCALE; i++) {
			father[i] = i;
			rank[i] = 1;
			used[i] = 0;
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

	public void use(int i) {
		used[i] = 1;
	}
	public int getUsed(int i) {
		return used[i];
	}

	public int getSCALE() {
		return SCALE;
	}
}
