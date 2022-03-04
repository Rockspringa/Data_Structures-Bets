package edu.mooncoder.model.domain.containers;

public class Reporte {
    private static Reporte reporte;
    private long totalPasos = 0;
    private long totalTime = 0;
    private int cantVerificiones = 0;
    private int maxPasos = 0;
    private int minPasos = 0;

    public static void clear() {
        reporte = null;
    }

    public static Reporte getInstance() {
        if (reporte == null) {
            reporte = new Reporte();
        } return reporte;
    }

    public void addVerificacionData(long time, int pasos) {
        if (maxPasos < pasos)
            maxPasos = pasos;
        if (minPasos > pasos)
            minPasos = pasos;

        totalTime += time;
        totalPasos += pasos;
        cantVerificiones++;
    }

    public long getPromedioPasos() {
        return totalPasos / cantVerificiones;
    }

    public long getPromedioTime() {
        return totalTime / cantVerificiones;
    }

    public int getMaxPasos() {
        return maxPasos;
    }

    public int getMinPasos() {
        return minPasos;
    }
}
