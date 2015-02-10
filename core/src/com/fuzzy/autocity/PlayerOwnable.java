package com.fuzzy.autocity;

public abstract class PlayerOwnable {

    protected Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
