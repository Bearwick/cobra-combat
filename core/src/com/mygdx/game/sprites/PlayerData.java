package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private String nickName;
    private List<Vector2> position;
    private int score;

    public PlayerData() {
        this.nickName = "";
        this.position = new ArrayList<>();
        this.score = 0;
    }
    public PlayerData(String name, ArrayList<Vector2> position, int score) {
        this.nickName = name;
        this.position = position;
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }
    public List<Vector2> getPosition() {
        return position;
    }
    public int getScore() {
        return score;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setPosition(List<Vector2> position) {
        this.position = position;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void addPosition(Vector2 pos) {
        this.position.add(pos);
    }
    public void changePosition(int index, Vector2 pos) {
        position.set(index, pos);
    }
}
