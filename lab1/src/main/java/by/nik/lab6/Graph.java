package by.nik.lab6;

import java.util.*;

public class Graph {
    private int[][] adjacentMatrix; //матрица смежности
    private int[][] incidencesMatrix; // матрица инцидентности
    private int[][] adjacentList; // списки смежности
    private int[][] ribList; // списки дуг

    public Graph() {
    }

    public Graph(int[][] adjacentMatrix, int[][] incidencesMatrix, int[][] adjacentList, int[][] ribList) {
        this.adjacentMatrix = adjacentMatrix;
        this.incidencesMatrix = incidencesMatrix;
        this.adjacentList = adjacentList;
        this.ribList = ribList;
    }

    public Graph(Graph graph) {
        this(graph.getAdjacentMatrix(), graph.getIncidencesMatrix(), graph.getAdjacentList(), graph.getRibList());
    }

    public void addNode() {
        // смежная
        int[][] a = new int[adjacentMatrix.length + 1][adjacentMatrix.length + 1];
        for (int i = 0; i < adjacentMatrix.length; i++)
            System.arraycopy(adjacentMatrix[i], 0, a[i], 0, adjacentMatrix.length);
        this.adjacentMatrix = a;
        // инцидентности
        a = new int[incidencesMatrix.length + 1][incidencesMatrix.length];
        for (int i = 0; i < incidencesMatrix.length; i++)
            System.arraycopy(incidencesMatrix[i], 0, a[i], 0, incidencesMatrix.length);
        this.incidencesMatrix = a;
    }

    public void addRib(int firstNode, int secondNode) {
        if (firstNode >= adjacentMatrix.length || secondNode >= adjacentMatrix.length) {
            System.out.println("Такого узла не существует:" + firstNode + " " + secondNode);
        }
        // смежная
        adjacentMatrix[firstNode][secondNode] = 1;
        adjacentMatrix[secondNode][firstNode] = 1;
        // инцидентности
        int[][] a = new int[incidencesMatrix.length][incidencesMatrix.length];
        for (int i = 0; i < incidencesMatrix.length; i++)
            for (int j = 0; j < incidencesMatrix[i].length; j++)
                a[i][j] = incidencesMatrix[i][j];
        a[firstNode][incidencesMatrix.length - 1] = 1;
        a[secondNode][incidencesMatrix.length - 1] = 1;
        this.incidencesMatrix = a;
        // списки смежности
        a = new int[adjacentList.length + 1][2];
        for (int i = 0; i < adjacentList.length; i++)
            for (int j = 0; j < adjacentList[i].length; j++)
                a[i][j] = adjacentList[i][j];
        a[adjacentList.length][0] = firstNode;
        a[adjacentList.length][1] = secondNode;
        this.adjacentList = a;
        // списки дуг
        a = new int[ribList.length + 2][3];
        for (int i = 0; i < ribList.length; i++)
            for (int j = 0; j < ribList[i].length; j++)
                a[i][j] = ribList[i][j];
        a[ribList.length][0] = firstNode;
        a[ribList.length][1] = secondNode;
        a[ribList.length][2] = 1;
        a[ribList.length + 1][0] = secondNode;
        a[ribList.length + 1][1] = firstNode;
        a[ribList.length + 1][2] = 1;
        this.ribList = a;
    }

    public void search() {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        int[] nodes = new int[adjacentMatrix.length]; // вершины графа
        for (int i = 0; i < adjacentMatrix.length; i++)
            nodes[i] = 0; // исходно все вершины равны 0
        queue.add(0); // помещаем в очередь первую вершину
        while (queue.size() != 0) { // пока очередь не пуста
            int node = queue.poll(); // извлекаем вершину
            nodes[node] = 2; // отмечаем ее как посещенную
            for (int j = 0; j < adjacentMatrix.length; j++) { // проверяем для нее все смежные вершины
                if (adjacentMatrix[node][j] == 1 && nodes[j] == 0) { // если вершина смежная и не обнаружена
                    queue.add(j); // добавляем ее в очередь
                    nodes[j] = 1; // отмечаем вершину как обнаруженную
                }
            }
            System.out.println(node);
            ; // выводим номер вершины
        }
    }

    public void center() {
        int N = adjacentMatrix.length; // Количество вершин в графе
        int INF = Integer.MAX_VALUE; // мксимальное
        int[][] d = new int[N][N]; // Дистанции в графе
        int[] e = new int[N]; // Эксцентриситет вершин
        Set<Integer> c = new HashSet<>(); // Центр графа
        int rad = INF; // Радиус графа

        // Алгоритм Флойда-Уоршелла
        for (int k = 0; k < N; k++) {
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        // Нахождение эксцентриситета
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                e[i] = Math.max(e[i], d[i][j]);
            }
        }

        // Нахождение диаметра и радиуса
        for (int i = 0; i < N; i++) {
            rad = Math.min(rad, e[i]);
        }

        for (int i = 0; i < N; i++) {
            if (e[i] == rad) {
                c.add(i);
                System.out.println(i);
            }
        }
    }

    public int[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }

    public void printAdjacentMatrix() {
        for (int i = 0; i < adjacentMatrix.length; i++) {
            for (int j = 0; j < adjacentMatrix[i].length; j++) {
                System.out.print(adjacentMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setAdjacentMatrix(int[][] adjacentMatrix) {
        this.adjacentMatrix = adjacentMatrix;
    }

    public int[][] getIncidencesMatrix() {
        return incidencesMatrix;
    }

    public void printIncidencesMatrix() {
        for (int i = 0; i < incidencesMatrix.length; i++) {
            for (int j = 0; j < incidencesMatrix[i].length; j++) {
                System.out.print(incidencesMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setIncidencesMatrix(int[][] incidencesMatrix) {
        this.incidencesMatrix = incidencesMatrix;
    }

    public int[][] getAdjacentList() {
        return adjacentList;
    }

    public void printAdjacentList() {
        for (int i = 0; i < adjacentList.length; i++) {
            for (int j = 0; j < adjacentList[i].length; j++) {
                System.out.print(adjacentList[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setAdjacentList(int[][] adjacentList) {
        this.adjacentList = adjacentList;
    }

    public int[][] getRibList() {
        return ribList;
    }

    public void printRibList() {
        for (int i = 0; i < ribList.length; i++) {
            for (int j = 0; j < ribList[i].length; j++) {
                System.out.print(ribList[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void setRibList(int[][] ribList) {
        this.ribList = ribList;
    }

    @Override
    public String toString() {
        return "CityGraph{" +
                "adjacentMatrix=" + Arrays.toString(adjacentMatrix) +
                ", incidencesMatrix=" + Arrays.toString(incidencesMatrix) +
                ", adjacentList=" + Arrays.toString(adjacentList) +
                ", ribList=" + Arrays.toString(ribList) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph cityGraph = (Graph) o;
        return Arrays.equals(adjacentMatrix, cityGraph.adjacentMatrix) &&
                Arrays.equals(incidencesMatrix, cityGraph.incidencesMatrix) &&
                Arrays.equals(adjacentList, cityGraph.adjacentList) &&
                Arrays.equals(ribList, cityGraph.ribList);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(adjacentMatrix);
        result = 31 * result + Arrays.hashCode(incidencesMatrix);
        result = 31 * result + Arrays.hashCode(adjacentList);
        result = 31 * result + Arrays.hashCode(ribList);
        return result;
    }
}
