package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;


public class EdibleFactory {
    private Array<Vector2> ediblePositions = new Array<Vector2>(); //Array to help avoid overlapping spawning
    private Vector2 generateRandomPosition(){ //helper method to generate random vector
        Vector2 randomVector = new Vector2();
        int min = 1;

        randomVector.set((int) Math.floor(Math.random() * (31 - min + 1) + min) * MyGdxGame.GRID_CELL_X, (int) Math.floor(Math.random() * (15 - min + 1) + min) * MyGdxGame.GRID_CELL_Y);
        return randomVector;
    }

    public Edible getEdible(String edibleType){     //the factory method

        Vector2 randomPosition = generateRandomPosition();

        while(ediblePositions.contains(randomPosition, false)) {
             //check if an edible is already put in random position. If it is, generate new value.
            randomPosition = generateRandomPosition();
            }

        if(edibleType == null){
            return null;
        }

        if(edibleType.equalsIgnoreCase("APPLE")){ //can implement a similar if-statement to make other edibles
            Edible edible;
            edible = new Apple(randomPosition);
            ediblePositions.add(edible.getPosition());
            return edible;
        }
        return null;
    }



}
