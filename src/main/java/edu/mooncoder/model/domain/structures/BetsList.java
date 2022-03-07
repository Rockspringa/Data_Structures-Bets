package edu.mooncoder.model.domain.structures;

import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.domain.containers.BadBetsDetails;
import edu.mooncoder.model.domain.containers.reports.ResultsReport;
import edu.mooncoder.model.domain.containers.reports.SortReport;
import edu.mooncoder.model.domain.containers.reports.VerificationReport;
import edu.mooncoder.model.tools.exceptions.BetsOutOfBounds;
import edu.mooncoder.model.tools.exceptions.NotADigitException;
import edu.mooncoder.model.tools.exceptions.RepeatedDigitExpection;

public class BetsList {
    private Node root;
    private int length;

    public static void concat(BetsList first, BetsList second) {
        if (second.length == 0) return;

        Node nodeToAdd = second.root.getLeft();
        for (int i = 0; i < second.length; i++) {
            first.add(nodeToAdd.getData());
            nodeToAdd = nodeToAdd.getLeft();
        }
    }

    public int getLength() {
        return length;
    }

    public void add(Apuesta data) {
        Node nodo = new Node(data);

        if (root != null) {
            Node leaf = root.getLeft();
            root.setLeft(nodo);
            nodo.setLeft(leaf);
        } else {
            nodo.setLeft(nodo);
        }
        root = nodo;
        length++;
    }

    public void filterBets() {
        Node anchor = root;
        long time = System.currentTimeMillis();
        int pasos = 2; // 1. anchor = root; 2. item = 0;

        int lastLength = length;
        for (int item = 0; item < lastLength; item++) {
            DigitSet set = new DigitSet();

            int[] caballos = anchor.getData().getApuestas();

            try {
                if (caballos.length != 10)
                    throw new BetsOutOfBounds(10 < caballos.length);

                pasos += 6; // 2 del for por iteracion + 2 de asignacion + 1 comparacion + i = 0
                for (int i = 0; i < 10; i++) {
                    set.add(caballos[i]);
                    pasos += 3; // 2 del for y 1 de set.add(...)
                }
            } catch (NotADigitException | RepeatedDigitExpection | BetsOutOfBounds e) {
                new BadBetsDetails(anchor.getData().getApostador(), e.getMessage());
                if (length > 1) {
                    anchor.getLeft().setRight(anchor.getRight());
                    if (anchor == root) {
                        root = anchor.getRight();
                        pasos++; // ↑
                    }
                } else if (length == 1) {
                    root = null;
                    pasos++; // ↑
                }
                length--;
                pasos += 3; // 2 comparaciones o 1 comp... y anchor.get... y length--
            }
            anchor = anchor.getRight();
            pasos++; // ↑
        }

        VerificationReport.getInstance().addData(System.currentTimeMillis() - time, pasos);
    }

    public void setResults(int[] results) {
        Node anchor = root;
        int pasos = 1; // asignacion for externo
        long time = System.currentTimeMillis();

        for (int n = 0; n < length; n++) {
            int[] caballos = anchor.getData().getApuestas();
            int score = 0, puntos = 10;

            pasos += 8; // 2 del for, 3 asignaciones, 1 del for interno, lo ultimo del for
            for (int i = 0; i < 10; i++) {
                pasos += 4; // dos del for, 1 comparacion, puntos--
                if (caballos[i] == results[i]) {
                    pasos++; // ↓
                    score += puntos;
                }
                puntos--;
            }

            anchor.getData().setScore(score);
            anchor = anchor.getRight();
        }
        ResultsReport.getInstance().addData(System.currentTimeMillis() - time, pasos);
    }

    public void sort(boolean byScore) {
        Node anchor = root;
        int pasos = 2; // ↑, i = 0
        long time = System.currentTimeMillis();

        for (int i = 0; i < length - 1; i++) {
            Node min = anchor;
            Node searcher = anchor.getRight();

            pasos += 5; // ↑ x 2 asignaciones, if despues del while, 2 asignaciones
            while (searcher != root) {
                if (searcher.isLess(min, byScore)) {
                    min = searcher;
                    pasos++; // ↑
                }
                searcher = searcher.getRight();
                pasos += 3; // while, if, ↑
            }

            if (anchor == root && anchor != min) {
                root = min;
            }
            min.swapWith(anchor);
            anchor = min.getRight();
        }
        SortReport.getInstance().addData(System.currentTimeMillis() - time, pasos);
    }

    public Apuesta[] toArray() {
        Apuesta[] apuestas = new Apuesta[length];
        Node anchor = root;

        for (int i = 0; i < apuestas.length; i++) {
            apuestas[i] = anchor.getData();
            anchor = anchor.getRight();
        }

        return apuestas;
    }

    public Apuesta[] toReversedArray() {
        Apuesta[] apuestas = new Apuesta[length];
        Node anchor = root.getLeft();

        for (int i = 0; i < apuestas.length; i++) {
            apuestas[i] = anchor.getData();
            anchor = anchor.getLeft();
        }

        return apuestas;
    }
}
