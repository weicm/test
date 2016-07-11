package cn.julong.tree.trie;

import java.util.ArrayList;

/**
 * Created by weicm on 2016/7/9.
 */
public class TrieNode {
	//字符
	private char c;
	//词频
	private int freq;
	//子节点
	private TrieNode[] childrens;

	//途径记录编号
	private ArrayList<Integer> ids = new ArrayList();
	//节点初始化
	public TrieNode() {
		childrens = new TrieNode[26];
		freq = 0;
	}

	public TrieNode[] getChildrens() {
		return childrens;
	}

	public void setChildrens(TrieNode[] childrens) {
		this.childrens = childrens;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	public ArrayList<Integer> getIds() {
		return ids;
	}

	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}
}
