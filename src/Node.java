import java.util.ArrayList;
import java.util.List;

public class Node {
    private int numberOfNode;
    private List<NextTo> nextNode = new ArrayList<>();

    public int getNumberOfNode() {
        return numberOfNode;
    }

    public void setNumberOfNode(int numberOfNode) {
        this.numberOfNode = numberOfNode;
    }

    public List<NextTo> getNextNode() {
        return nextNode;
    }

    public void setNextNode(List<NextTo> nextNode) {
        this.nextNode = nextNode;
    }
}
