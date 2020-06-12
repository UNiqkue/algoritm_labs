package by.nik.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100000];
        // Заполнение массива
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000)+1;

        }

        Arrays.sort(arr);

        for (int value : arr) {
            System.out.print(value + "\t");
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

        long start = System.currentTimeMillis();

        interSearch(arr, 0, arr.length - 1, number);

        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.printf("Алгоритм выполняется %d мс \n", timeConsumedMillis);
    }

    public static void interSearch(int[] arr, int low, int high, int k) {
        if (k >= 0) {
            while (arr[low] < k && arr[high] >= k) {
                int mid = low + (k - arr[low]) * (high - low) / (arr[high] - arr[low]);
                if (arr[mid] < k) {
                    low = mid + 1;
                    interSearch(arr, low, high, k);
                } else if (arr[mid] > k) {
                    high = mid - 1;
                    interSearch(arr, low, high, k);
                } else {
                    System.out.printf("Искомый номер найден на позиции %d\n", mid);
                    return;
                }
            }
            if (arr[low] == k) {
                System.out.printf("Искомый номер найден на позиции %d\n", low);
            } else if (arr[high] == k) {
                System.out.printf("Искомый номер найден на позиции %d\n", high);
            } else {
                System.out.println("Искомый номер не найден\n");
            }
        }
    }

}
