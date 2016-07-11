package cn.julong;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Think on 2016/6/8.
 */
public class MainTest {
	public static void main(String[] args) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(format.format(new Date()));
	}

}
