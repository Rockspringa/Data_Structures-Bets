package edu.mooncoder.model.tools.readers;

import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.domain.structures.BetsList;

import java.io.File;
import java.io.IOException;

public class BetsReader {
    private StringFileContent file;
    private int[] betsReport = new int[] { 0, 0 };

    public void setPath(File file) throws IOException {
        this.file = new StringFileContent(file);
    }

    public BetsList readBetsFile() {
        if (file == null) return null;
        BetsList list = new BetsList();
        String[] linesData = file.getContent().split("[\n\r]+");
        betsReport[0] = linesData.length;
        betsReport[1] = 0;

        for (String line : linesData) {
            try {
                list.add(readIndividualBet(line));
            } catch (Exception e) {
                betsReport[1]++;
                betsReport[0]--;
            }
        }

        return list;
    }

    public Apuesta readIndividualBet(String bet) {
        String[] data = bet.split(",[ \f\t]*");


        String apostador = data[0];
        double monto;
        try {
            monto = Double.parseDouble(data[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Monto");
        }
        int[] apuestas = new int[data.length - 2];

        for (int i = 0; i < apuestas.length; i++) {
            try {
                apuestas[i] = Integer.parseInt(data[i + 2]) - 1;
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Pos");
            }
        }

        return new Apuesta(apostador, monto, apuestas);
    }

    public int[] getBetsReport() {
        return betsReport;
    }
}
