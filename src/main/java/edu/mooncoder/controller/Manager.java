package edu.mooncoder.controller;

import edu.mooncoder.model.domain.structures.BetsList;

public abstract class Manager {
    private static BetsList bets = new BetsList();

    protected BetsList getBets() {
        return bets;
    }

    protected void clearBets() {
        bets = new BetsList();
    }
}
