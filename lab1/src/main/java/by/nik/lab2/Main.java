package by.nik.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];

        // Заполнение массива
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) + 1;
        }

        // Сортировка
        Arrays.sort(arr);
        for(int value : arr) {
            System.out.print(value + " ");
        }

        // Ввод числа для поиска
        System.out.println("\nВведите число для поиска: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = 0;
        try {
            number = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("Вы ввели не число");
        }

        long start = System.nanoTime();

        Binary binary = binarySearch(arr, number);

        long finish = System.nanoTime();

        System.out.println("Кол-во итераций: " + binary.getCount());

        if (binary.getResult() != -1)
            System.out.println("Найден под индексом =  " + binary.getResult());
        else System.out.println("Такое число не найдено");

        long timeConsumedMillis = finish - start;
        System.out.printf("алгоритм выполняется %d нс \n", timeConsumedMillis);
    }

    // Метод дихотомии
    public static Binary binarySearch(int[] sortedArray, int key) {
        int index = -1; //если не найдёт элемент, то вернёт его
        int count = 0; // кол-во итераций поиска
        int firstIndex = 0; //первый элемент массива
        int lastIndex = sortedArray.length - 1; //последний элемент массива
        while (firstIndex <= lastIndex) {
            count++;
            int mid = (firstIndex + lastIndex) / 2;
            if (sortedArray[mid] < key) {
                firstIndex = mid + 1;
            } else if (sortedArray[mid] > key) {
                lastIndex = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return new Binary(count, index);
    }
}
