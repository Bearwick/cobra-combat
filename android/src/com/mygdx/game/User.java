package com.mygdx.game;

import java.util.ArrayList;

public class User {
    private String nickName;
    private int score;
    private ArrayList<ArrayList<Integer>> positions;
    private ArrayList<Integer> posXY;

    public User(String nickName) {
        posXY = new ArrayList<Integer>();

        if (nickName.equals("Player2")) {
            posXY.set(0,20);
            posXY.set(1,20);
        } else {
            posXY.set(0,1);
            posXY.set(1,20);
        }


        this.nickName = nickName;
        this.score = 0;
        this.positions.set(0, posXY);
    }
}
