package Practical1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Inputmaker {

    public void writeInputToFile() {
        try {
            File f = new File("Practical1\\input.txt");
            FileWriter fout = new FileWriter(f);
            for (int i = 1; i < 1002; i++) {
                fout.write(i + "\n");
            }
            fout.close();
            System.out.println("Input File is Ready.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void avlTreeInput() {
        try {
            File f = new File("Practical2\\input.txt");
            FileWriter fout = new FileWriter(f);
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 8; i < 20; i++) {
                arr.add(i);
            }
            Collections.shuffle(arr);

            for (Integer integer : arr) {
                fout.write(integer + "\n");
            }

            fout.close();
            System.out.println("Input File is Ready.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
