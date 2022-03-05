package edu.mooncoder.model.domain.containers;

public class Report {
    private long totalPasos = 0;
    private long totalTime = 0;
    private int cantVerificiones = 0;
    private int maxPasos = 0;
    private int minPasos = Integer.MAX_VALUE;

    public void addData(long time, int pasos) {
        if (maxPasos < pasos)
            maxPasos = pasos;
        if (minPasos > pasos)
            minPasos = pasos;

        totalTime += time;
        totalPasos += pasos;
        cantVerificiones++;
    }

    public long getPromedioPasos() {
        return totalPasos / ((cantVerificiones != 0) ? cantVerificiones : 1);
    }

    public long getPromedioTime() {
        return totalTime / ((cantVerificiones != 0) ? cantVerificiones : 1);
    }

    public int getMaxPasos() {
        return maxPasos;
    }

    public int getMinPasos() {
        return minPasos;
    }
}
