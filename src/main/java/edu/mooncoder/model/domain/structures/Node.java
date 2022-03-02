package edu.mooncoder.model.domain.structures;

import edu.mooncoder.model.domain.containers.Apuesta;

class Node {
    private Apuesta data;
    private Node left;
    private Node right;

    Node(Apuesta data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    Node(Apuesta data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Apuesta getData() {
        return data;
    }

    public void setData(Apuesta data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;

        if (left != null)
            left.right = this;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;

        if (right != null)
            right.left = this;
    }

    /**
     * Compara si otro objeto debe de estar en una posicion mayor o menor.
     * Si ambos son iguales regresara un <code>false</code>.
     * 
     * @param node    que se comparara con el nodo que llama el metodo.
     * @param byScore si es <code>true</code> los comparara por score sino por
     *                nombre del apostador
     * @return <code>true</code> si el nodo a comparar es menor, sino
     *         <code>false</code>.
     */
    public boolean isLess(Node node, boolean byScore) {
        if (byScore) {
            return this.getData().getScore() < node.getData().getScore();
        } else {
            return 0 < node.getData().getApostador().compareTo(this.getData().getApostador());
        }
    }

    public void swapWith(Node other) {
        if (this == other) return;

        Node thisLeft = (this.left == other) ? this : this.left;
        Node thisRight = (this.right == other) ? this : this.right;
        Node otherLeft = (other.left == this) ? other : other.left;
        Node otherRight = (other.right == this) ? other : other.right;


        this.setLeft(otherLeft);
        this.setRight(otherRight);

        other.setLeft(thisLeft);
        other.setRight(thisRight);
    }
}
