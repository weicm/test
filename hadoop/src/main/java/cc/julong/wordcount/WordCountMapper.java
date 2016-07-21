package cc.julong.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Think on 2016/7/19.
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] words = value.toString().split("\\s+");
		for (int i = 0; i < words.length; i++) {
			context.write(new Text(words[i]), new IntWritable(1));
		}
	}
}
