package edu.mooncoder.model.domain.structures;

import edu.mooncoder.model.domain.containers.Apuesta;
import edu.mooncoder.model.tools.exceptions.BetsOutOfBounds;
import edu.mooncoder.model.tools.exceptions.NotADigitException;
import edu.mooncoder.model.tools.exceptions.RepeatedDigitExpection;

public class BetsList {
    private Node root;
    private int length;

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

    public static void concat(BetsList first, BetsList second) {
        if (second.length == 0) return;

        Node nodeToAdd = second.root.getLeft();
        for (int i = 0; i < second.length; i++) {
            first.add(nodeToAdd.getData());
            nodeToAdd = nodeToAdd.getLeft();
        }
    }

    public void filterBets() {
        Node anchor = root;

        long time = System.currentTimeMillis();

        int lastLength = length;
        for (int item = 0; item < lastLength; item++) {
            DigitSet set = new DigitSet();

            int[] caballos = anchor.getData().getApuestas();

            try {
                if (caballos.length != 10)
                    throw new BetsOutOfBounds(10 < caballos.length);

                for (int i = 0; i < 10; i++)
                    set.add(caballos[i]);
            } catch (NotADigitException | RepeatedDigitExpection | BetsOutOfBounds e) {
                if (length > 1) {
                    anchor.getLeft().setRight(anchor.getRight());
                    if (anchor == root)
                        root = anchor.getRight();
                } else if (length == 1) {
                    root = null;
                }
                length--;
            }
            anchor = anchor.getRight();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }

    public void setResults(int[] results) {
        Node anchor = root;

        long time = System.currentTimeMillis();

        for (int n = 0; n < length; n++) {
            int[] caballos = anchor.getData().getApuestas();
            int score = 0, puntos = 10;

            for (int i = 0; i < 10; i++) {
                if (caballos[i] == results[i]) {
                    score += puntos;
                }
                puntos--;
            }

            anchor.getData().setScore(score);
            anchor = anchor.getRight();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }

    public void sort(boolean byScore) {
        Node anchor = root;

        for (int i = 0; i < length - 1; i++) {
            Node min = anchor;
            Node searcher = anchor.getRight();

            while (searcher != root) {
                if (searcher.isLess(min, byScore)) {
                    min = searcher;
                }
                searcher = searcher.getRight();
            }

            if (anchor == root && anchor != min) {
                root = min;
            }
            min.swapWith(anchor);
            anchor = min.getRight();
        }
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
