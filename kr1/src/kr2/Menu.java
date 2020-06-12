package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void startGraph() throws IOException {
        int max_v = 100000;

        printStartMenu();
        BufferedReader reader = getBufferedReader();
        int nodes = Integer.parseInt(reader.readLine()); //кол-во узлов
        int ribs = Integer.parseInt(reader.readLine()); //кол-во рёбер
        CityGraph cityGraph = new CityGraph(nodes, ribs); //инициализация графа города

        int[][] mass = new int[nodes][ribs];       // матрица смежностей

        for (int i = 0; i < nodes; i++) {   // присваиваем элементам матрицы максимальные значения
            for (int j = 0; j < nodes; j++) {
                mass[i][j] = max_v;
                mass[j][i] = max_v;
            }
        }

        printInputValues(ribs);
        for (int i = 0; i < ribs; i++) {    // заполняем матрицу значениями
            int firstNodeA = Integer.parseInt(reader.readLine());
            System.out.print("->");
            int secondNodeB = Integer.parseInt(reader.readLine());
            System.out.print("Стоимость пути: ");
            int cost = Integer.parseInt(reader.readLine());

            mass[firstNodeA][secondNodeB] = cost;
            mass[secondNodeB][firstNodeA] = cost;
        }
        
        printEnterStartAndEndNodes();
        int start = Integer.parseInt(reader.readLine());
        int end = Integer.parseInt(reader.readLine());

        CityGraphService cityGraphService = new CityGraphService(mass, cityGraph);
        System.out.println("Стоимость самого короткого пути: " + cityGraphService.findShortWay(start, end));
    }

    private static void printEnterStartAndEndNodes() { // пример : " 1 3 " : 1 - начало , 3 - конец пути
        System.out.println("Введите от какого узла до какого будем искать путь (узлы считаюся от нуля)");
    }

    private static void printInputValues(int ribs) {
        System.out.println("Введите первый узел, второй узел, значение стоимости ребра между ними. Итак " + ribs + "раз");
    }

    public static void printStartMenu() {
        System.out.println("Введите количество узлов и рёбер (например, 6 и 9)");
    }

    private static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

}
