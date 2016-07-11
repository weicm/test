package cn.julong.tree.trie;


import java.util.ArrayList;

/**
 * Created by weicm on 2016/7/9.
 */
public class Trie {
	//根节点
	private TrieNode root = new TrieNode();

	/**
	 * 添加单词
	 * @param word
	 * @param id
	 */
	public void addWord(String word, int id) {
		addWord(root, word, id);
	}

	private void addWord(TrieNode node, String word, int id) {
		//单词是否为空，为空直接返回
		if (null == word || word.length() == 0) {
			return;
		}
		//获取当前节点值，计算节点位置
		char c = word.charAt(0);
		int index = c - 'a';
		//节点为空时，初始化节点并记录节点的值
		TrieNode nowNode = node.getChildrens()[index];
		if(null == nowNode) {
			nowNode = new TrieNode();
			nowNode.setC(c);
			node.getChildrens()[index] = nowNode;
		}
		//记录途径单词id
		nowNode.getIds().add(id);
		//最后一个节点时，记录词频
		String sub = word.substring(1);
		if(sub.length() == 0) {
			nowNode.setFreq(nowNode.getFreq() + 1);
		}
		//递归计算剩余字符
		addWord(nowNode, sub, id);
	}

	/**
	 * 前缀查询
	 * @param prefix
	 * @return
	 */
	public ArrayList<Integer> prefixSearch(String prefix) {
		ArrayList<Integer> ids = prefixSearch(root, prefix);
		if (null == ids) {
			ids = new ArrayList();
		}
		return ids;
	}

	/**
	 * 词频查询
	 * @param node
	 * @param prefix
	 * @return
	 */
	private ArrayList<Integer> prefixSearch(TrieNode node ,String prefix) {
		//前缀为空，直接返回
		if(null == prefix || prefix.length() == 0) {
			return null;
		}
		//获取当前字符,计算字符所在父节点中的位置
		char c = prefix.charAt(0);
		int index = c - 'a';
		//获取当前节点
		TrieNode nowNode = node.getChildrens()[index];
		//如果当前节点为空，则说明所前缀不存在，前缀为null
		if(null == nowNode) {
			return null;
		}
		//判断当前字符是否为最后一个
		String sub = prefix.substring(1);
		if(sub.length() == 0) {
			//是则:返回结果
			return nowNode.getIds();
		}
		//否则：递归查找
		return prefixSearch(nowNode, sub);
	}

	public int wordFreq(String word) {
		return wordFreq(root, word);
	}

	private int wordFreq(TrieNode node, String word) {
		//word为空，直接返回0
		if(null == word || word.length() == 0) {
			return 0;
		}
		//获取当前字符，计算当前节点位置，获取当前节点
		char c = word.charAt(0);
		int index = c - 'a';
		TrieNode nowNode = node.getChildrens()[index];
		//如果当前节点为空，则说明所查单词不存在，词频为0
		if(null == nowNode) {
			return 0;
		}
		//判断当前节点是否为最后一个
		String sub = word.substring(1);
		if(sub.length() == 0) {
			//是则：返回单词词频
			return nowNode.getFreq();
		}
		//否则：递归查找
		return wordFreq(nowNode, sub);
	}

	/**
	 * 删除单词
	 * @param word
	 */
	public void deleteWord(String word, int id) {
		//单词不存在，则直接返回
		if(!isExsit(word, id)) {
			return;
		}
		deleteWord(root, word, id);
	}

	private void deleteWord(TrieNode node, String word, int id) {
		//word为空，直接返回
		if(null == word || word.length() == 0) {
			return;
		}
		//获取当前字符，计算当前节点在符节点中的位置，获取当前节点
		char c = word.charAt(0);
		int index = c - 'a';
		TrieNode nowNode = node.getChildrens()[index];
		//删除途经单词id
		nowNode.getIds().remove(nowNode.getIds().indexOf(id));
		//计算当单词是否是最后一个
		String sub = word.substring(1);
		if(sub.length() == 0) {
			//是则：如果途径单词ids，则直接删除节点
			if(nowNode.getIds().size() == 0) {
				node.getChildrens()[index] = null;
			}
			//否则：词频减1，
			nowNode.setFreq(nowNode.getFreq() - 1);
		}
		//否则：递归删除
		deleteWord(nowNode, sub, id);
	}

	/**
	 * 判断单词是否存在，存在true，不存在false
	 * @param word
	 * @return
	 */
	public boolean isExsit(String word, int id) {
		return isExsit(root, word, id);
	}

	private boolean isExsit(TrieNode node, String word, int id) {
		//word为空，直接返回false
		if(null == word || word.length() == 0) {
			return false;
		}
		//获取当前字符，计算当前节点在符节点中的位置，获取当前节点
		char c = word.charAt(0);
		int index = c - 'a';
		TrieNode nowNode = node.getChildrens()[index];
		//当前节点不存在，则说明单词不存在，返回false
		if(null == nowNode) {
			return false;
		}
		//计算当前单词是否是最后一个
		String sub = word.substring(1);
		if(sub.length() == 0) {
			//是则：返回true
			return true;
		}
		//否则：递归查找
		return isExsit(nowNode, sub, id);
	}

}