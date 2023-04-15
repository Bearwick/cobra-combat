package com.mygdx.game.data;

public class LobbyData {

    private String player1;
    private String player2;


    public LobbyData(String player1, String player2) {
        setPlayer1(player1);
        setPlayer2(player2);
    }
    public LobbyData() {
    }
    public void setPlayer1(String player1) {
        if(player1.equals(""))
            this.player1="Player1";
        else
            this.player1 = player1;
    }
    public void setPlayer2(String player2) {

        if(player2.equals(""))
            this.player2="Player2";
        else
            this.player2 = player2;
    }
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }


}

