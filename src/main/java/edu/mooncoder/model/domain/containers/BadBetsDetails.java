package edu.mooncoder.model.domain.containers;

import java.util.ArrayList;

public class BadBetsDetails {
    private static ArrayList<BadBetsDetails> allDetails = new ArrayList<>();

    private final String name;
    private final String details;

    public BadBetsDetails(String name, String details) {
        this.name = name;
        this.details = details;

        allDetails.add(this);
    }

    public static void clearDetails() {
        allDetails = new ArrayList<>();
    }

    public static BadBetsDetails[] getAllDetails() {
        return allDetails.toArray(new BadBetsDetails[0]);
    }

    @Override
    public String toString() {
        return "Apostador = '" + name + "', Razon = '" + details + '\'';
    }
}
