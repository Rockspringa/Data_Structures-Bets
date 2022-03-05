package edu.mooncoder.design;

import edu.mooncoder.controller.managers.ReportManager;
import edu.mooncoder.model.tools.types.ReportType;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.util.List;

public class ReportVisualizer extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel titleLbl;
    private JTabbedPane tabbedPane1;
    private JPanel verificationPanel;
    private JPanel resultsPanel;
    private JPanel sortPanel;
    private JList<String> attrVerList;
    private JList<String> dataVerList;
    private JList<String> attrResList;
    private JList<String> dataResList;
    private JList<String> attrSorList;
    private JList<String> dataSorList;

    public ReportVisualizer() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(480, 230));

        setData();
        buttonOK.addActionListener(e -> dispose());

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setData() {
        setData(dataVerList, ReportType.VERIFICATION_REPORT);
        setData(dataResList, ReportType.RESULTS_REPORT);
        setData(dataSorList, ReportType.SORT_REPORT);
    }

    private void setData(JList<String> list, ReportType type) {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addAll(List.of(ReportManager.getData(type)));

        list.setModel(model);
    }
}
