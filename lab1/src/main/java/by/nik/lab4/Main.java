package by.nik.lab4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arrBubble = new int[100000];
        for (int i = 0; i < arrBubble.length; i++) {
            arrBubble[i] = random.nextInt(1000000) + 1;
        }

       int[] arrQuick = arrBubble.clone();
        for (int i = 0; i < arrQuick.length; i++) {
            arrQuick[i] = random.nextInt(10000) + 1;
        }

        long startBubble = System.currentTimeMillis();
        fastBubbleSort(arrBubble);
        long finishBubble = System.currentTimeMillis();
        long timeConsumedMillisBubble = finishBubble - startBubble;

        long start = System.nanoTime();
        quickSort(arrQuick, 0, arrQuick.length-1);
        long finish = System.nanoTime();
        long timeConsumedMillisQuickSort = finish - start;

        System.out.printf("\nАлгоритм укоренной сортировки пузырьком выполняется %d мс \n", timeConsumedMillisBubble);
        System.out.printf("\nАлгоритм быстрой сортировки выполняется %d нс \n", timeConsumedMillisQuickSort);
    }

    public static void fastBubbleSort(int[] arr) {
        int k = 1;
        int temp;
        boolean trp;

        do {
            trp = false;
            for (int i = 0; i < arr.length - k; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    trp = true;
                }
            }
            k++;
        } while (trp);

        for (int i : arr) {
            System.out.printf("%d ", i);
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = arr[middle];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < opora) {
                i++;
            }
            while (arr[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
    }

}
