public class SegmentTree {
    public STNode buildSegmentTree(int Arr[], int l, int r) {
        if (l == r) {
            STNode node = new STNode(l, r, Arr[l]);
            return node;
        }
        int mid = (l + r) / 2;
        STNode leftNode = buildSegmentTree(Arr, l, mid);
        STNode rightNode = buildSegmentTree(Arr, mid + 1, r);
        STNode root = new STNode(leftNode.start, rightNode.end, leftNode.sum + rightNode.sum);
        root.left = leftNode;
        root.right = rightNode;

        return root;
    }

    public int getQuerySum(STNode root, int l, int r) {
        if (root.end < l || root.start > r) {
            return 0;
        }
        if (root.start >= l && root.end <= r) {
            return root.sum;
        }
        return getQuerySum(root.left, l, r) + getQuerySum(root.right, l, r);
    }

    public void preOrd(STNode root) {
        if (root != null) {
            System.out.print(root.sum + " ");
            preOrd(root.left);
            preOrd(root.right);
        }
    }
    
}
