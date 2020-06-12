package by.nik.lab6;

public class Main {

    public static void main(String[] args) {

        int[][] adjacentMatrix = {{0, 1, 0, 1}, //матрица смежности
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}};
        int[][] incidencesMatrix = {{1, 0, 0, 1}, // матрица инцидентности
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 1}};
        int[][] adjacentList = {{0, 1}, {1, 2}, {2, 3}, {3, 0}}; // списки смежности
        int[][] ribList = {{0, 1, 1},
                {0, 3, 1},
                {1, 2, 1},
                {1, 0, 1},
                {2, 1, 1},
                {2, 3, 1},
                {3, 2, 1},
                {3, 0, 1}}; // списки дуг

        Graph graph = new Graph(adjacentMatrix, incidencesMatrix, adjacentList, ribList);
        Graph graph2Copy = new Graph(graph);

        System.out.println("******");
        graph2Copy.printAdjacentMatrix();
        System.out.println("******");
        graph2Copy.printIncidencesMatrix();
        System.out.println("******");
        graph2Copy.printAdjacentList();
        System.out.println("******");
        graph2Copy.printRibList();
        System.out.println("******");
        System.out.println("После добавления");
        graph2Copy.addNode();
        graph2Copy.addRib(0, 4);
        System.out.println("******");
        graph2Copy.printAdjacentMatrix();
        System.out.println("******");
        graph2Copy.printIncidencesMatrix();
        System.out.println("******");
        graph2Copy.printAdjacentList();
        System.out.println("******");
        graph2Copy.printRibList();

        System.out.println("******");
        graph2Copy.search();
        System.out.println("******");
        graph2Copy.center();
    }
}
