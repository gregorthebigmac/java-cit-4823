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

public class image_counter extends Configured implements Tool {
	@Override public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: image_counter [input/dir] [output/dir]");
			return -1;
		}
		Job job = Job.getInstance();
		job.setJarByClass(image_counter.class);
		job.setJobName("image_counter");
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(image_counter_mapper.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setNumReduceTasks(0);
		
		boolean success = job.waitForCompletion(true);
		if (success) {
			long jpg = job.getCounters().findCounter("image_counter", "jpg").getValue();
			long gif = job.getCounters().findCounter("image_counter", "gif").getValue();
			long other = job.getCounters().findCounter("image_counter", "other").getValue();
			System.out.println("jpg = " + jpg);
			System.out.println("gif = " + gif);
			System.out.println("other = " + other);
			return 0;
		}
		else return 1;
	}
	public static void main(String[] args) throws Exception {
		int exit_code = ToolRunner.run(new Configuration(), new image_counter(), args);
		System.exit(exit_code);
	}
}
