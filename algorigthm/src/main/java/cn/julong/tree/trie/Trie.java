package cn.julong.tree.trie;


import java.util.ArrayList;
import java.util.List;

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
	public boolean deleteWord(String word, int id) {
		//单词不存在，则直接返回
		return deleteWord(root, word, id);
	}

	private boolean deleteWord(TrieNode node, String word, int id) {
		//word为空，直接返回
		if(null == word || word.length() == 0) {
			return false;
		}
		//获取当前字符，计算当前节点在符节点中的位置，获取当前节点
		char c = word.charAt(0);
		int index = c - 'a';
		TrieNode nowNode = node.getChildrens()[index];
		String sub = word.substring(1);
		//计算当单词是否是最后一个并且指定id的节点存在
		if(sub.length() == 0 && node.getIds().indexOf(id) > -1) {
			//是则：如果途径单词ids.size=0，则直接删除该子节点
			if(nowNode.getIds().size() == 0) {
				node.getChildrens()[index] = null;
			}else {
				//否则：当前节点词频减1，删除途径id
				nowNode.setFreq(nowNode.getFreq() - 1);
				nowNode.getIds().remove(nowNode.getIds().indexOf(id));
			}
			return true;
		}
		//递归删除子节点是否成功
		boolean delSuccess = deleteWord(nowNode, sub, id);
		if(delSuccess) {
			//成功：删除当前节点(删除途径id)，返回true
			nowNode.getIds().remove(nowNode.getIds().indexOf(id));
			return true;
		}
		//否则：删除失败，所删节点不存在，返回false
		return false;
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
		//计算当前单词是否是最后一个，并且id在结束节点的途径节点ids中存在
		String sub = word.substring(1);
		if(sub.length() == 0 && node.getIds().indexOf(id) > -1) {
			//是则：返回true
			return true;
		}
		//否则：递归查找
		return isExsit(nowNode, sub, id);
	}

	/**
	 * 先续遍历
	 * @return
	 */
	public ArrayList<String> preorderIterator() {
		ArrayList<String> strs = new ArrayList<String>();
		preorderItrator(root, strs, "");
		return strs;
	}

	private void preorderItrator(TrieNode node, List<String> strs, String parent) {
		//当前节点为空，直接返回
		if(null == node) {
			return;
		}
		//判断当前节点是否是单词，是则与祖先拼接，加入结果strs中
		parent += node.getC();
		if(node.getFreq() > 0) {
			strs.add(parent);
		}
		//循环递归子节点
		TrieNode[] childrens = node.getChildrens();
		for (int i=0; i<childrens.length; i++) {
			preorderItrator(childrens[i], strs, parent);
		}
	}

}
