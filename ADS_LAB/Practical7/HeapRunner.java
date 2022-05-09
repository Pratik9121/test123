import java.util.Scanner;

public class HeapRunner {
    public static void main(String[] args) throws Exception {
        Heap h1;
        String heaptype = "";

        Scanner sc = new Scanner(System.in);

        System.out.print("Press 1 for MaxHeap 0 for MinHeap : ");
        int heapchoice = sc.nextInt();

        heaptype = (heapchoice == 1) ? "Maxheap" : "Minheap";
        h1 = new Heap(heapchoice);

        while (true) {
            System.out.println("\n1. For Push Element into " + heaptype + " heap");
            System.out.println("2. For Pop Element from " + heaptype + " heap");
            System.out.println("3. For Fetch Element from " + heaptype + " heap");
            System.out.println("4. For Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Element : ");
                    int ele = sc.nextInt();
                    System.out.print("\nEnter Priority : ");
                    int prio = sc.nextInt();
                    h1.push(ele, prio);
                    break;
                case 2:
                    HNode temp = h1.pop();
                    if (temp == null) {
                        System.out.println("Heap is Empty!!!");
                        System.exit(0);
                    } else {
                        System.out.println(temp.key + " is popped from heap");
                    }
                    break;
                case 3:
                    System.out.println("Element : " + h1.peek());
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
