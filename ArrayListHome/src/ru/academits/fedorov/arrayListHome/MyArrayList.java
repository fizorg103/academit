package ru.academits.fedorov.arrayListHome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MyArrayList {
    public static void main(String[] args) {
        String fileName = ".gitignore";

        ArrayList<String> linesList = new ArrayList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = fileReader.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : linesList) {
            System.out.println(line);
        }

        int seed = 123;
        Random random = new Random(seed);
        ArrayList<Integer> intList = new ArrayList<>();

        int numbersCount = 30;
        for (int i = 0; i < numbersCount; ++i) {
            intList.add(random.nextInt(10));
        }
        System.out.println(intList.toString());

        int index = 0;
        while (index < intList.size()) {
            if (intList.get(index) % 2 == 0) {
                intList.remove(index);
            } else {
                ++index;
            }
        }
        System.out.println(intList.toString());

        ArrayList<Integer> intListNew = new ArrayList<>();

        intListNew.add(intList.get(0));
        for (int element: intList) {
            if (!intListNew.contains(element)) {
                intListNew.add(element);
            }
        }
        System.out.println(intListNew);
    }
}
