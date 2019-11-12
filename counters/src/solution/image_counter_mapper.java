package solution;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class image_counter_mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] fields = value.toString().split("\"");
		if (fields.length > 1) {
			String request = fields[1];
			fields = request.split(" ");
			if (fields.length > 1) {
				String filename = fields[1].toLowerCase();
				if (filename.endsWith(".jpg"))
					context.getCounter("image_counter", "jpg").increment(1);
				else if (filename.endsWith(".gif"))
					context.getCounter("image_counter", "gif").increment(1);
				else
					context.getCounter("image_counter", "other").increment(1);
			}
		}
	}
}