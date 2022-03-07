package edu.mooncoder.controller.managers;

import edu.mooncoder.controller.Manager;
import edu.mooncoder.model.domain.containers.BadBetsDetails;
import edu.mooncoder.model.tools.writers.BetsInfoWriter;

import javax.swing.*;
import java.io.IOException;

public class ResultsManager extends Manager {

    private JFileChooser getFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Directorio para datos de salida");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        return fileChooser;
    }

    private boolean setDirectoryPath() {
        JFileChooser fileChooser = getFileChooser();
        int aprove = fileChooser.showSaveDialog(null);

        if (aprove == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().isDirectory()) {
            BetsInfoWriter.path = fileChooser.getSelectedFile().getAbsolutePath();
            return false;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Directorio escogido, no se guardaran los datos de esta carrera.",
                    "Directorio no escogido", JOptionPane.ERROR_MESSAGE);

            return true;
        }
    }

    public void filterBets() {
        getBets().filterBets();

        if (BetsInfoWriter.path == null) {
            if (setDirectoryPath()) return;
        }
        try {
            BetsInfoWriter.writeBadBetsDetails();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio un error guardando el archivo de detalles.",
                    "Archivo no guardado", JOptionPane.ERROR_MESSAGE);
        }
        BadBetsDetails.clearDetails();
    }

    public void setResults(int[] results) {
        getBets().setResults(results);

        if (BetsInfoWriter.path == null) {
            return;
        }
        try {
            BetsInfoWriter.writeHistorial(getBets().toArray());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Ocurrio un error guardando el archivo de historial.",
                    "Archivo no guardado", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean showRanking() {
        return getBets().getLength() != 0;
    }
}
