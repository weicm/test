package cc.julong.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Think on 2016/7/19.
 */
public class WordCountDriver extends Configured implements Tool {
	public int run(String[] strings) throws Exception {
		String userName = getConf().get("user.name");
		Job job = Job.getInstance(getConf(), "word count");
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		String inputpath = getConf().get("inputpath");
		String outputpath = getConf().get("outputpath");
		if(null == inputpath || null == outputpath) {
			System.out.println("Useage: yarn jar <jarpath> <-Dinputpath=path> <-Dinputpath=path>");
			System.exit(1);
		}
		FileInputFormat.addInputPath(job, new Path(inputpath));
		FileSystem system = FileSystem.get(getConf());
		boolean exists = system.exists(new Path(outputpath));
		if(exists) {
			boolean delete = system.delete(new Path(outputpath), true);
		}
		FileOutputFormat.setOutputPath(job, new Path(outputpath));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("user.name", "hdfs");
		int res = ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		System.exit(res);
	}
}
