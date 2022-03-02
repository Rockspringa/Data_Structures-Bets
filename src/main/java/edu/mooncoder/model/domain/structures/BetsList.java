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
            root.setLeft(nodo);
        }
        root = nodo;
        length++;
    }

    public void filterBets() {
        Node anchor = root;

        long time = System.currentTimeMillis();

        while (anchor != null) {
            DigitSet set = new DigitSet();

            int[] caballos = anchor.getData().getApuestas();

            try {
                if (caballos.length != 10)
                    throw new BetsOutOfBounds(10 < caballos.length);

                for (int i = 0; i < 10; i++)
                    set.add(caballos[i]);
            } catch (NotADigitException | RepeatedDigitExpection | BetsOutOfBounds e) {
                if (anchor.getLeft() != null)
                    anchor.getLeft().setRight(anchor.getRight());
            }

            anchor = anchor.getRight();
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }

    public void sort(boolean byScore) {
        Node anchor = root;

        while (anchor.getRight() != null) {
            Node min = anchor;
            Node searcher = anchor.getRight();

            while (searcher != null) {
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
}
