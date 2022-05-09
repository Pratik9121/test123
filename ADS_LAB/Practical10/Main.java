import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hashmap map = new Hashmap();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Entry");
            System.out.println("2. Delete Entry");
            System.out.println("3. Search Element");
            System.out.println("4. Display Table");
            System.out.println("5. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter key : ");
                    int key = sc.nextInt();
                    System.out.println("Enter val : ");
                    int val = sc.nextInt();
                    map.put(key, val);
                    break;
                case 2:
                    System.out.println("Enter key : ");
                    int k = sc.nextInt();
                    map.remove(k);
                    break;
                case 3:
                    System.out.println("Enter key To Search : ");
                    int k1 = sc.nextInt();
                    System.out.println(map.get(k1));
                    break;
                case 4:
                    map.print();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
