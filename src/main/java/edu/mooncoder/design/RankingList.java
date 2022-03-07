package edu.mooncoder.design;

import edu.mooncoder.controller.managers.SortManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankingList extends JFrame {
    private JScrollPane tableScroller;
    private JPanel principal;
    private JLabel titleLabel;
    private JButton atrasBtn;
    private JTable rankingTable = new JTable();
    private SortManager sortManager;
    private JFrame parent;

    public RankingList(JFrame parent) {
        super("Score Ranking");
        this.setContentPane(principal);

        this.parent = parent;
        this.tableScroller = new JScrollPane(rankingTable);
        this.tableScroller.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.tableScroller.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        this.add(tableScroller, BorderLayout.CENTER);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        atrasBtn.addActionListener(e -> dispose());
    }

    @Override
    public void dispose() {
        super.dispose();
        parent.setVisible(true);
        parent.setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(parent, "Esta por iniciar otra carrera, puede volver a apostar",
                "Informacion nueva carrera", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            sortManager = new SortManager(rankingTable);
            sortManager.sort(true);
        }
        this.pack();
        super.setVisible(b);
    }
}
