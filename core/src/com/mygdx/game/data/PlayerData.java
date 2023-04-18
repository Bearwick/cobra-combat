package com.mygdx.game.data;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private String nickName;
    private List<Vector2> position;
    private List<Float> rotation;
    private Boolean dead;

    public PlayerData() {
        this.nickName = "";
        this.position = new ArrayList<>();
        rotation = new ArrayList<>();
        dead = false;
    }
    public void setDead(Boolean dead){
        this.dead = dead;
    }
    public Boolean getDead(){
        return this.dead;
    }
    public String getNickName() {
        return nickName;
    }
    public List<Vector2> getPosition() {
        return position;
    }
    public List<Float> getRotation(){
        return rotation;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setPosition(List<Vector2> position) {
        this.position = position;
    }

    public void addPosition(Vector2 pos) {
        this.position.add(pos);
    }
    public void addRotation(Float rotation){
        this.rotation.add(rotation);
    }
    public void changePosition(int index, Vector2 pos) {
        position.set(index, pos);
    }
    public void changeRotation(int index, Float rotation){
        this.rotation.set(index, rotation);
    }
    public String toString(){
        String data = "Pos: \n";
        for (int i = 0; i < position.size(); i++){
            data = data.concat(String.format("x: %s, y:%s, rotation: %s\n", position.get(i).x, position.get(i).y, rotation.get(i).toString()));
        }
        return data;
    }
}
