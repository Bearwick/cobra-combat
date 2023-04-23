package com.mygdx.game.edibles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

import java.util.ArrayList;

public class EdibleFactory {

    private final ArrayList<Edible> edibleArray = new ArrayList<>();
    private final Array<Vector2> ediblePositions = new Array<>(); //Array to help avoid overlapping spawning

    private Vector2 generateRandomPosition() { //helper method to generate random vector
        Vector2 randomVector = new Vector2();
        randomVector.set((int) Math.floor(Math.random() * (MyGdxGame.CELL_RATIO - 1)) * MyGdxGame.GRID_CELL_X, (int) Math.floor(Math.random() * (MyGdxGame.CELL_RATIO / 2 - 1)) * MyGdxGame.GRID_CELL_Y);
        return randomVector;
    }

    public Edible getEdible(String edibleType) {     //the factory method

        Vector2 randomPosition = generateRandomPosition();

        while (ediblePositions.contains(randomPosition, false)) {
            //check if an edible is already put in random position. If it is, generate new value.
            randomPosition = generateRandomPosition();
        }

        if (edibleType == null) {
            return null;
        }

        if (edibleType.equalsIgnoreCase("APPLE")) { //can implement a similar if-statement to make other edibles
            Edible edible;
            edible = new Apple(randomPosition);
            ediblePositions.add(edible.getPosition());
            edibleArray.add(edible);
            return edible;
        }
        if (edibleType.equalsIgnoreCase("RAINBOW")) {
            Edible edible;
            edible = new Rainbow(randomPosition);
            ediblePositions.add(edible.getPosition());
            edibleArray.add(edible);
            return edible;
        }
        return null;
    }

    public ArrayList<Edible> getEdibleArray() {
        return edibleArray;
    }
}
