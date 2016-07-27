package cn.julong.disjointset;

import java.util.Scanner;

/**
 * Created by Think on 2016/7/27.
 * 输入一组节点构成森林
 * 判断森林是否有回路、是否在只是一个图的森林
 * 应用说明：disjointsetApp.doc
 */
public class DisjointSetApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(-1 == a && -1 == b) {
				break;
			}
			if(0 == a && 0 == b) {
				System.out.println("yes");
			}
			boolean flag = true;
			DisjointSet set = new DisjointSet(10);

			while (true) {
				if(0 == a && 0 == b) {
					break;
				}
				a = set.getFather(a);
				b = set.getFather(b);
				if(a != b) {
					set.union(a, b);
				}else {
					flag = false;
				}
				set.use(a);
				set.use(b);
				a = sc.nextInt();
				b = sc.nextInt();
			}

			int k = 0;
			for (int i=0; i<set.getSCALE(); i++) {
				if(set.getUsed(i) == 1 && set.getFather(i) == i) {
					k++;
				}
			}
			if(k > 1) {
				flag = false;
			}

			if (flag)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
