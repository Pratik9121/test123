import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. For insert node in skiplist");
            System.out.println("2. For delete node in skiplist");
            System.out.println("3. For search node in skiplist");
            System.out.println("4. For display skiplist");
            System.out.println("5. For Exit");
            int ch = sc.nextInt();

            switch(ch) {
                case 1:
                    System.out.println("Enter Element : ");
                    int key = sc.nextInt();
                    skiplist.skipInsert(key);
                    break;
                case 2:
                    System.out.println("Enter Element to Delete : ");
                    int key2 = sc.nextInt();
                    skiplist.remove(key2);
                    break;
                case 3:
                    System.out.println("Enter Element to Search : ");
                    int key3 = sc.nextInt();
                    Node temp = skiplist.skipSearch(key3);
                    if (temp != null) {
                        System.out.println(key3 + " is Present in skipList");
                    } else {
                        System.out.println(key3 + " is not present in skipList");
                    }
                    break;
                case 4:
                    skiplist.printSkipList();
                    break;
                case 5:
                    System.exit(0);
            }

        }
    }
    
}
