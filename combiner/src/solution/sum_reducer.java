package solution;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class sum_reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	@Override
	public void reduce(Text key, Iterable<IntWritable> key_vals, Context context) throws IOException, InterruptedException {
		int word_count = 0;
		for (IntWritable value : key_vals) {
			word_count += value.get();
		}
		context.write(key, new IntWritable(word_count));
	}
}