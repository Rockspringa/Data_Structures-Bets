package edu.mooncoder;

import edu.mooncoder.design.BettingHouse;

import javax.swing.SwingUtilities;

// import java.io.FileReader;

// import edu.mooncoder.model.analizer.lexic.Lexer;
// import edu.mooncoder.model.analizer.syntax.Parser;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BettingHouse();
            }
        });
    }
}
