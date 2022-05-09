package Practical3;

public class SCGNode {
    protected SCGNode right, left, parent;
    protected int obj, key;

    public SCGNode(int key, int obj) {
        right = left = parent = null;
        this.key = key;
        this.obj = obj;
    }
}
