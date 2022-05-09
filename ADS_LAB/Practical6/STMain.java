import java.util.Scanner;

public class STMain {
    public static void main(String[] args) {
        // int arr[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // static array according to
        // defination

        SegmentTree sgt = new SegmentTree();

        int a[];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Size of the Array : ");
        int n = sc.nextInt();

        a = new int[n];
        System.out.println("Enter array elements : ");
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }

        STNode root = sgt.buildSegmentTree(a, 0, n - 1);
        sgt.preOrd(root);

        while (true) {
            System.out.println("1. For sum range queries");
            System.out.println("2. exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    int left = sc.nextInt();
                    int right = sc.nextInt();
                    System.out.println("Sum : (" + left + ", " + right + ") : " + sgt.getQuerySum(root, left, right));
                    break;
                case 2:
                    System.exit(0);
            }
        }

    }
} 