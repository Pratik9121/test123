public class Heap {
    HNode head, tail;
    int type;

    public Heap(int type) {
        this.type = type;
    }

    public void push(int key, int priority) {
        if (head == null) {
            head = new HNode(key, priority);
            tail = head;
            return;
        }

        if (type == 0) {
            if (priority < head.priority) {
                pushtAtBeg(key, priority);
            } else if (priority > tail.priority) {
                pushtAtEnd(key, priority);
            } else {
                pushAtMid(key, priority);
            }
        } else {
            if (priority > head.priority) {
                pushtAtBeg(key, priority);
            } else if (priority < tail.priority) {
                pushtAtEnd(key, priority);
            } else {
                pushAtMid(key, priority);
            }
        }

    }

    private void pushtAtBeg(int key, int priority) {
        HNode temp = new HNode(key, priority);
        temp.next = head;
        head = temp;
    }

    private void pushtAtEnd(int key, int priority) {
        HNode temp = new HNode(key, priority);
        tail.next = temp;
        tail = temp;
    }

    private void pushAtMid(int key, int priority) {
        HNode curr = head;
        HNode prev = curr;

        if (type == 0) {
            while (curr != null && curr.priority < priority) {
                prev = curr;
                curr = curr.next;
            }

            HNode temp = new HNode(key, priority);
            prev.next = temp;
            temp.next = curr;
        } else {
            while (curr != null && curr.priority > priority) {
                prev = curr;
                curr = curr.next;
            }

            HNode temp = new HNode(key, priority);
            prev.next = temp;
            temp.next = curr;
        }
    }

    public int peek() {
        if (head == null) {
            return -1;
        }
        return head.key;
    }

    public HNode pop() {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            HNode temp = head;
            head = tail = null;
            return temp;
        }
        HNode temp = head;
        head = head.next;

        return temp;
    }
}
