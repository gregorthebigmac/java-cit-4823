package solution;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class log_month_mapper extends Mapper<LongWritable, Text, Text, Text> {
    public static List<String> months = Arrays.asList("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(" ");
        if (fields.length > 3) {
            String ip_addr = fields[0];
            String field_date = fields[3].split("/");
            if (field_date.length > 1) {
                String field_month = field_date[1];
                if (months.contains(field_month))
                    context.write(new Text(ip_addr), new Text(field_month));
            }
        }
    }
}
