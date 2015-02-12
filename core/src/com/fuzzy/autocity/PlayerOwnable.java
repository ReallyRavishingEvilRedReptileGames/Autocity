package com.fuzzy.autocity;

public abstract class PlayerOwnable {

    protected Player player;

    @Invokable
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Invokable
    public Player getPlayer() {
        return player;
    }
}
