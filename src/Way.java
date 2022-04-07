import java.util.ArrayList;
import java.util.List;

public class Way {
    private int value;
    private List<Integer> minWay = new ArrayList<>();

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getMinWay() {
        return minWay;
    }

    public void setMinWay(List<Integer> minWay) {
        this.minWay = minWay;
    }
}
