package edu.mooncoder;

// import java.io.FileReader;

// import edu.mooncoder.model.analizer.lexic.Lexer;
// import edu.mooncoder.model.analizer.syntax.Parser;
import edu.mooncoder.model.domain.structures.BetsList;
import edu.mooncoder.model.tools.readers.BetsReader;

public class Main {
    private static String path = "C:/Users/dylan/Desktop/Everything,+but+Nothing/"
            + "Programacion+Eliminar/DevilsDeal/Prueba.txt";

    public static void main(String[] args) {
        try {
            // FileReader reader = new FileReader(path);
            // Lexer lexer = new Lexer(reader);
            // Parser parser = new Parser(lexer);
            
            // parser.parse();
            // parser.getBets().filterBets();
            // parser.getBets().setResults(new int[] { 0, 9, 4, 2, 1, 3, 5, 6, 7, 8 });
            // parser.getBets().printScores();
            // parser.getBets().sort(true);
            // System.out.println();
            // parser.getBets().printScores();

            BetsList list = new BetsReader(path).readBetsFile();
            
            list.filterBets();
            list.setResults(new int[] { 0, 9, 4, 2, 1, 3, 5, 6, 7, 8 });
            list.printScores();
            list.sort(true);
            System.out.println();
            list.printScores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
