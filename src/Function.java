public class Function {
    private double k1, k2, k3, k4, k5, k6;
    private int type;

    public Function(double k2, double k3, double k4, double k5, int type) {
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
        this.type = type;
    }

    public Function(double k1, double k2, double k3, double k4, double k5, double k6, int type) {
        this.k1 = k1;
        this.k2 = k2;
        this.k3 = k3;
        this.k4 = k4;
        this.k5 = k5;
        this.k6 = k6;
        this.type = type;
    }

    public double value(double x, double y, double t){
        if(type == 1)
            return k2 * Math.pow(x, k3) * Math.pow(y, k4) * Math.pow(t, k5);
        else if(type == 2)
            return k1 * Math.pow(Math.sin(k2 * Math.pow(x, k3) * Math.pow(y, k4) * Math.pow(t, k5)), k6);
        else
            return k1 * Math.pow(Math.cos(k2 * Math.pow(x, k3) * Math.pow(y, k4) * Math.pow(t, k5)), k6);
    }
}
