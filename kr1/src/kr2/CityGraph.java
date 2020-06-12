package sample;

import java.util.Objects;

public class CityGraph {
    private int node;
    private int rib;

    public CityGraph() {
    }

    public CityGraph(int node, int rib) {
        this.node = node;
        this.rib = rib;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getRib() {
        return rib;
    }

    public void setRib(int rib) {
        this.rib = rib;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityGraph cityGraph = (CityGraph) o;
        return node == cityGraph.node &&
                rib == cityGraph.rib;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, rib);
    }
}
