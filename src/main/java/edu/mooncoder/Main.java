package edu.mooncoder;

import java.io.FileReader;

import edu.mooncoder.model.analizer.lexic.Lexer;
import edu.mooncoder.model.analizer.syntax.Parser;

public class Main {
    private static String path = "C:/Users/dylan/Desktop/Everything,+but+Nothing/"
            + "Programacion+Eliminar/DevilsDeal/Prueba.txt";

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader(path);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            
            parser.parse();
            parser.getBets().sort(false);
            parser.getBets().filterBets();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
