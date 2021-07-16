import java.util.ArrayList;

public class Wind {
    private ArrayList<Function> vxArr;
    private ArrayList<Function> vyArr;

    public Wind() {
        vxArr = new ArrayList<>();
        vyArr = new ArrayList<>();
    }

    public ArrayList<Function> getVx() {
        return vxArr;
    }

    public ArrayList<Function> getVy() {
        return vyArr;
    }

    public void setVxArr(ArrayList<Function> vxArr) {
        this.vxArr = vxArr;
    }

    public void setVyArr(ArrayList<Function> vyArr) {
        this.vyArr = vyArr;
    }

    public Vector getWindV(double x, double y, double t){
        double vx = 0;
        double vy = 0;
        for (Function function : vxArr)
            vx += function.value(x, y, t);
        for (Function function : vyArr)
            vy += function.value(x, y, t);

        return new Vector(vx, vy);
    }


}
