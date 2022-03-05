package edu.mooncoder.controller.managers;

import edu.mooncoder.controller.Manager;

public class ResultsManager extends Manager {

    public ResultsManager(int[] results) {
        // TODO: Agregar mandar datos de tiempo y pasos a reportes
        getBets().filterBets();
        getBets().setResults(results);
    }
}
