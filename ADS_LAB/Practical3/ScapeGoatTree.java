package Practical3;

public class ScapeGoatTree {
    private SCGNode root;
    // Size of the tree
    int n;
    // overestimate of n
    int q;

    public ScapeGoatTree() {
        root = null;
        n = 0;
    }

    public boolean IsEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public int GetSize(SCGNode Rt) {
        if (Rt == null) {
            return 0;
        } else {
            return (GetSize(Rt.left) + 1 + GetSize(Rt.right));
        }
    }

    public int SearchKey(int Key) {
        return SearchKey(root, Key);
    }

    private int SearchKey(SCGNode Rt, int Key) {
        if (Rt == null || Rt.key == Key) {
            return Rt.key;
        } else if (Rt.key > Key) {
            return SearchKey(Rt.left, Key);
        } else {
            return SearchKey(Rt.right, Key);
        }
    }

    private static final int Log32(int Val) {
        final double lg32 = 2.4663034623764317;
        return (int) Math.ceil(lg32 * Math.log(Val));
    }

    public int InsertWithDepth(SCGNode N) {
        SCGNode Rt = root;
        if (Rt == null) {
            root = N;
            n++;
            q++;
            return 0;
        }
        boolean inserted = false;
        int depth = 0;
        do {
            if (N.key < Rt.key) {
                if (Rt.left == null) {
                    Rt.left = N;
                    N.parent = Rt;
                    inserted = true;
                } else {
                    Rt = Rt.left;
                }
            } else if (N.key > Rt.key) {
                if (Rt.right == null) {
                    Rt.right = N;
                    N.parent = Rt;
                    inserted = true;
                } else {
                    Rt = Rt.right;
                }
            } else {
                return -1;
            }
            depth++;
        } while (!inserted);
        n++;
        q++;
        return depth;
    }

    public void Insert(int Key, int obj) {
        SCGNode n = new SCGNode(Key, obj);
        int depth = InsertWithDepth(n);
        if (depth > Log32(q)) { // n can be used as well
            // Depth exceeded, find scapegoat
            SCGNode temp = n.parent;
            while (3 * GetSize(temp) <= 2 * GetSize(temp.parent)) {
                temp = temp.parent;
                System.out.println("Thresold is reached Rebuild the Tree");
                Rebuild(temp.parent);
            }
        }
    }

    public void Rebuild(SCGNode N) {
        int NodeSize = GetSize(N);
        SCGNode Parent = N.parent;
        SCGNode[] Arr = new SCGNode[NodeSize];
        PackIntoArr(N, Arr, 0);
        if (Parent == null) {
            root = BuildBalanced(Arr, 0, NodeSize);
            root.parent = null;
        } else if (Parent.right == N) {
            Parent.right = BuildBalanced(Arr, 0, NodeSize);
            // Parent.Right.Parent=Parent;
            System.out.println(Parent.right.key);
        } else {
            Parent.left = BuildBalanced(Arr, 0, NodeSize);
            // Parent.Left.Parent=Parent;
        }
    }

    public int PackIntoArr(SCGNode N, SCGNode[] Arr, int i) {
        if (N == null) {
            return i;
        }
        i = PackIntoArr(N.left, Arr, i);
        Arr[i++] = N;

        return PackIntoArr(N.right, Arr, i);
    }

    public SCGNode BuildBalanced(SCGNode[] Arr, int i, int NodeSize) {
        if (NodeSize == 0) {
            return null;
        }
        int m = NodeSize / 2;
        Arr[i + m].left = BuildBalanced(Arr, i, m);
        if (Arr[i + m].left != null) {
            Arr[i + m].left.parent = Arr[i + m];
        }
        Arr[i + m].right = BuildBalanced(Arr, i + m + 1, NodeSize - m - 1);
        if (Arr[i + m].right != null) {
            Arr[i + m].right.parent = Arr[i + m];
        }
        return Arr[i + m];
    }

    public void Delete(int Key) {
        root = Delete(root, Key);
    }

    private SCGNode Delete(SCGNode Rt, int Key) {
        if (Rt == null) {
            return Rt;
        }
        if (Key < Rt.key) {
            Rt.left = Delete(Rt.left, Key);
        } else if (Key > Rt.key) {
            Rt.right = Delete(Rt.right, Key);
        } else {
            if (Rt.left == null) {
                return Rt.right;
            } else if (Rt.right == null) {
                return Rt.left;
            }
            Rt.key = MinVal(Rt.right);
            Rt.right = Delete(Rt.right, Rt.key);
        }
        return Rt;
    }

    public int MinVal(SCGNode Rt) {
        int Minimum = Rt.key;
        while (Rt.left != null) {
            Minimum = Rt.left.key;
            Rt = Rt.left;
        }
        return Minimum;
    }

    public void InOrder() {
        System.out.println("Inorder Traversal of Scapegoattree is : ");
        InOrder(root);
    }

    private void InOrder(SCGNode Rt) {
        if (Rt != null) {
            String str = "";

            if (Rt.left == null) {
                str += ".";
            } else {
                str += Rt.left.key;
            }

            str += " <- " + Rt.key + " --> " + Rt.obj + " -> ";

            if (Rt.right == null) {
                str += ".";
            } else {
                str += Rt.right.key;
            }

            InOrder(Rt.left);
            System.out.println(str);
            InOrder(Rt.right);
        }

    }

    public void PreOrder() {
        System.out.println("Preorder Traversal of Scapegoattree is : ");
        PreOrder(root);
    }

    private void PreOrder(SCGNode Rt) {

        if (Rt != null) {
            System.out.println(Rt.key + " --> " + Rt.obj);
            PreOrder(Rt.left);
            PreOrder(Rt.right);
        }
    }

}
