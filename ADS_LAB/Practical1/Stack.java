package Practical1;

import java.io.*;
import java.util.*;

public class Stack {
    private float threshold;
    private int top;
    private int n;
    private int stk[];

    // for file input reading purpose
    private Vector<Integer> inpvec = new Vector<Integer>();
    // for fetch one by one value form above vector
    private Iterator<Integer> it;

    public Stack(float threshold, int n) {
        this.threshold = threshold;
        this.top = -1;
        this.n = n;
        this.stk = new int[this.n];
    }

    public void shadowCopy() {
        // double the size
        this.n = this.n * 2;
        // make new doubled size array
        int newarr[] = new int[this.n];

        // copy original to new
        for (int i = 0; i < this.stk.length; i++) {
            newarr[i] = this.stk[i];
        }

        // assign back to original
        this.stk = newarr;
    }

    // for pushing one item :-> Normal function
    public void push(int data) {
        if (this.top + 1 <= (this.threshold * this.n) - 1) {
            this.stk[++this.top] = getInput();
        } else {
            display();
            shadowCopy();
            this.stk[++this.top] = getInput();
        }
    }

    // for fetch data from file into vector and initialize iterator
    public void fetchInput() {
        try {
            File input = new File("Practical1\\input.txt");
            Scanner sc = new Scanner(input);

            while (sc.hasNextLine()) {
                inpvec.add(Integer.valueOf(sc.nextLine()));
            }
            this.it = inpvec.iterator();
            sc.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // for next iteration of input
    public int getInput() {
        if (this.it.hasNext()) {
            int temp = (int) it.next();
            return temp;
        }
        return -1;
    }

    // advanced function that take input from file
    public void push() {
        Scanner sc = new Scanner(System.in);
        int continue_further = 0;
        boolean isReturn = false;
        do {
            System.out.println("Size : " + this.n);
            while (this.top + 1 <= (this.threshold * this.n) - 1) {
                int inp = getInput();
                if (inp != -1) {
                    this.stk[++this.top] = inp;
                } else {
                    System.out.println("End of file input!!");
                    isReturn = true;
                    break;
                }
            }
            display();
            if (isReturn == true) {
                return;
            }
            System.out.println("Do you Want to Continue ? (1->Yes, 0->No) : ");
            continue_further = sc.nextInt();
            if (continue_further == 1) {
                shadowCopy();
            } else {
                return;
            }

        } while (continue_further == 1);
        sc.close();
    }

    // pop from stack
    public int pop() {
        if (this.top == -1) {
            return -1;
        }
        int temp = this.stk[this.top];
        --this.top;
        display();
        return temp;
    }

    // return top element from stack
    public int peek() {
        if (this.top == -1) {
            return -1;
        }
        return this.stk[this.top];
    }

    // display stack data
    public void display() {
        System.out.println("CurrTop is : " + this.top);
        System.out.print("[ ");
        for (int i = 0; i <= this.top; i++) {
            System.out.print(this.stk[i] + " ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static void main(String[] args) {
        Inputmaker inpmaker = new Inputmaker();
        inpmaker.writeInputToFile();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Alpha : ");
        float alpha = sc.nextFloat();

        System.out.println("Enter Initial Size of the Stack : ");
        int n = sc.nextInt();

        Stack s = new Stack(alpha, n);
        s.fetchInput();
        while (true) {
            System.out.println("1. For push");
            System.out.println("2. For pop");
            System.out.println("3. For peek");
            System.out.println("4. For Display");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    s.push();
                    break;
                case 2:
                    int res = s.pop();
                    if (res == -1) {
                        System.out.println("Stack is Empty!!");
                    }
                    break;
                case 3:
                    System.out.println(s.peek());
                    break;
                case 4:
                    s.display();
                    break;
                default:
                    System.out.println("Enter Valid Choice -> (From 1 To 4) <- only !!");
                    break;
            }
        }
    }
}
