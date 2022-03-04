package edu.mooncoder.controller.services;

import edu.mooncoder.controller.Manager;
import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.tools.types.SortState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SortManager extends Manager {
    private static final String[] header = {"No.", "Apostador", "Monto", "Puntuacion ↓"};

    private final JTable ranking;

    private SortState sortState = SortState.NO_SORTED;
    private boolean normal;

    public SortManager(JTable ranking) {
        this.ranking = ranking;
    }

    private Object[][] getMatrixData(boolean reversed) {
        Apuesta[] orderedBets = (reversed) ? getBets().toReversedArray() : getBets().toArray();

        Object[][] data = new Object[orderedBets.length][];
        int index = 0;

        for (Apuesta bet : orderedBets) {
            data[index++] = new Object[]{index + 1, bet.getApostador(), bet.getMonto(), bet.getScore()};
        }

        return data;
    }

    private void setSortedData(boolean reversed) {
        Object[][] data = getMatrixData(reversed);
        TableModel model = new DefaultTableModel(data, header);

        this.ranking.setModel(model);
        this.ranking.revalidate();
        this.ranking.repaint();

        this.ranking.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = ranking.columnAtPoint(e.getPoint());
                if (col == 1) {
                    header[1] = (!normal) ? "Apostador ↓" : "Apostador ↑";
                    header[3] = "Puntuacion";
                    sort(false);
                } else if (col == 3) {
                    header[1] = "Apostador";
                    header[3] = (!normal) ? "Puntuacion ↓" : "Puntuacion ↑";
                    sort(true);
                }
            }
        });
    }

    public void sort(boolean byScore) {
        SortState properState = SortState.getSortStateBy(byScore);

        if (sortState != properState) {
            getBets().sort(byScore);
        }
        setSortedData(normal);
        normal = sortState != properState || !normal;
        sortState = properState;
    }
}
