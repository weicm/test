package cn.julong;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Think on 2016/6/8.
 */
public class MainTest {
	public static void main(String[] args) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date start = c.getTime();
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		Date end = c.getTime();
		System.out.println(start.getTime()+"--"+end.getTime());
	}

}
