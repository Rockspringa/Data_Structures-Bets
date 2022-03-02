package edu.mooncoder.model.domain.containers;

public class Apuesta {
    private final String apostador;
    private final double monto;
    private final int[] apuestas;

    private int score;
    
    public Apuesta(String apostador, double monto, int[] apuestas) {
        this.apostador = apostador;
        this.monto = monto;
        this.apuestas = apuestas;
    }

    public String getApostador() {
        return apostador;
    }

    public double getMonto() {
        return monto;
    }

    public int[] getApuestas() {
        return apuestas;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
