public class HashLinkNode {

    private int key;

    private int value;

    private HashLinkNode next;

    public HashLinkNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public HashLinkNode getNext() {
        return next;
    }

    public void setNext(HashLinkNode next) {
        this.next = next;
    }

}