package mapreduce.MapreduceJoin;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Join_Reducer extends Reducer<Text,Text,Text,Text> {

    //1 [{Emp,Jack} {Address,Paris}]
    public void reduce(Text EmployeeID,Iterable<Text> Info,Context context) throws IOException,InterruptedException{

        List<String>  EmployeeList=new ArrayList<String>(); //jack
        List<String> AddressList=new ArrayList<String>();   //London

        //creating object of iterator class to iterator over list
        Iterator<Text> Itr =Info.iterator();

        while(Itr.hasNext()){

            Text data=Itr.next(); // data = Emp,Jack

            String NewRecord[] = data.toString().split(","); //NewRecord = [{Emp} {Jack}]

            //check condition on where to put the new incoming new record

            if(NewRecord[0].equalsIgnoreCase("Emp")){       //If the incoming record contains Emp then put record into EmployeeList

                EmployeeList.add(NewRecord[1]);

            }else if(NewRecord[0].equalsIgnoreCase("Address")){ //If the incoming record contains Address then put record into AddressList

                AddressList.add(NewRecord[1]);
            }
        }
        //In inner join for particular key our both list should not be empty.
        if(!EmployeeList.isEmpty() && !AddressList.isEmpty()){

            for(String record:EmployeeList){

                for(String address : AddressList){

                    context.write(new Text(EmployeeID),new Text(record + "," + address));

                }
            }
        }
    }

}
