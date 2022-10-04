package com.liu.cases;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author 刘老师
 *
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 *
 */
public class MapReduceWordCountCase {

	/**
	 * I love you and you love me.
	 * You are the best one in my heart.
	 */
	private static class TokenizerMapper extends Mapper
			<				// 
				Object, 	// 输入 Key
				Text, 		// 输入 Value
				Text, 		// 输出 Key
				IntWritable	// 输出 Value
			> {

		// 单位 1 累加器
		private final static IntWritable one = new IntWritable(1);

		// 文本
		private Text word = new Text();

		/**
		 *
		 * @param key
		 * @param value
		 * @param context
		 *
		 */
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(
					value
						.toString()
						.replace("“", " ")
						.replace("”", " ")
						.replace("\"", " ")
						.replace(".", " ")
						.replace("’", " ")
						.replace("‘", " ")
						.replace(",", " ")
						.replace(";", " ")
						.replace("-", " ")
						.replace("(", " ")
						.replace(")", " ")
						.replace(">", " ")
						.replace("<", " ")
						.replace("=", " ")
						.replace("!", " ")
						.replace("\\", " ")
						.replace("/", " ")
						.replace("|", " ")
						.replace("{", " ")
						.replace("}", " ")
						.replace("[", " ")
						.replace("]", " ")
						.replace("_", " ")
						.replace(":", " ")
						.replace("?", " ")
						.replace("@", " ")
						.replace("#", " ")
						.replace("%", " ")
						.replace("&", " ")
						.replace("*", " ")
						.replace("$", " ")
						.trim()
					);
			
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				
				context.write(word, one);
			}
		}
	}

	private static class IntSumReducer extends Reducer
			<					//
				Text, 			// 输入 Key
				IntWritable, 	// 输入 Value
				Text, 			// 输出 Key
				IntWritable		// 输出 Value
			> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {

			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(MapReduceWordCountCase.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
