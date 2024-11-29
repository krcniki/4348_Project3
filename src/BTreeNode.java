
import java.util.ArrayList;

public class BTreeNode {
    public boolean isLeaf;
    public ArrayList<Integer> keys;
    public ArrayList<Integer> values;
    public ArrayList<BTreeNode> children;

    public BTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
        this.children = new ArrayList<>();
    }
}
