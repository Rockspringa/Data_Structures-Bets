package edu.mooncoder.controller.managers;

import java.io.File;
import java.io.IOException;

import edu.mooncoder.controller.Manager;
import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.domain.structures.BetsList;
import edu.mooncoder.model.tools.readers.BetsReader;

public class BetsManager extends Manager {
    private BetsReader betsReader;

    public BetsManager() {
        betsReader = new BetsReader();
        clearBets();
    }

    public int[] addBetsInFile(File file) throws IOException {
        betsReader.setPath(file);
        BetsList.concat(getBets(), betsReader.readBetsFile());
        return betsReader.getBetsReport();
    }

    public void addBet(String apostador, String monto, String apuestas) {
        Apuesta bet = betsReader.readIndividualBet(String.format("%s, %s, %s", apostador, monto, apuestas));
        getBets().add(bet);
    }
}
