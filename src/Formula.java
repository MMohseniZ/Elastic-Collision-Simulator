public class Formula {
    private int type;
    private double c1, c2, c3, c4, c5, c6;

    public Formula(int type, double c2, double c3, double c4, double c5) {
        this.type = type;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
    }

    public Formula(int type, double c1, double c2, double c3, double c4, double c5, double c6) {
        this.type = type;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
    }

    public double getV(double x, double y, double t) {
        if (type == 1) {
            return c2 * Math.pow(x, c3) * Math.pow(y, c4) * Math.pow(t, c5);
        } else if (type == 2) {
            return c1 * Math.pow(Math.sin(c2 * Math.pow(x, c3) * Math.pow(y, c4) * Math.pow(t, c5)), c6);
        } else if (type == 2){
            return c1 * Math.pow(Math.cos(c2 * Math.pow(x, c3) * Math.pow(y, c4) * Math.pow(t, c5)), c6);
        }
        return 0;
    }
}
