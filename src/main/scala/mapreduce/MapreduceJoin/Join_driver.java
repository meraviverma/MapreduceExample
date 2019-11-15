package mapreduce.MapreduceJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class Join_driver {
    public static void main(String[] args) throws IOException,ClassNotFoundException,InterruptedException{

        Path EmpInfoPath=new Path("");
        Path addressPath=new Path("");

        Path Out_Directory = new Path("");

        Configuration c1=new Configuration();

        Job job=new Job(c1,"Join_reduceside");

        //name of driver class
        job.setJarByClass(Join_driver.class);

        //name of Mapper1 class
        job.setMapperClass(Emp_Mapper.class);;

        //name of Mapper2 class
        job.setMapperClass(Location_Mapper.class);

        //name of Reducer class
        job.setReducerClass(Join_Reducer.class);

        job.setMapOutputValueClass(Text.class);
        job.setMapOutputKeyClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        MultipleInputs.addInputPath(job,EmpInfoPath,TextInputFormat.class,Emp_Mapper.class);
        MultipleInputs.addInputPath(job,addressPath,TextInputFormat.class,Location_Mapper.class);

        FileOutputFormat.setOutputPath(job,Out_Directory);
        Out_Directory.getFileSystem(job.getConfiguration()).delete(Out_Directory,true);

        System.exit(job.waitForCompletion(true) ? 0 : 1);


    }
}
