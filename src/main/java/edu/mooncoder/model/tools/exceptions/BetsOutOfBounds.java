package edu.mooncoder.model.tools.exceptions;

public class BetsOutOfBounds extends Exception{
    public BetsOutOfBounds(boolean demas) {
        super(String.format("Se leyeron %s de 10 lugares, cuando se debe apostar por "
                + "exactamente los 10 lugares existentes", (demas) ? "mas" : "menos"));
    }
}
