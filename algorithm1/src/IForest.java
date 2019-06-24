
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
//import sun.tools.java.Parser;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.*;

import static java.lang.Math.log;

public class IForest {

    private static List<Node> nodeList = null;
    private ArrayList<double[]> noI = new ArrayList<double[]>(); //获得每个元素的值与对应的层数
    private ArrayList<double[]> buffer = new ArrayList<double[]>();  //用于构造空满二叉树的基础结构
    private int datasize;
    private int leng=5;    //层数
    private final static int TwoSix = 64;
    private static int k =15;
    private static class Node { //构造节点结构
        Node leftChild;
        Node rightChild;
        ArrayList<double[]> data;

        Node(ArrayList<double[]> newData) {
            leftChild=null;
            rightChild=null;
            data= newData;
        }
    }

    public IForest(int datasize) {
        this.datasize = datasize;
    }

    public void createBinTree(ArrayList<double[]> array, StringBuilder output, StringBuilder outbuf) {
        nodeList = new ArrayList<Node>();
        int length=(int) Math.ceil(log(datasize)/log(2));
        double random ;
        for (int nodeIndex = 0; nodeIndex < Math.pow(2,length)-1; nodeIndex++) {
            nodeList.add(new Node(buffer));  // 先构造一个有31个节点的满二叉树，个数为2^5-1
        }
        nodeList.set(0,new Node(array));     //将第一层的父节点设为初始数组
        for(int i =0;i<Math.pow(2,length-1)-1;i++){              //所有父节点，31-16
            if(nodeList.get(i).data.size()==0){   //当节点无数据时看作是空

            }
            else if(nodeList.get(i).data.size()==1){   //当节点只有一个数据时说明被隔离
                double x = log((double)i)/log((double)2);
                int y =(int)x;                             //获得当前层数
                String z =Arrays.toString(nodeList.get(i).data.get(0))+" "+y;   //获得被隔离数据的值和层数放到outbuf里
                //  System.out.println(z);
                outbuf.append(z);
                outbuf.append(";");
                noI.add(nodeList.get(i).data.get(0));            //获得被隔离数据的值输出到noI中
            }
            else {
                HashSet<Double> hashSet1= new HashSet<Double>();
                HashSet<Double> hashSet2= new HashSet<Double>();
                HashSet<Double> hashSet3= new HashSet<Double>();
                HashSet<Double> hashSet4= new HashSet<Double>();
                for (int s = 0; s < nodeList.get(i).data.size(); s++) {
                    hashSet1.add(nodeList.get(i).data.get(s)[0]);
                    hashSet2.add(nodeList.get(i).data.get(s)[1]);
                    hashSet3.add(nodeList.get(i).data.get(s)[2]);
                    hashSet4.add(nodeList.get(i).data.get(s)[3]);
                }
                if (hashSet1.size() == 1&&hashSet2.size()==1&&hashSet3.size()==1) {

                        double x = log((double) i) / log((double) 2);
                        int y = (int) x;                             //获得当前层数
                        String z = Arrays.toString(nodeList.get(i).data.get(0)) + " " + y;   //获得被隔离数据的值和层数放到outbuf里
                        //  System.out.println(z);
                        outbuf.append(z);
                        outbuf.append(";");
                        noI.add(nodeList.get(i).data.get(0));//获得被隔离数据的值输出到noI中

                }
                else {
                    Random rand = new Random();
                    if (nodeList.get(i).data.size() == 0) {   //当节点无数据时看作是空
                    } else {
                        int num = rand.nextInt(nodeList.get(i).data.get(0).length);
                        ArrayList<double[]> buffer1 = new ArrayList<double[]>();   //用于存放小于等于分割点的值
                        ArrayList<double[]> buffer2 = new ArrayList<double[]>();   //用于存放大于分割点的值
                        double[] randElementArray = new double[nodeList.get(i).data.size()];
                        for (int k = 0; k < nodeList.get(i).data.size(); k++) {  //从所有属性值中随机一个属性作为分割点
                            randElementArray[k] = nodeList.get(i).data.get(k)[num];
                        }
                        List<Double> BUF = new ArrayList<Double>();
                        for (int m = 0; m < randElementArray.length; m++) {
                            BUF.add(randElementArray[m]);
                        }
                        Collections.sort(BUF);
                        random = BUF.get(0) + Math.random() * (BUF.get(BUF.size() - 1) - BUF.get(0));
//                    System.out.println(BUF.get(BUF.size()-1));
                        for (int j = 0; j < nodeList.get(i).data.size(); j++) {  //分割
                            if (nodeList.get(i).data.get(j)[num] < random) {
                                buffer1.add(nodeList.get(i).data.get(j));
                            } else {
                                buffer2.add(nodeList.get(i).data.get(j));
                            }
                        }
                        nodeList.set(i * 2 + 1, new Node(buffer1));     //左子树设为buffer1的值
                        nodeList.set(i * 2 + 2, new Node(buffer2));     //右子树设为buffer2的值
                        nodeList.get(i).leftChild = nodeList.get(i * 2 + 1);  //设置当前树结构的左子树
                        nodeList.get(i).rightChild = nodeList.get(i * 2 + 2); //设置当前树结构的右子树
                    }
                }
            }
        }
        for(int i=0;i<nodeList.get(0).data.size();i++){
            String a = outbuf.toString();
            if(!a.equals("")) {
                String[] b = a.split(";");
                int result = 0;
                for (int k = 0; k < b.length; k++) {
                    if (Arrays.toString(nodeList.get(0).data.get(i)).equals(b[k].substring(0, b[k].length() - 2))) {
                        String z = "被隔离的元素是" + Arrays.toString(nodeList.get(0).data.get(i)) + "层数是" + b[k].substring(b[k].length() - 1, b[k].length());
                        output.append(z);
                        output.append(";");
                        result++;
                    }
                }
                if (result == 0) {
                    String z = "未被隔离的元素是" + Arrays.toString(nodeList.get(0).data.get(i)) + "层数是"+String.valueOf(length);
                    output.append(z);
                    output.append(";");
                }
            }
            else {
                String z = "未被隔离的元素是" + Arrays.toString(nodeList.get(0).data.get(i)) + "层数是"+String.valueOf(length);
                output.append(z);
                output.append(";");
            }
        }
        noI=new ArrayList<double[]>();
    }

