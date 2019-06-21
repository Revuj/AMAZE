package com.grupo73.proj1.ModelTests;

import com.grupo73.proj1.Controller.CommandInput.Position;
import com.grupo73.proj1.Model.GameModel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GameModelTest {

    private GameModel gameModel;
    private Set<Position> wallsPositions;

    @Before
    public void setup_test() throws IOException {
        gameModel = new GameModel();
        wallsPositions= new HashSet<>();
        wallsPositions.add(new Position(3,6));
        wallsPositions.add(new Position(29,6));
        wallsPositions.add(new Position(37,6));
        wallsPositions.add(new Position(47,6));
        wallsPositions.add(new Position(3,7));
        wallsPositions.add(new Position(8,7));
        wallsPositions.add(new Position(37,7));
        wallsPositions.add(new Position(47,7));
        wallsPositions.add(new Position(3,9));
        wallsPositions.add(new Position(8,9));
        wallsPositions.add(new Position(3,16));

    }

    @Test
    public void testLevel() {
        assertEquals(1,gameModel.getLevel());
    }


    @Test
    public void exitPositionLevel1() {
        assertEquals(gameModel.getExit().getPosition().getY(),17);
        assertEquals(gameModel.getExit().getPosition().getX(),51);
    }

    @Test
    public void wallsPositionLevel1() {

        assertEquals(true,gameModel.getWallsPositions().containsAll(wallsPositions));
        assertEquals(gameModel.getWallsPositions().size(),651);
    }

    @Test
    public void heroPositionLevel1() {
        Position position = new Position(3,1);
        assertEquals(gameModel.getHero().getPosition(), position);
    }

}
