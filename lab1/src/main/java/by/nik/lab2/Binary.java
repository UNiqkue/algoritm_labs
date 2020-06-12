package by.nik.lab2;

import java.util.Objects;

public class Binary {
    private int count;
    private int index;

    public Binary(int count, int index) {
        this.count = count;
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getResult() {
        return index;
    }

    public void setResult(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Binary binary = (Binary) o;
        return count == binary.count &&
                index == binary.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, index);
    }
}
