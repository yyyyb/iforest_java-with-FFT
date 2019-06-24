import com.csvreader.CsvWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FFT {


        // compute the FFT of x[], assuming its length is a power of 2
        public static Complex[] fft(Complex[] x) {
            int N = x.length;

            // base case
            if (N == 1) return new Complex[] { x[0] };

            // radix 2 Cooley-Tukey FFT
            if (N % 2 != 0) { throw new RuntimeException("N is not a power of 2"); }

            // fft of even terms
            Complex[] even = new Complex[N/2];
            for (int k = 0; k < N/2; k++) {
                even[k] = x[2*k];
            }
            Complex[] q = fft(even);

            // fft of odd terms
            Complex[] odd  = even;  // reuse the array
            for (int k = 0; k < N/2; k++) {
                odd[k] = x[2*k + 1];
            }
            Complex[] r = fft(odd);

            // combine
            Complex[] y = new Complex[N];
            for (int k = 0; k < N/2; k++) {
                double kth = -2 * k * Math.PI / N;
                Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
                y[k]       = q[k].plus(wk.times(r[k]));
                y[k + N/2] = q[k].minus(wk.times(r[k]));
            }
            return y;
        }
    public static void main(String[] args) {
        String[] filenameList = new String[54];
        LinkedList<String[]> E = new LinkedList<String[]>();
        for(int j=1;j<=54;j++){
            if(j==5){
                filenameList[j - 1]="传感器5-空补4的倒置.txt";
            }
            else if(j==15){
                filenameList[j - 1]="传感器15-空补14和16平均.txt";
            }
            else if(j==28){
                filenameList[j - 1]="传感器28-光照数据缺失补27光照.txt";
            }
            else if(j==49){
                filenameList[j - 1]="传感器49-少取48和50平均.txt";
            }
            else {
                filenameList[j - 1] = "传感器" + String.valueOf(j) + ".txt";
            }
        }
        for(int b =0 ;b<filenameList.length;b++) {
            /*新建文件夹\*/
            String csvfile = "C:\\Users\\79441\\Desktop\\测试数据集\\英特尔实验室传感器数据集\\异常点8点-9点\\8-9点插值结果\\" + filenameList[b];//CSV文件地址
            List<String[]> csvdata = new ArrayList<String[]>();                  //获取输入值
            IForest.readCSV(csvfile, csvdata);           //读取CSV文件将数值传到array中
            System.out.println(csvdata.size());
            Frequency[] frequencies = new Frequency[csvdata.get(0).length];
            for (int k = 3; k < 4; k++) {
                Complex[] a = new Complex[csvdata.size()];
                for (int i = 0; i < csvdata.size(); i++) {
                    a[i] = new Complex(Double.valueOf(csvdata.get(i)[k]), 0);
                }
//        Complex[] a = {new Complex(1,0),new Complex(2,0),new Complex(3,0),new Complex(4,0),new Complex(3,0),
//                new Complex(2,0),new Complex(1,0),new Complex(0,0)};
                FFT aaa = new FFT();
                Complex[] C = aaa.fft(a);
//            System.out.println(C.length);
                String[] D = new String[C.length];
                for (int i = 0; i < C.length; i++) {
                    System.out.println(C[i].model());
                    D[i] = String.valueOf(C[i].model());
                }
                    E.add(D);

                System.out.println("-----");
            }
        }
        ArtificialData.writeCSV(E, "电压.txt");
        }
}
