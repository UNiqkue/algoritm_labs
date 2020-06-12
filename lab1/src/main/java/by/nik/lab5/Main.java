package by.nik.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {
    private static final int MAX_VALUE = 120;

    public static void main(String[] args) {
        Random rnd = new Random();
        int n = 16;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(MAX_VALUE) + 1;
        }
        BinaryTree tree = new BinaryTree(array[0], null);
        System.out.println("Рандомный массив: ");
        for (int i = 1; i < n; i++) {
            tree.add(array[i]);
            System.out.printf("%d ", array[i]);
        }

        System.out.println("\nОтсортированный массив:");
        tree.printInorder(tree);

        System.out.println("\nВведите число для поиска: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = 0;
        try {
            number = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("Вы ввели не число");
        }
        BinaryTree binaryTree = tree.search(number);
        if (binaryTree == null) {
            System.out.println("Не найдено");
        } else
            System.out.println(binaryTree.getValue());

    }
}


