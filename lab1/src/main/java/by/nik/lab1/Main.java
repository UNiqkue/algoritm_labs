package by.nik.lab1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        for (int n = 10000000; n <= 50000000; n += 10000000) {
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt();
            }

            long start = System.currentTimeMillis();

            getArray(arr);

            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.printf("При n = %d алгоритм выполняется %d мс \n", n, timeConsumedMillis);
        }
    }

    public static void getArray(int[] arr) {
        int sumArray = 0;

        for (int i = 0; i < arr.length; i++) {
            sumArray+=arr[i];
        }
        int average = sumArray/arr.length;
        System.out.println("Среднее = " + average);
    }
}
