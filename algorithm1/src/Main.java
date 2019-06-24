import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(!Integer.valueOf(Integer.valueOf("08:00:16".split(":")[1]) % 59 + 1).equals(Integer.valueOf("08:01:16".split(":")[1]) % 59));
//        Complex[][] a = {{new Complex(1, 0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0), new Complex(3, 0), new Complex(2, 0), new Complex(1, 0), new Complex(0, 0)},
//                {new Complex(1.1, 0), new Complex(2.2, 0), new Complex(3.1, 0), new Complex(3.9, 0), new Complex(3.1, 0), new Complex(2.1, 0), new Complex(1.1, 0), new Complex(0.2, 0)},
//                {new Complex(1.2, 0), new Complex(2.1, 0), new Complex(6, 0), new Complex(4.1, 0), new Complex(3.2, 0), new Complex(2.0, 0), new Complex(1.2, 0), new Complex(0, 0)},
//                {new Complex(1.1, 0), new Complex(2.3, 0), new Complex(3.2, 0), new Complex(4.2, 0), new Complex(3.1, 0), new Complex(2.1, 0), new Complex(1.3, 0), new Complex(0, 0)},
//                {new Complex(3, 0), new Complex(5, 0), new Complex(3, 0), new Complex(3.8, 0), new Complex(2.9, 0), new Complex(1.9, 0), new Complex(0.9, 0), new Complex(0, 0)},
//                {new Complex(0.9, 0), new Complex(2.1, 0), new Complex(3.1, 0), new Complex(4, 0), new Complex(3.4, 0), new Complex(1.9, 0), new Complex(0.8, 0), new Complex(0, 0)},
//                {new Complex(1.3, 0), new Complex(2.2, 0), new Complex(2.9, 0), new Complex(4.1, 0), new Complex(3.1, 0), new Complex(1.8, 0), new Complex(0.8, 0), new Complex(0, 0)},
//                {new Complex(0.8, 0), new Complex(2, 0), new Complex(3.1, 0), new Complex(3.8, 0), new Complex(3.0, 0), new Complex(2.1, 0), new Complex(1.1, 0), new Complex(0, 0)},
//                {new Complex(1.1, 0), new Complex(1.8, 0), new Complex(3.0, 0), new Complex(4.1, 0), new Complex(3.1, 0), new Complex(2.1, 0), new Complex(1.2, 0), new Complex(0, 0)},
//                {new Complex(1, 0), new Complex(1.9, 0), new Complex(3.1, 0), new Complex(4.2, 0), new Complex(3.0, 0), new Complex(2.2, 0), new Complex(1.1, 0), new Complex(0, 0)},
//        };
//        List<Frequency> res = new ArrayList<Frequency>();
//        for (int j = 0; j < a.length; j++) {
//            FFT aaa = new FFT();
//            Complex[] C = aaa.fft(a[j]);
//          //  System.out.println(C.length);
//            double count = 0; //计算模的总和
//            double max = 0; //计算最大值
//            double[] pervariance = new double[C.length]; //用于方差计算的平方总和
//            for (int i = 0; i < C.length; i++) {
//                double model = C[i].model(); //求得频域特征的模
//                pervariance[i] = model;
//                System.out.println(model);
//                count += model;
//                if (model > max) {
//                    max = model;
//                }
//            }
//            double avg = count / C.length;
//            double variancecount = 0;
//            for (int i = 0; i < pervariance.length; i++) {
//                pervariance[i] = (pervariance[i] - avg) * (pervariance[i] - avg);
//                variancecount += pervariance[i];
//            }
//            double variance = (1 / (double) pervariance.length) * variancecount;
////            System.out.println("MAX=" + max);
////            System.out.println("AVG=" + avg);
////            System.out.println("VAR=" + variance);
//            Frequency thisfre = new Frequency(max,avg,variance);
//            res.add(thisfre);
//        }
//        for (int k=0;k<res.size();k++){
////            System.out.println("AVG="+ res.get(k).getAVG() + "MAX=" + res.get(k).getMAX() + "VAR=" + res.get(k).getVariance());
//        }
//        IForest binTree = new IForest();
//        double[] avgarray = new double[res.size()];
//        double[] maxarray = new double[res.size()];
//        double[] vararray = new double[res.size()];
//        for (int i =0;i<res.size();i++){
//            avgarray[i] = res.get(i).getAVG();
//            maxarray[i] = res.get(i).getMAX();
//            vararray[i] = res.get(i).getVariance();
//        }
//        ArrayList<String[]> allout=new ArrayList<String[]>();  //创建一个String数组集合用于存放每次输出的元素层数的字符串
//        for(int x=0; x<10 ; x++ ) {
//            StringBuilder output = new StringBuilder();   //存放所有输出数据
//            StringBuilder outbuf = new StringBuilder();   //存放被提前隔离的数据
//            binTree.createBinTree(avgarray, output, outbuf);  //隔离树的实现
//            binTree.createBinTree(maxarray,output,outbuf);
//            binTree.createBinTree(vararray,output,outbuf);
//            String output1 = output.toString();            //StringBuilder转成String
//            String[] output2 = output1.split(",");  //将String以逗号作为分隔符生成String数组
//            allout.add(output2);                           //集合里添加生成的数组
//        }
//        IForest.writeCSV(allout);                                   //写入CSV文件中
    }
}
