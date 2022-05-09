package Practical3;

import java.util.Scanner;

public class ScapeGoatMain {
    public static void main(String[] args) {
        ScapeGoatTree sgt = new ScapeGoatTree();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. For Insert");
            System.out.println("2. For Delete");
            System.out.println("3. For Display");
            System.out.println("4. For Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    int key, val;
                    System.out.println("Enter Key : ");
                    key = sc.nextInt();
                    System.out.println("Enter Value : ");
                    val = sc.nextInt();
                    sgt.Insert(key, val);
                    break;
                case 2:
                    int k;
                    System.out.println("Enter Key to Delete : ");
                    k = sc.nextInt();
                    sgt.Delete(k);
                    System.out.println(k + " is deleted successfully");
                    break;
                case 3:
                    System.out.println("1. For inorder traversal");
                    System.out.println("2. For preorder traversal");
                    int c = sc.nextInt();
                    if (c == 1) {
                        sgt.InOrder();
                    } else if (c == 2){
                        sgt.PreOrder();
                    } else {
                        System.out.println("Enter Valid Choice!!");
                    }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Enter Valid Choice!!");
            }
        }

    }
}
