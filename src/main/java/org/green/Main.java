package org.green;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filename = "serialization.txt";
        ArrayList<Human> humanList = new ArrayList<>();
        ArrayList<Human> newHumanList = new ArrayList<>();

        humanList.add(new Human("Yaroslav", 3, "Businessman"));
        humanList.add(new Human("Lev", 6, "Marketer"));
        humanList.add(new Human("Arkady", 15, "Manager"));
        humanList.add(new Human("Bogdan", 23, "Coach"));
        humanList.add(new Human("Eugene", 37, "Financier"));
        humanList.add(new Human("Alexey", 70, "Company Owner"));

        try {
            serialize(humanList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            deserialize(newHumanList, filename, humanList.size());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Output lists
        System.out.println("Old list");
        for (Human human : humanList) {
            System.out.println(human);
        }

        System.out.println("New list");
        for (Human human : newHumanList) {
            System.out.println(human);
        }
    }

    private static void serialize(ArrayList<Human> humanList, String filename) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        for (Human human : humanList) {
            objectOutputStream.writeObject(human);
        }

        fileOutputStream.close();
    }

    private static void deserialize(ArrayList<Human> humanList, String filename, int size)
            throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        for (int i = 0; i < size; i++) {
            humanList.add((Human) objectInputStream.readObject());
            getProfession(humanList.get(i));
        }

        objectInputStream.close();

    }

    private static void getProfession(Human human) {

        int age = human.getAge();

        if (age > 0 && age < 3) {
            human.setProfession("Stays at home");
        } else if (age < 7) {
            human.setProfession("Goes to kindergarten");
        } else if (age < 18) {
            human.setProfession("Studies at school");
        } else if (age < 23) {
            human.setProfession("Studies at university");
        } else if (age < 65) {
            human.setProfession("Works");
        } else {
            human.setProfession("Retired");
        }
    }
}