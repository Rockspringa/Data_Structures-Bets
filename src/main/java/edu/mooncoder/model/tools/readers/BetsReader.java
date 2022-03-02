package edu.mooncoder.model.tools.readers;

import java.io.IOException;

import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.domain.structures.BetsList;

public class BetsReader {
    private final StringFileContent file;
    private int betsRemoved;

    public BetsReader(String path) throws IOException {
        this.file = new StringFileContent(path);
    }

    public BetsList readBetsFile() {
        BetsList list = new BetsList();

        for (String line : file.getContent().split("[\n\r]+")) {
            try {
                list.add(readIndividualBet(line));
            } catch (Exception e) {
                betsRemoved++;
            }
        }

        return list;
    }

    public Apuesta readIndividualBet(String bet) {
        String[] data = bet.split(",[ \f\t]*");

        String apostador = data[0];
        double monto = Double.parseDouble(data[1]);
        int[] apuestas = new int[data.length - 2];

        for (int i = 0; i < apuestas.length; i++) {
            apuestas[i] = Integer.parseInt(data[i + 2]) - 1;
        }

        return new Apuesta(apostador, monto, apuestas);
    }

    public int getBetsRemoved() {
        return betsRemoved;
    }
}
