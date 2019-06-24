public class Frequency {
    double MAX ;
    double AVG ;
    double variance;
    Frequency(){
        MAX = 0;
        AVG = 0;
        variance =0;
    }
    Frequency(double max ,double avg ,double variance){
        MAX=max;
        AVG=avg;
        this.variance=variance;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "MAX=" + MAX +
                ", AVG=" + AVG +
                ", variance=" + variance +
                '}';
    }

    public double getMAX() {
        return MAX;
    }

    public void setMAX(double MAX) {
        this.MAX = MAX;
    }

    public double getAVG() {
        return AVG;
    }

    public void setAVG(double AVG) {
        this.AVG = AVG;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }
}
