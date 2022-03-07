package edu.mooncoder.model.tools.writers;

import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.domain.containers.BadBetsDetails;

import java.io.*;

public class BetsInfoWriter {
    private static int fileDetailsNum = 1;
    private static int fileHistorialNum = 1;
    public static String path;

    public static void writeBadBetsDetails() throws IOException {
        File file = new File(path, String.format("rechazadas_%d.txt", fileDetailsNum));
        BadBetsDetails[] betsDetails = BadBetsDetails.getAllDetails();
        if (betsDetails.length == 0) return;

        try (
                FileOutputStream stream = new FileOutputStream(file);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        ) {
            for (BadBetsDetails details : betsDetails) {
                writer.write(details.toString());
                writer.newLine();
            }
            fileDetailsNum++;
        }
    }

    public static void writeHistorial(Apuesta[] list) throws IOException {
        if (list.length == 0) return;
        File file = new File(path, String.format("historial_%d.txt", fileHistorialNum));

        try (
                FileOutputStream stream = new FileOutputStream(file);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
        ) {
            for (Apuesta bet : list) {
                writer.write(String.format("%s, %.2f, %d", bet.getApostador(), bet.getMonto(), bet.getScore()));
                writer.newLine();
            }
            fileHistorialNum++;
        }
    }
}
