package cc.julong.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Think on 2016/7/19.
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		Iterator<IntWritable> it = values.iterator();
		while (it.hasNext()) {
			int c =  it.next().get();
			count += c;
		}
		context.write(key, new IntWritable(count));
	}
}
