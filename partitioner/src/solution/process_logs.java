package solution;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class process_logs {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: process_logs <input_dir> <output_dir>");
            System.exit(-1);
        }
        
        Job job = Job.getInstance();
        job.setJarByClass(process_logs.class);
        job.setJobName("Process Logs");
        
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        job.setMapperClass(log_month_mapper.class);
        job.setReducerClass(count_reducer.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        
        job.setNumReduceTasks(12);
        job.setPartitionerClass(month_partitioner.class);
        boolean success = job.waitForCompletion(true);
        if (success)
            System.exit(0);
        else System.exit(1);
    }
}
