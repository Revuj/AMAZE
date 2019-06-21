package com.grupo73.proj1.ControllerTests;

import com.grupo73.proj1.Controller.State.*;
import com.grupo73.proj1.Model.Entities.HeroModel;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HeroTest {

   private HeroModel heroModel;

    @Before
    public void setUp() {
        heroModel = new HeroModel(0,0);
    }


    @Test
    public void testMoveUp() {

        int oldX = heroModel.getPosition().getX();
        int oldY = heroModel.getPosition().getY();
        heroModel.setState(new MovingUpState());
        heroModel.getState().update(heroModel);
        int newX = heroModel.getPosition().getX();
        int newY = heroModel.getPosition().getY();

        assertEquals(0, newX - oldX);
        assertEquals(-1, newY - oldY);
    }

    @Test
    public void testMoveDown()  {
        int oldX = heroModel.getPosition().getX();
        int oldY = heroModel.getPosition().getY();
        heroModel.setState(new MovingDownState());
        heroModel.getState().update(heroModel);
        int newX = heroModel.getPosition().getX();
        int newY = heroModel.getPosition().getY();

        assertEquals(0, newX - oldX);
        assertEquals(1, newY - oldY);
    }

    @Test
    public void testMoveLeft()  {
        int oldX = heroModel.getPosition().getX();
        int oldY = heroModel.getPosition().getY();
        heroModel.setState(new MovingLeftState());
        heroModel.getState().update(heroModel);
        int newX = heroModel.getPosition().getX();
        int newY = heroModel.getPosition().getY();

        assertEquals(-1, newX - oldX);
        assertEquals(0, newY - oldY);
    }

    @Test
    public void testMoveRight() {
        int oldX = heroModel.getPosition().getX();
        int oldY = heroModel.getPosition().getY();
        heroModel.setState(new MovingRightState());
        heroModel.getState().update(heroModel);
        int newX = heroModel.getPosition().getX();
        int newY = heroModel.getPosition().getY();

        assertEquals(1, newX - oldX);
        assertEquals(0, newY - oldY);
    }
}
