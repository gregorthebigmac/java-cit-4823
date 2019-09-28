package stubs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class AvgWordLength {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: AvgWordLength <input dir> <output dir>");
			System.exit(-1);
		}
		Job job = new Job();
		job.setJarByClass(AvgWordLength.class);
		job.setJobName("Average Word Length");
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(LetterMapper.class);
		job.setReducerClass(AverageReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		boolean success = job.waitForCompletion(true);
		if (success) { System.exit(0); }
		else { System.exit(1); }
	}
}
