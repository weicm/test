package cn.julong.disjointset;

import java.util.Scanner;

/**
 * Created by Think on 2016/7/26.
 */
public class MainTest {
	public static void main(String[] args) {
		int[][] inputs = new int[][] {
			{2,4},
			{5,7},
			{1,3},
			{8,9},
			{1,2},
			{5,6},
			{2,3}
		};

		DisjointSet set = new DisjointSet(11);

		for (int i=0; i<inputs.length; i++) {
			int a = set.getFather(inputs[i][0]);
			int b = set.getFather(inputs[i][1]);
			set.union(a, b);
		}

		Scanner scanner = new Scanner(System.in);
		int k = 0;
		while (k < 3) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			k++;
			if (set.getFather(a) == set.getFather(b))
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
