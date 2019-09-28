package solution;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String file_line = value.toString();
		for (String word : file_line.split("\\W+")) {
			if (word.length() > 0) {
				String first_letter = word.substring(0, 1);
				context.write(new Text(first_letter), new IntWritable(word.length()));
			}
		}
	}
}
