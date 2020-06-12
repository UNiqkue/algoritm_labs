package sample;

public class Main {
    
    public static void main(String[] args) {
        // n - количество узлов, т. е. V
        int n = 4;
        int[][] graph = {{0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}};
//        int n = 6;
//        int[][] graph = {{0, 1, 1,1, 0, 0},
//                {1, 0,1, 0, 1, 0},
//                {1,1, 0, 0, 0, 1},
//                {1,0,  0, 0, 1, 1},
//                {0, 1, 0, 1, 0, 1},
//                {0, 0, 1, 1, 1, 0}};

        // логический массив для проверки наличия узла
        // посещали или нет
        boolean[] wasInNode = new boolean[n];
        // Пометить 0-й узел как посещенный
        wasInNode[0] = true;
        int maxValue = Integer.MAX_VALUE;
        // Находим минимальный вес гамильтонова цикла
        int result = findCycle(graph, wasInNode, 0, n, 1, 0, maxValue);
        // result - результат пути гамильтонова цикла
        System.out.println(result);
    }

    // Функция поиска минимального веса
    // Гамильтонов цикл
    static int findCycle(int[][] graph, boolean[] wasInNode, int currPos, int n, int count, int cost, int maxValue) {

        // Если достигнут последний узел и есть ссылка к начальному узлу, т.е. к источнику
        // сохранить минимальное значение от общей стоимости прохождения и результат
        // Наконец возвращаемся, чтобы проверить больше возможных значений
        if (count == n && graph[currPos][0] > 0) {
            maxValue = Math.min(maxValue, cost + graph[currPos][0]);
            return maxValue;
        }
        // BACKTRACKING STEP
        // Цикл для обхода списка смежности узла currPos и увеличение количества
        // на 1 и стоимость на график [currPos, i] значение
        for (int i = 0; i < n; i++) {
            if (wasInNode[i] == false && graph[currPos][i] > 0) {
                // Пометить как посещенные
                wasInNode[i] = true;
                maxValue = findCycle(graph, wasInNode, i, n, count + 1,
                        cost + graph[currPos][i], maxValue);
                // Пометить i-й узел как не посещенный
                wasInNode[i] = false;
            }
        }
        return maxValue;
    }
}