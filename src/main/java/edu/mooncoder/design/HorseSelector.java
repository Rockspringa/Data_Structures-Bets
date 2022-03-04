package edu.mooncoder.design;

import edu.mooncoder.controller.services.ResultsManager;

import javax.swing.*;

public class HorseSelector extends JDialog {
    private static int[] posiciones;
    private static Object[] horses = { 1 };
    private static int index;
    private static JFrame parent;

    private JPanel contentPane;
    private JButton buttonOK;
    private JSpinner horseSelected;
    private JLabel messageLbl;
    private JFrame rankingList;

    public HorseSelector(JFrame p) {
        posiciones = new int[] { 0, 0, 0, 0, 0, 0 ,0 ,0, 0, 0 };
        horses = new Object[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        index = 0;
        parent = p;
        createDialog();
    }

    public HorseSelector() {
        createDialog();
    }

    private void createDialog() {
        messageLbl.setText(String.format("¿Que caballo quedo en %dº lugar?", index + 1));
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        SpinnerListModel model = new SpinnerListModel(horses);
        horseSelected.setModel(model);

        buttonOK.addActionListener(e -> action());

        this.pack();
        this.setVisible(true);
    }

    private void action() {
        int value = (int) horseSelected.getValue();
        posiciones[index++] = value - 1;

        Object[] tmp = new Object[horses.length - 1];
        for (int i = 0, j = 0; i < horses.length; i++) {
            if ((int) horses[i] != value)
                tmp[j++] = (int) horses[i];
        }
        horses = tmp;
        this.dispose();

        if (index < 9) {
            new HorseSelector();
        } else {
            posiciones[index++] = (int) horses[0] - 1;
            new ResultsManager(posiciones);

            if (rankingList == null) {
                rankingList = new RankingList(parent);
            } else {
                rankingList.setVisible(true);
                rankingList.setLocationRelativeTo(null);
            }
        }
    }

    public static int[] getPosiciones() {
        return posiciones;
    }
}
