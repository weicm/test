import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Think on 2016/7/12.
 */
public class DateTest {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 0, 0);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 5);
		long startHour = calendar.getTime().getTime();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 14);
		long endHour = calendar.getTime().getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		System.out.println(startHour + "----" + endHour);
		System.out.println(format.format(new Date(startHour)));
		System.out.println(format.format(new Date(endHour)));


	}
}
