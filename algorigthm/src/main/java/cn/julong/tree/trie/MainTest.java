package cn.julong.tree.trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by weicm on 2016/7/9.
 */
public class MainTest {
	private static String FREQ = "separate";

	private static String PREFIX = "separate";

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();

		//将数据加入内存，并存入trie树
		ArrayList<String> ss = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(MainTest.class.getResourceAsStream("/bible.txt")));
		String word = null;
		int id = 0;
		while ((word = br.readLine()) != null) {
			String[] ws = word.split("\\s+");
			for (int i = 0; i < ws.length; i++) {
				String w = ws[i].trim();
				ss.add(w);
				if (!"".equals(w)) {
					trie.addWord(w.toLowerCase(), id);
				}
				id++;
			}
		}
		br.close();

		//词频查找
		int count = trie.wordFreq(FREQ);
		System.out.println("freq-----------------------------: " + FREQ);
		System.out.println(count);
		//前缀匹配查找
		System.out.println("prefix-----------------------------: " + PREFIX);
		ArrayList<Integer> co = trie.prefixSearch(PREFIX);
		for (Iterator<Integer> iterator = co.iterator(); iterator.hasNext(); ) {
			Integer index = iterator.next();
			System.out.println(index +  "\t" +ss.get(index));
		}
		System.out.println("\n");


		//删除，词频查找
		trie.deleteWord(FREQ, 95);

		//词频查找
		count = trie.wordFreq(FREQ);
		System.out.println("freq-----------------------------: " + FREQ);
		System.out.println(count);

		//前缀匹配查找
		System.out.println("prefix-----------------------------: " + PREFIX);
		co = trie.prefixSearch(PREFIX);
		for (Iterator<Integer> iterator = co.iterator(); iterator.hasNext(); ) {
			Integer index = iterator.next();
			System.out.println(index +  "\t" +ss.get(index));
		}

		System.out.println("\n");
		//排序
		System.out.println("sort-----------------------------");
		ArrayList<String> strs = trie.preorderIterator();
		for (Iterator<String> iterator = strs.iterator(); iterator.hasNext(); ) {
			System.out.println(iterator.next());
		}
	}
}
