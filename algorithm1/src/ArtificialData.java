import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class ArtificialData {
    public static LinkedList<String[]> AverageInsert(String csvfile){
        LinkedList<String[]> csvFileList = new LinkedList<String[]>();
        try {
            // 用来保存数据

            // 定义一个CSV路径
            String csvFilePath = csvfile;
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ' ', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
//            reader.readHeaders();
            // 逐行读入除表头的数据
            while (reader.readRecord()) {
                csvFileList.add(reader.getValues());

            }
            reader.close();
            for(int i=0;i<csvFileList.size();i++){
                String[] databuf=csvFileList.get(i)[0].split(",");
                DecimalFormat df = new DecimalFormat( "0.0000 ");
//                if(i%2==0){
                    String[] buf=new String[8];
                    buf[0]=databuf[0];
                    buf[1]=databuf[1];
                    buf[2]=databuf[2];
                    buf[3]=databuf[3];
                    buf[4]=String.valueOf(df.format(Double.valueOf(databuf[4])-0.2));
                    buf[5]=String.valueOf(df.format(Double.valueOf(databuf[5])-0.2));
                    buf[6]=String.valueOf(df.format(Double.valueOf(databuf[6])-2.0));
                    buf[7]=String.valueOf(df.format(Double.valueOf(databuf[7])-0.02));
                    csvFileList.set(i,buf);
//                }
//                else {
//                    String[] buf=new String[8];
//                    buf[0]=databuf[0];
//                    buf[1]=databuf[1];
//                    buf[2]=databuf[2];
//                    buf[3]=databuf[3];
//                    buf[4]=String.valueOf(df.format(Double.valueOf(databuf[4])+0.2));
//                    buf[5]=String.valueOf(df.format(Double.valueOf(databuf[5])+0.2));
//                    buf[6]=String.valueOf(df.format(Double.valueOf(databuf[6])+2.0));
//                    buf[7]=String.valueOf(df.format(Double.valueOf(databuf[7])+0.02));
//                    csvFileList.set(i,buf);
//                }
            }
//            System.out.println("111");
        } catch (IOException e) {
            e.printStackTrace();

        }
        return csvFileList;
    }

    public static void writeCSV(LinkedList<String[]> printdata,String csvFilePath) {
        // 定义一个CSV路径
        csvFilePath = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\人造8-9点插值结果\\"+csvFilePath;
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(csvFilePath ), "UTF-8" );//encoding 没用

            BufferedWriter bw = new BufferedWriter(ow);
            CsvWriter csvWriter = new CsvWriter(bw, ',');//add BOM info
            bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            //   CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            // 写内容
            //将集合内的所有数据输出到csv文件中
            for (int i = 0; i < printdata.size(); i++) {
                String[] csvContent = new String[printdata.get(i).length];
                for(int j=0;j<printdata.get(i).length;j++) {
                    csvContent[j] = printdata.get(i)[j];
                }
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();

            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        String[] filenameList = new String[54];
        for(int j=1;j<=54;j++){
            filenameList[j-1]="传感器"+String.valueOf(j)+".txt";
        }
        String[] addFileList=new String[54];
        for(int j=163;j<=216;j++){
            addFileList[j-163]="传感器"+String.valueOf(j)+".txt";
        }
        for (int i =0 ;i<filenameList.length;i++) {
            if(i==4){
                LinkedList<String[]> csvFileList = ArtificialData.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\传感器5-空补4的倒置.txt" );
                ArtificialData.writeCSV(csvFileList, addFileList[i]);
            }
            else if(i==14){
                LinkedList<String[]> csvFileList = ArtificialData.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\传感器15-空补14和16平均.txt" );
                ArtificialData.writeCSV(csvFileList, addFileList[i]);
            }
            else if(i==48){
                LinkedList<String[]> csvFileList = ArtificialData.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\传感器49-少取48和50平均.txt" );
                ArtificialData.writeCSV(csvFileList, addFileList[i]);
            }
            else if(i==27){
                LinkedList<String[]> csvFileList = ArtificialData.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\传感器28-光照数据缺失补27光照.txt" );
                ArtificialData.writeCSV(csvFileList, addFileList[i]);
            }
            else {
                LinkedList<String[]> csvFileList = ArtificialData.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\" + filenameList[i]);
                ArtificialData.writeCSV(csvFileList, addFileList[i]);
            }
        }
    }
}
