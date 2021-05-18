package util;

public class Average {

    private final int id;
    private double average;
    private double size;

    public Average(int id) {
        this.id = id;
        this.size = 1;
        this.average = 0;
    }

    public void putValue(double value){
        average = (average + value)/size;
        size++;

        if(size == 1000){
            average /= size;
            size = 1;
        }
    }

    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }
}
