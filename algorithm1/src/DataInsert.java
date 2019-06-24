import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataInsert {
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
                //System.out.println(reader.getRawRecord());
                if(reader.getValues()[0].equals("2004-02-28")){
                csvFileList.add(reader.getValues());
                }
            }
            reader.close();
            if(!csvFileList.get(0)[1].split(":")[1].equals("00")){
                csvFileList.add(0,new String[]{"2004-02-28","08:00:16.00",csvFileList.get(0)[2],csvFileList.get(0)[3],csvFileList.get(0)[4],csvFileList.get(0)[5],csvFileList.get(0)[6],csvFileList.get(0)[7]});
            }
            if(!csvFileList.get(csvFileList.size()-1)[1].split(":")[1].equals("59")){
                csvFileList.add(new String[]{"2004-02-28","23:59:16",csvFileList.get(csvFileList.size()-1)[2],csvFileList.get(csvFileList.size()-1)[3],csvFileList.get(csvFileList.size()-1)[4],
                        csvFileList.get(csvFileList.size()-1)[5],csvFileList.get(csvFileList.size()-1)[6],csvFileList.get(csvFileList.size()-1)[7]});
            }
            for (int i=0;i<csvFileList.size()-1;i++){
                if(csvFileList.get(i)[1].split(":")[1].equals(csvFileList.get(i+1)[1].split(":")[1])){
                    csvFileList.remove(i+1);
                }
            }
            DecimalFormat df = new DecimalFormat("0.0000 ");
            for (int i = 1; i < csvFileList.size(); i++) {
                if(csvFileList.size() == 960 ){
                    break;
                }
                if(!Integer.valueOf((Integer.valueOf(csvFileList.get(i - 1)[1].split(":")[1])+1) % 60 ).equals(Integer.valueOf(csvFileList.get(i)[1].split(":")[1]) % 60)){
                    double data4double=(Double.valueOf(csvFileList.get(i-1)[4])+Double.valueOf(csvFileList.get(i)[4]))/2;
                    String data4=String.valueOf(df.format(data4double));
                    double data5double=(Double.valueOf(csvFileList.get(i-1)[5])+Double.valueOf(csvFileList.get(i)[5]))/2;
                    String data5=String.valueOf(df.format(data5double));
                    double data6double=(Double.valueOf(csvFileList.get(i-1)[6])+Double.valueOf(csvFileList.get(i)[6]))/2;
                    String data6=String.valueOf(df.format(data6double));
                    double data7double=(Double.valueOf(csvFileList.get(i-1)[7])+Double.valueOf(csvFileList.get(i)[7]))/2;
                    String data7=String.valueOf(df.format(data7double));
                    csvFileList.add(i,new String[]{"2004-02-28",String.valueOf(i/60+8)+":"+String.valueOf(i%60)+":16.00",csvFileList.get(0)[2],csvFileList.get(0)[3],data4,data5,data6,data7});
                }
            }
            //            System.out.println(csvFileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFileList;
    }
    public static void writeCSV(LinkedList<String[]> printdata,String csvFilePath) {
        // 定义一个CSV路径
        csvFilePath = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\插值结果\\"+csvFilePath;
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

    public static LinkedList<String[]> readCSV(String csvfile) {
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
                //System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return csvFileList;
    }

    public static void merge(LinkedList<String[]> printdata1, LinkedList<String[]> printdata2, String csvFilePath) {
        csvFilePath = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\插值结果\\"+csvFilePath;
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
            for (int i = 0; i < printdata1.size(); i++) {
                String[] csvContent = new String[printdata1.get(0)[0].split(",").length];
                String[] splits1 = printdata1.get(i)[0].split(",");
                String[] splits2 = printdata2.get(i)[0].split(",");
                csvContent[0] = "2004-02-28";
                csvContent[1] = splits1[1];
                csvContent[2] = splits1[2];
                csvContent[3] = "5";
                csvContent[4] = String.valueOf((Double.valueOf(splits1[4]) + Double.valueOf(splits2[4])) / 2);
                csvContent[5] = String.valueOf((Double.valueOf(splits1[5]) + Double.valueOf(splits2[5])) / 2);
                csvContent[6] = String.valueOf((Double.valueOf(splits1[6]) + Double.valueOf(splits2[6])) / 2);
                csvContent[7] = String.valueOf((Double.valueOf(splits1[7]) + Double.valueOf(splits2[7])) / 2);
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();

            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
   /*     LinkedList<String[]> csvData1 = DataInsert.readCSV("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\插值结果\\4.txt");
        LinkedList<String[]> csvData2 = DataInsert.readCSV("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\插值结果\\6.txt");
        DataInsert.merge(csvData1, csvData2, "5.txt");*/
   /*     String[] filenameList = new String[53];
        for (int i = 1; i < 5; i++) {
            filenameList[i - 1] = String.valueOf(i) + ".txt";
        }
        for (int i = 6; i < 55; i++) {
            filenameList[i - 2] = String.valueOf(i) + ".txt";
        }
        for(int i =0 ;i< filenameList.length;i++){
            if(i != 26){
                LinkedList<String[]> csvFileList = DataInsert.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\" + filenameList[i]);
                DataInsert.writeCSV(csvFileList, filenameList[i]);
            }
        }*/
        LinkedList<String[]> csvFileList = DataInsert.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\15.txt" );
        DataInsert.writeCSV(csvFileList,"15.txt");
  /*      for(int j=1;j<=54;j++){
            filenameList[j-1]="传感器"+String.valueOf(j)+".txt";
        }
        for (int i =0 ;i<filenameList.length;i++) {
            if(i==4|| i==14|| i==48||i==27){

            }
            else {
                LinkedList<String[]> csvFileList = DataInsert.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\完整2-28 8-9点传感器数据\\" + filenameList[i]);
                DataInsert.writeCSV(csvFileList, filenameList[i]);
            }
        }*/
//        LinkedList<String[]> csvFileList = DataInsert.AverageInsert("C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\完整2-28 8-9点传感器数据\\传感器5.txt");
//        DataInsert.writeCSV(csvFileList,"传感器28.txt");
    }
}
