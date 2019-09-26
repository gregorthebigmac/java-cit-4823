package stubs;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
  	throws IOException, InterruptedException {
        String line = value.toString();
		for (String word : line.split("\\W+")) {
			if (word.length() > 0) {
				context.write(new Text(word), new IntWritable);
			}
		}
  }
}