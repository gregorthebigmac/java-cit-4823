package solution;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class word_count_driver extends Configured implements Tool {
	@Override
	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: word_count_driver <path/to/input/dir> <path/to/output/dir");
			return -1;
		}
		Job job = Job.getInstance(getConf());
		job.setJarByClass(word_count_driver.class);
		job.setJobName("Word Count Driver");
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(word_mapper.class);
		job.setReducerClass(sum_reducer.class);
		
		job.setCombinerClass(sum_reducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		if (job.getCombinerClass() == null)
			throw new Exception("Combiner not set!");
		
		boolean success = job.waitForCompletion(true);
		if (success) { return 0; }
		else { return 1; }
	}
	public static void main(String[] args) throws Exception {
		int exit_code = ToolRunner.run(new Configuration(), new word_count_driver(), args);
		System.exit(exit_code);
	}
}
