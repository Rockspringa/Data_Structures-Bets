package edu.mooncoder.design;

import edu.mooncoder.controller.managers.BetsManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BettingHouse extends JFrame implements Theme {
    private BetsManager house;
    private JPanel principal;
    private JTextField apostadorTextField;
    private JTextField montoTextField;
    private JTextField posicionesTextField;
    private JLabel apostadorLabel;
    private JLabel montoLabel;
    private JLabel posicionesLabel;
    private JButton addBetBtn;
    private JLabel titleLabel;
    private JTextPane descriptionPanel;
    private JMenuBar menuBar;
    private JMenuItem betsFileBtn;
    private JMenuItem closeBetsBtn;
    private JMenuItem reportsBtn;

    public BettingHouse() {
        super("Betting House");
        this.setContentPane(principal);
        this.setMinimumSize(new Dimension(450, 370));
        this.setBounds(0, 0, 450, 370);
        this.addDesign();
        this.runUIManager();
        this.addListeners();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addDesign() {
        apostadorTextField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        montoTextField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        posicionesTextField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        this.menuBar = new JMenuBar();
        this.menuBar.add((betsFileBtn = new JMenuItem("Cargar Archivo")));
        this.menuBar.add(new JSeparator(JSeparator.VERTICAL));
        this.menuBar.add((closeBetsBtn = new JMenuItem("Cerrar Apuestas")));
        this.menuBar.add(new JSeparator(JSeparator.VERTICAL));
        this.menuBar.add((reportsBtn = new JMenuItem("Mostrar Reportes")));
        this.setJMenuBar(menuBar);

        this.menuBar.setBackground(secondaryBg);
        this.betsFileBtn.setBackground(thirdBg);
        this.closeBetsBtn.setBackground(thirdBg);
        this.reportsBtn.setBackground(thirdBg);
        this.betsFileBtn.setForeground(secondaryFg);
        this.closeBetsBtn.setForeground(secondaryFg);
        this.reportsBtn.setForeground(secondaryFg);
        this.betsFileBtn.setHorizontalAlignment(JMenuItem.CENTER);
        this.closeBetsBtn.setHorizontalAlignment(JMenuItem.CENTER);
        this.reportsBtn.setHorizontalAlignment(JMenuItem.CENTER);
    }

    private void addListeners() {
        addBetBtn.addActionListener(e -> addBet());

        betsFileBtn.addActionListener(e -> addBetsByFile());

        reportsBtn.addActionListener(e -> new ReportVisualizer());

        closeBetsBtn.addActionListener(e -> {
            new HorseSelector(this);
            this.dispose();
        });
    }

    private void addBet() {
        String apostador = apostadorTextField.getText();
        String monto = montoTextField.getText();
        String pos = posicionesTextField.getText();

        if (apostador.isBlank() || monto.isBlank() || pos.isBlank()) {
            String message = "Debe llenar todos los campos.", title = "Advertencia";
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                house.addBet(apostador, monto, pos);

                String message = String.format("Se agrego con exito su apuesta, '%s'.", apostador);
                String title = "Aprovado";
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                String attr = ex.getMessage().contains("Monto") ? "Monto" : "Posiciones";
                String type = ex.getMessage().contains("Monto") ? "decimal" : "entero";
                String message = String.format("El atributo '%s' no tiene un numero %s valido.", attr, type);
                String title = "Advertencia";
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addBetsByFile() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int aprove = fileChooser.showOpenDialog(this);
            if (aprove == JFileChooser.APPROVE_OPTION) {
                int[] removidos = house.addBetsInFile(fileChooser.getSelectedFile());

                String message = String.format("Se agregaron con exito %d apuestas, se removieron %d lineas "
                        + "que tenian datos no validos (ej: texto en lugar de numeros).", removidos[0], removidos[1]);
                String title = "Reporte del archivo de apuestas";
                JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrio un error abriendo el archivo, vuelva a intentarlo",
                    "Archivo no abierto", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            house = new BetsManager();
        }
    }
}