    public static void readCSV(String csvfile,List<String[]> csvdata) {
        try {
            // 用来保存数据
            ArrayList<String[]> csvFileList = new ArrayList<String[]>();
            // 定义一个CSV路径
            String csvFilePath = csvfile;
            // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);
            CsvReader reader = new CsvReader(csvFilePath, ' ', Charset.forName("UTF-8"));
            // 跳过表头 如果需要表头的话，这句可以忽略
//            reader.readHeaders();
            // 逐行读入除表头的数据

            while (reader.readRecord()) {
                if (csvFileList.size() >= TwoSix*k) {
                    break;
                }
                //   System.out.println(reader.getRawRecord());
                csvFileList.add(reader.getValues());
            }
            reader.close();
            Object[] data =new Object[csvFileList.size()];
            // 遍历读取的CSV文件
            for (int row = TwoSix * (k-1); row < csvFileList.size(); row++) {
                    String array = csvFileList.get(row)[0];
                    String[] element = array.split(",");
                    String[] buf = new String[element.length-4];
                    for (int k = 4 ; k<element.length;k++){
//                        System.out.println(element[k]);
                        buf[k-4]=element[k];
                    }
                    csvdata.add(buf);
//                    System.out.println("------------>" + array[j]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCSV(ArrayList<String[]> printdata,int datasize) {
        // 定义一个CSV路径
        String csvFilePath = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\结果存放\\第三次实验\\"+"result"+
                String.valueOf(TwoSix*(k-1)+1)+"-"+String.valueOf(TwoSix*k)
                +".csv";
        try {
            // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);
            OutputStreamWriter ow = new OutputStreamWriter( new FileOutputStream(csvFilePath ), "UTF-8" );//encoding 没用

            BufferedWriter bw = new BufferedWriter(ow);
            CsvWriter csvWriter = new CsvWriter(bw, ',');//add BOM info
            bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            //   CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));
            // 写表头
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=1;i<=datasize;i++){
                String s ="传感器"+String.valueOf(i)+",";
                stringBuilder.append(s);
            }
           String buff = stringBuilder.toString();
            String[] csvHeaders = buff.split(",");
            csvWriter.writeRecord(csvHeaders);
            // 写内容
            int m=0;
            //将集合内的所有数据输出到csv文件中
            for (int i = 0; i < printdata.size(); i++) {
                String[] csvContent = new String[printdata.get(i).length];
                for(int j=0;j<printdata.get(i).length;j++) {
                    csvContent[m] = printdata.get(i)[j];
                    m++;
                }
                csvWriter.writeRecord(csvContent);
                m=0;
            }
            //计算每个属性隔离层数的平均值
            String[] csvContent1 = new String[printdata.get(0).length];
            for (int i=0;i<printdata.get(0).length;i++){

                double k = 0;
                for (int j=0;j<printdata.size();j++){
                    k+=Double.parseDouble(printdata.get(j)[i].substring(printdata.get(j)[i].length()-1,printdata.get(j)[i].length())) ;
                }
                double s=k/printdata.size();
                csvContent1[m]=String.valueOf(s);
                m++;
            }
            String[] avgres = new String[printdata.get(0).length/3];
            for(int i=0 ; i<datasize ;i++){
                avgres[i]=String.valueOf((Double.parseDouble(csvContent1[i])+Double.parseDouble(csvContent1[i+avgres.length])+Double.parseDouble(csvContent1[i+(2*avgres.length)]))/3);
            }
            int datasizeleft=datasize-1;
            double innum=2*(double)datasizeleft/datasize;
            double Cm=2*(Math.log(datasizeleft)+0.5772156649)-innum;
            String[] Sxm =  new String[avgres.length];
            for(int i=0;i<avgres.length;i++){
                Sxm[i]=Double.toString( Math.pow(2,-(Double.valueOf(avgres[i])/Cm)));
            }
            csvWriter.writeRecord(avgres);
            csvWriter.writeRecord(Sxm);
            csvWriter.close();

            System.out.println("--------CSV文件已经写入--------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        String csvfile=System.getProperty("csvfile"); //获取系统变量中的csvfile值
        List<Frequency[]> alldata = new ArrayList<>();
//        String[] filenameList = new String[54];
//        for(int j=1;j<=54;j++){
//            if(j==5){
//                filenameList[j - 1]="传感器5-空补4的倒置.txt";
//            }
//            else if(j==15){
//                filenameList[j - 1]="传感器15-空补14和16平均.txt";
//            }
//            else if(j==28){
//                filenameList[j - 1]="传感器28-光照数据缺失补27光照.txt";
//            }
//            else if(j==49){
//                filenameList[j - 1]="传感器49-少取48和50平均.txt";
//            }
//            else {
//                filenameList[j - 1] = "传感器" + String.valueOf(j) + ".txt";
//            }
//        }
//        for(int j=1;j<=54;j++){
//                filenameList[j - 1] = "传感器" + String.valueOf(j) + ".csv";
//        }
        String[] filenameList =new String[54];
        for(int j=1;j<=filenameList.length;j++){
            filenameList[j-1]=String.valueOf(j)+".txt";
        }
/*        for (int j = 217; j <= 432; j++) {
            filenameList[j-1]="传感器"+String.valueOf(j-216)+" - 副本.txt";
        }
        for (int j = 433; j <= 648; j++) {
            filenameList[j-1]="传感器"+String.valueOf(j-432)+" - 副本 (2).txt";
        }
        for (int j = 649; j <= 864; j++) {
            filenameList[j-1]="传感器"+String.valueOf(j-648)+" - 副本 (3).txt";
        }
        for (int j = 865; j <= 1080; j++) {
            filenameList[j-1]="传感器"+String.valueOf(j-864)+" - 副本 (4).txt";
        }*/
        for(int b =0 ;b<filenameList.length;b++) {
            /*新建文件夹\*/
            String csvfile = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\8-24数据集\\插值结果\\"+filenameList[b];//CSV文件地址
//        String[] printData = new String[7];
            List<String[]> csvdata = new ArrayList<>();                  //获取输入值
            readCSV(csvfile, csvdata);           //读取CSV文件将数值传到array中
            System.out.println(csvdata.size());
            Frequency[] frequencies = new Frequency[csvdata.get(0).length];
            for (int k = 0; k < csvdata.get(0).length; k++) {
                Complex[] a = new Complex[csvdata.size()];
                for (int i = 0; i < csvdata.size(); i++) {
                    a[i] = new Complex(Double.valueOf(csvdata.get(i)[k]), 0);
                }
//                FFT aaa = new FFT();
                Complex[] C = FFT.fft(a);
                double count = 0; //计算模的总和
                double max = 0; //计算最大值
                double[] pervariance = new double[C.length]; //用于方差计算的平方总和
                for (int i = 1; i < C.length; i++) {
                    double model = C[i].model(); //求得频域特征的模
                    pervariance[i] = model;
//            System.out.println(model);
                    count += model;
                    if (model > max) {
                        max = model;
                    }
                }
                double avg = count / C.length;
                double variancecount = 0;
                for (int i = 0; i < pervariance.length; i++) {
                    pervariance[i] = (pervariance[i] - avg) * (pervariance[i] - avg);
                    variancecount += pervariance[i];
                }
                double variance = (1 / (double) pervariance.length) * variancecount;
                System.out.println("max=" + max + "avg=" + avg + "variance=" + variance);
                frequencies[k]=new Frequency(max,avg,variance);
            }
            alldata.add(frequencies);
        }
            double[][] avgarray = new double[alldata.size()][alldata.get(0).length];
            double[][] maxarray = new double[alldata.size()][alldata.get(0).length];
            double[][] vararray = new double[alldata.size()][alldata.get(0).length];
            for (int i =0;i<alldata.size();i++){
                for(int j=0;j<alldata.get(0).length;j++){
                    avgarray[i][j] = alldata.get(i)[j].getAVG();
                    maxarray[i][j] = alldata.get(i)[j].getMAX();
                    vararray[i][j] = alldata.get(i)[j].getVariance();
                }
            }
        ArrayList<String[]> allout=new ArrayList<>();  //创建一个String数组集合用于存放每次输出的元素层数的字符串
        ArrayList<double[]> avgarr = new ArrayList<>();
        ArrayList<double[]> maxarr = new ArrayList<>();
        ArrayList<double[]> vararr = new ArrayList<>();
        for (int i =0;i<filenameList.length;i++) {
            avgarr.add(avgarray[i]);
            maxarr.add(maxarray[i]);
            vararr.add(vararray[i]);
        }
        long startTime=System.nanoTime();   //获取开始时间
        IForest binTree = new IForest(filenameList.length);
        for(int x=0; x<100 ; x++ ) {
                StringBuilder output = new StringBuilder();   //存放所有输出数据
                StringBuilder outbuf = new StringBuilder();   //存放被提前隔离的数据
                binTree.createBinTree(avgarr, output, outbuf);  //隔离树的实现
                binTree.createBinTree(maxarr, output, outbuf);
                binTree.createBinTree(vararr, output, outbuf);
                String output1 = output.toString();            //StringBuilder转成String
                String[] output2 = output1.split(";");  //将String以逗号作为分隔符生成String数组
                allout.add(output2);                           //集合里添加生成的数组

        }
        IForest.writeCSV(allout,filenameList.length);                                   //写入CSV文件中
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)/1000000+"ms");
        }
}
//        ArrayList<String[]> allout=new ArrayList<String[]>();  //创建一个String数组集合用于存放每次输出的元素层数的字符串
//        for(int x=0; x<10 ; x++ ){                  //假定集成10次
//            StringBuilder output = new StringBuilder();   //存放所有输出数据
//            StringBuilder outbuf = new StringBuilder();   //存放被提前隔离的数据
//            binTree.createBinTree(array, output, outbuf);  //隔离树的实现
//            //  System.out.println(output);
//            String output1 = output.toString();            //StringBuilder转成String
//            //    System.out.println(output1);
//            String[] output2 = output1.split(",");  //将String以逗号作为分隔符生成String数组
//            allout.add(output2);                           //集合里添加生成的数组
//        }
//        writeCSV(allout);                                   //写入CSV文件中
