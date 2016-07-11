package cn.julong.json;

/**
 * json字符串格式化工具,针对压缩过的json串
 * Created by weicm on 2016/5/31.
 */
public class JSONUtils {
	//单位缩进字符串
	private static String SPACE = "....";

	public static String formatJson(String json, String indent) {
		SPACE = indent;
		return formatJson(json);
	}

	/**
	 * 返回格式化JSON字符串。
	 *
	 * @param json 未格式化的JSON字符串。
	 * @return 格式化的JSON字符串。
	 */
	public static String formatJson(String json) {
		StringBuffer result = new StringBuffer();

		int length = json.length();
		int number = 0;
		char key = 0;

		//遍历输入字符串。
		for (int i = 0; i < length; i++) {
			//1、获取当前字符。
			key = json.charAt(i);
			//如果key为冒号
			if (key == ':') {
				result.append(" " + key + " ");
				continue;
			}

			//2、如果当前字符是前方括号、前花括号做如下处理：
			if ((key == '[') || (key == '{')) {

				//（1）打印：当前字符。
				result.append(key);

				//（2）前方括号、前花括号，的后面必须换行。打印：换行。
				result.append('\n');

				//（3）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
				number++;
				result.append(indent(number));

				//（4）进行下一次循环。
				continue;
			}

			//3、如果当前字符是后方括号、后花括号做如下处理：
			if ((key == ']') || (key == '}')) {
				//（1）后方括号、后花括号，的前面必须换行。打印：换行。
				result.append('\n');

				//（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
				number--;
				result.append(indent(number));

				//（3）打印：当前字符。
				result.append(key);

				//（4）继续下一次循环。
				continue;
			}

			//4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
			if ((key == ',')) {
				result.append(key);
				result.append('\n');
				result.append(indent(number));
				continue;
			}

			//5、打印：当前字符。
			result.append(key);
		}

		return result.toString();
	}

	/**
	 * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
	 *
	 * @param number 缩进次数。
	 * @return 指定缩进次数的字符串。
	 */
	private static String indent(int number) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < number; i++) {
			result.append(SPACE);
		}
		return result.toString();
	}
}
