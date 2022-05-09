package Practical2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// import Practical1.Inputmaker;

public class AvlRunner {

    private class Pair {
        public int v1, v2;

        public Pair(int a, int b) {
            this.v1 = a;
            this.v2 = b;
        }
    }

    private ArrayList<Pair> arr;

    public AvlRunner() {
        arr = new ArrayList<Pair>();
        fetchInput();
    }

    private void fetchInput() {
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader("Practical2\\input.csv"));
            while ((line = br.readLine()) != null) {
                String[] keyval = line.split(",");
                Pair p = new Pair(Integer.parseInt(keyval[0]), Integer.parseInt(keyval[1]));
                arr.add(p);
            }
            br.close();
            // System.out.println("Input : " + arr);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] args) {
        AVLTree avl1 = new AVLTree();
        AvlRunner avr = new AvlRunner();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. For fetch Input from file and make AVL tree from that input");
            System.out.println("2. For Delete Node From AVL Tree");
            System.out.println("3. For display AVL tree");
            System.out.println("4. For Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    for (Pair p : avr.arr) {
                        System.out.println("inserting : " + p.v1 + " -> " + p.v2);
                        avl1.insert(p.v1, p.v2);
                    }
                    System.out.println("AVL Tree is Builded Successfully");
                    break;
                case 2:
                    System.out.println("Enter Key to Delete: ");
                    int key = sc.nextInt();
                    avl1.delete(key);
                    System.out.println(key + " is deleted Successfully.");
                    break;
                case 3:
                    avl1.display();
                    break;

                case 4:
                    System.exit(0);
                default:
                    System.out.println("Please Select from above choice!!");
            }
        }
    }
}
