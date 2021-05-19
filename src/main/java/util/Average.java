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
        if(size == 1000){
            average = (average / size-1 ) + ( value / size);
        } else {
            average = (average + value)/size;
            size++;
        }
    }

    public double getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    public double getSize() {
        return size;
    }
}
