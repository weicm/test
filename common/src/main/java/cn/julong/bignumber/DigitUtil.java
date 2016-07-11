package cn.julong.bignumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 格式化数字类型为字符串，避免出现科学计数法格式显示
 * Created by weicm on 2016/7/4.
 */
public class DigitUtil {
	private static final Logger LOG = LoggerFactory.getLogger(DigitUtil.class);

	private static final Map<Integer, DecimalFormat> formats = new HashMap();
	static {
		//保留整数
		formats.put(0, new DecimalFormat("#"));
		//保留1位小数
		formats.put(1, new DecimalFormat("#.#"));
		//保留2位小数
		formats.put(2, new DecimalFormat("#.##"));
		//为了精确度，保留10位小数
		formats.put(10, new DecimalFormat("#.##########"));
	}

	/**
	 * 格式化大数字为字符串
	 * @param d 用来格式化的数字
	 * @param pattern 格式模式：0整数、1一位小数、2两位小数
	 * @return
	 */
	public static String format(Object d, Integer pattern) {
		if(null == pattern || null == formats.get(pattern)) {
			LOG.error("Pattern is null or pattern is not exsits!");
			return null;
		}
		return formats.get(pattern).format(d);
	}

	/**
	 * 格式化为整数，去掉小数部分
	 * @param d 用来格式化的数字
	 * @return
	 */
	public static String format2Int(Object d) {
		return formats.get(0).format(d);
	}

	/**
	 * 格式化为小数，保留两位小数
	 * @param d 用来格式化的数字
	 * @return
	 */
	public static String format2Decimal(Object d) {
		return formats.get(10).format(d);
	}

	public static String add(String a, String b) {
		return format2Decimal(new BigDecimal(a).add(new BigDecimal(b)));
	}

	public static void main(String[] args) {
		String add = add("111.111", "222.222");
		System.out.println(add);
	}
}
