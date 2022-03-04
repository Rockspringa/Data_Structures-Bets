package edu.mooncoder.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

public interface Theme {
    Font BOLDER = new Font("JRoboto Slab Bold", Font.PLAIN, 13);
    Font BOLD = new Font("JetBrains Mono Bold", Font.PLAIN, 13);
    Font Normal = new Font("JetBrains Mono", Font.PLAIN, 13);

    Color primaryBg = new Color(33, 37, 42);
    Color primaryFg = new Color(171, 178, 192);
    Color secondaryBg = new Color(61, 66, 75);
    Color secondaryFg = new Color(160, 167, 181);
    Color thirdBg = new Color(40, 44, 51);

    default void runUIManager() {
        UIManager.put("Label.font", BOLD);
        UIManager.put("Button.font", BOLDER);
        UIManager.put("TextPane.font", Normal);

        UIManager.put("Label.background", primaryBg);
        UIManager.put("Label.foreground", primaryFg);
        UIManager.put("Label.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));

        UIManager.put("ScrollBar.thumbDarkShadow", primaryBg);
        UIManager.put("ScrollBar.thumbHighlight", secondaryBg);
        UIManager.put("ScrollBar.thumbShadow", secondaryBg);
        UIManager.put("ScrollBar.background", primaryBg);
        UIManager.put("ScrollBar.thumb", secondaryFg);
        UIManager.put("ScrollBar.track", primaryBg);
        UIManager.put("ScrollBar.width", 12);

        UIManager.put("Panel.background", primaryBg);
        UIManager.put("Frame.background", primaryBg);

        UIManager.put("TableHeader.background", secondaryBg);
        UIManager.put("TableHeader.foreground", secondaryFg);
        UIManager.put("Table.background", thirdBg);
        UIManager.put("Table.foreground", secondaryFg);
        UIManager.put("Table.gridColor", primaryBg);

        UIManager.put("OptionPane.background", primaryBg);
        UIManager.put("OptionPane.foreground", primaryFg);
        UIManager.put("OptionPane.messageForeground", Color.lightGray);

        UIManager.put("ComboBox.background", primaryBg);
        UIManager.put("ComboBox.foreground", primaryFg);

        UIManager.put("List.background", primaryBg);
        UIManager.put("List.foreground", primaryFg);

        UIManager.put("TextField.background", thirdBg);
        UIManager.put("TextField.foreground", secondaryFg);

        UIManager.put("TextArea.background", thirdBg);
        UIManager.put("TextArea.foreground", secondaryFg);

        UIManager.put("ScrollPane.background", primaryBg);
        UIManager.put("ScrollPane.foreground", primaryFg);

        UIManager.put("Button.background", secondaryBg);
        UIManager.put("Button.foreground", secondaryFg);
        UIManager.put("Button.select", thirdBg);
        UIManager.put("Button.focus", Color.DARK_GRAY);
        UIManager.put("Button.border", BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

}
