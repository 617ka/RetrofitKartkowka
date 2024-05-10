package com.example.retrofitkartkowka;

public class Planszowki {

    private String nazwa;
    private int minimWiek;
    private int minLG;
    private int maxLG;
    private int czasGryWMin;

    public String getNazwa() {
        return nazwa;
    }

    public int getMinimWiek() {
        return minimWiek;
    }

    public int getMinLG() {
        return minLG;
    }

    public int getMaxLG() {
        return maxLG;
    }

    public int getCzasGryWMin() {
        return czasGryWMin;
    }

    @Override
    public String toString() {
        return "Planszowka: " + nazwa
                +"\nMinimalny wiek: " + minimWiek
                +"\nMinimalna liczba graczy: " + minLG
                +"\nMaksymalna liczba graczy: " + maxLG
                +"\nCzas gry: " + czasGryWMin;
    }
}
