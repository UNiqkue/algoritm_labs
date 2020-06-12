package sample;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Menu.startGraph();
        } catch (IOException e) {
            System.err.println("Видимо что-то не то ввели: " + e.getMessage());
        }
    }
}
