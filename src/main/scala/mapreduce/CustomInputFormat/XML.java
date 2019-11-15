package mapreduce.CustomInputFormat;


import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class XML
{

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {

        Path inputPath = new Path("C:\\Users\\rv00451128\\Desktop\\mylearning\\Project\\sparkcassandraexample\\xml.txt");
        Path outputDir = new Path("C:\\Users\\rv00451128\\Desktop\\mylearning\\data\\XML");

        Configuration conf = new Configuration();
        Job job = new Job(conf, "XML Reader");

        job.setInputFormatClass(XMLInputFormat.class);  //set your own input format class
        // name of driver class
        job.setJarByClass(XML.class);
        // name of mapper class
        job.setMapperClass(XMLMapper.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputDir);

        outputDir.getFileSystem(job.getConfiguration()).delete(outputDir,true);

        job.waitForCompletion(true);
    }
}

