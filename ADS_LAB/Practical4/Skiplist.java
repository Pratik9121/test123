import java.util.Random;

public class Skiplist {
    private Node head;
    private Node tail;

    private final int NEG_INF = Integer.MIN_VALUE;
    private final int POS_INF = Integer.MAX_VALUE;

    private int heightOfSkipList = 0;

    public Random rnd = new Random();

    public Skiplist() {
        head = new Node(NEG_INF);
        tail = new Node(POS_INF);
        head.next = tail;
        tail.prev = head;
    }

    public Node skipSearch(int key) {
        Node n = head;

        while (n.below != null) {
            n = n.below;

            while (key >= n.next.key) {
                n = n.next;
            }
        }

        return n;
    }

    public Node skipInsert(int key) {
        Node pos = skipSearch(key);
        Node q;

        int lvl = -1;
        int numOfHead = -1;

        if (pos.key == key) {
            return pos;
        }

        do {
            lvl++;
            numOfHead++;

            canIncreaseLvl(lvl);
            q = pos;

            while (pos.above == null) {
                pos = pos.prev;
            }

            pos = pos.above;

            q = insertAfterAbove(pos, q, key);

        } while (rnd.nextBoolean() == true);

        return q;
    }

    public Node remove(int key) {
        Node nodeToRemove = skipSearch(key);

        if ((nodeToRemove != null) && (nodeToRemove.key != key)) {
            return null;
        }

        removeRefToNode(nodeToRemove);

        while (nodeToRemove != null) {
            removeRefToNode(nodeToRemove);

            if (nodeToRemove.above != null) {
                nodeToRemove = nodeToRemove.above;
            } else {
                break;
            }
        }

        return nodeToRemove;
    }

    private void removeRefToNode(Node nodeToRemove) {
        Node afterNodeToRemove = nodeToRemove.next;
        Node beforeNodeToRemove = nodeToRemove.prev;

        beforeNodeToRemove.next = afterNodeToRemove;
        afterNodeToRemove.prev = beforeNodeToRemove;
    }

    private Node insertAfterAbove(Node pos, Node q, int key) {
        Node newNode = new Node(key);
        Node nodeBeforeNewNode = pos.below.below;

        setBeforeAndAfterRef(q, newNode);
        setAboveAndBelowRef(pos, key, newNode, nodeBeforeNewNode);

        return newNode;
    }

    private void setAboveAndBelowRef(Node pos, int key, Node newNode, Node nodeBeforeNewNode) {
        if (nodeBeforeNewNode != null) {
            while (true) {
                if (nodeBeforeNewNode.next.key != key) {
                    nodeBeforeNewNode = nodeBeforeNewNode.next;
                } else {
                    break;
                }
            }
            newNode.below = nodeBeforeNewNode.next;
            nodeBeforeNewNode.next.above = newNode;
        }
        if (pos != null) {
            if (pos.next.key == key) {
                newNode.above = pos.next;
            }
        }
    }

    private void setBeforeAndAfterRef(Node q, Node newNode) {
        newNode.next = q.next;
        newNode.prev = q;
        q.next.prev = newNode;
        q.next = newNode;
    }

    private void canIncreaseLvl(int level) {
        if (level >= heightOfSkipList) {
            heightOfSkipList++;
            addEmptyLvl();
        }
    }

    private void addEmptyLvl() {
        Node newHeadNode = new Node(NEG_INF);
        Node newTailNode = new Node(POS_INF);

        newHeadNode.next = newTailNode;
        newHeadNode.below = head;
        newTailNode.prev = newHeadNode;
        newTailNode.below = tail;

        head.above = newHeadNode;
        tail.above = newTailNode;

        head = newHeadNode;
        tail = newTailNode;
    }

    public void printSkipList() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSkipList starting with top-left most node");

        Node start = head;

        Node highlvl = start;
        int level = heightOfSkipList;

        while (highlvl != null) {
            sb.append("\nLevel : " + level + "\n");

            while (start != null) {
                sb.append(start.key);

                if (start.next != null) {
                    sb.append(" : ");
                }
                start = start.next;
            }

            highlvl = highlvl.below;
            start = highlvl;
            level--;
        }
        System.out.println(sb.toString());
    }

}
