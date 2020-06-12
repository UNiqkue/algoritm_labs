package sample;

public class CityGraphService {

    private int[][] mass;
    private CityGraph cityGraph;

    public CityGraphService() {
    }

    public CityGraphService(int[][] mass, CityGraph cityGraph) {
        this.mass = mass;
        this.cityGraph = cityGraph;
    }

    public int findShortWay(int start, int end) {
        int W = 99;
        int[] D = new int[100];
        int[] used = new int[100];

        if (start == end) // если начальный узел является конечным,
            return 0; // то путь равен 0

        /* используем алгоритм Дейкстры */
        for (int i = 0; i < cityGraph.getNode(); i++) //  заполняем массив, который отвечает за проход узла (если побывали в узле, то присваиваем 1, иначе 0)
            used[i] = 0;

        for (int i = 0; i < cityGraph.getNode(); i++)
            D[i] = mass[start][i]; // массив, который содержит кратчайший путь из заданной вершины в вершину с номером i
        used[start] = 1; // побывали в начальном узле

        for (int i = 0; i < cityGraph.getNode() - 2; i++) {
            int min_v = 1000000;
            for (int j = 0; j < cityGraph.getNode(); j++)
                if (used[j] == 0 && D[j] < min_v) {  // если еще не побывали в вершине j, и значение в вершине с номером j меньше, чем предыдущее значение,
                    min_v = D[j]; // min_v присваивам минимальное значение.
                    W = j; // W - номер узла с наименьшим значением пути
                }
            used[W] = 1;

            for (int j = 0; j < cityGraph.getNode(); j++)
                if (used[j] == 0)
                    D[j] = Math.min(D[j], D[W] + mass[W][j]); // выбираем минимальный путь
        }

        return D[end];
    }
}
