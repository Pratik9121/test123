package Practical2;

public class AVLTree {

    private class Node {
        int data;
        Node left, right;
        int height;
        int key;

        public Node(int key, int data) {
            this.data = data;
            this.height = 1;
            this.key = key;
        }
    }

    private Node root;

    public void insert(int key, int val) {
        this.root = insert(this.root, key, val);
    }

    private Node insert(Node node, int key, int val) {
        if (node == null) {
            return new Node(key, val);
        }

        if (key > node.key) {
            node.right = insert(node.right, key, val);
        } else if (key < node.key) {
            node.left = insert(node.left, key, val);
        } else {
            node.data = val;
            System.out.println("Key Found, Object Updated");
            return node;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int bf = balanceFactor(node);

        // LL
        if (bf > 1 && key < node.left.key) {
            System.out.println("Performing Left Left Rotation");
            return rightRotate(node);
        }
        // RR
        if (bf < -1 && key > node.right.key) {
            System.out.println("Performing Right Right Rotation");
            return leftRotate(node);
        }
        // LR
        if (bf > 1 && key > node.left.key) {
            System.out.println("Performing Left Right Rotation");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (bf < -1 && key < node.left.key) {
            System.out.println("Performing Right Left Rotation");
            node.right = rightRotate(node.left);
            return leftRotate(node);
        }

        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rightRotate(Node node) {
        Node temp = node.left;
        Node temp2 = temp.right;

        // rotation
        temp.right = node;
        node.left = temp2;

        // height updation
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    private Node leftRotate(Node node) {
        Node temp = node.right;
        Node temp2 = temp.left;

        // rotation
        temp.left = node;
        node.right = temp2;

        // height updation
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    public void display() {
        System.out.println("Your AVL Tree is : ");
        inord(this.root);
    }

    private void inord(Node node) {
        if (node != null) {
            String str = "";

            if (node.left == null) {
                str += ".";
            } else {
                str += node.left.key;
            }

            str += " <- " + node.key + " --> " + node.data + " -> ";

            if (node.right == null) {
                str += ".";
            } else {
                str += node.right.key;
            }

            System.out.println(str);
            inord(node.left);
            inord(node.right);
        }
    }

    private int findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.key;
    }

    public void delete(int key) {
        this.root = delete(this.root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            System.out.println("Tree is Empty!");
            return node;
        } else if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                int minn = findMin(root.right);
                node.key = minn;
                node.right = delete(node.right, minn);
            }
        }
        if (node == null) {
            return node;
        }
        node.height = height(node);

        int balance = node.height;

        if ((node.left).height >= 0 && balance > 1) {
            return rightRotate(node);
        } else if ((node.right).height <= 0 && balance < -1) {
            return leftRotate(node);
        } else if ((node.left).height < 0 && balance > 1) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        else if ((node.right).height > 0 && balance < -1) {
            node.right = rightRotate(node.left);
            return leftRotate(node);
        }
        return node;
    }
}
