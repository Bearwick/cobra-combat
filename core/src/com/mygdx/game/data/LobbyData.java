package com.mygdx.game.data;

public class LobbyData {

    private String player1;
    private String player2;

    public LobbyData() {
        setPlayer1("none");
        setPlayer2("none");
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        if (player1.equals(""))
            this.player1 = "Player1";
        else
            this.player1 = player1;
        if (this.player1.equals(this.player2) && !this.player1.equals("none"))
            this.player1 = player1 + "2";
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {

        if (player2.equals(""))
            this.player2 = "Player2";
        else
            this.player2 = player2;
        if (this.player2.equals(this.player1) && !this.player2.equals("none"))
            this.player2 = player2 + "2";
    }

    public String toString() {
        return (String.format("player1: %s, player2: %s, isStarted: %s", player1, player2));
    }
}

