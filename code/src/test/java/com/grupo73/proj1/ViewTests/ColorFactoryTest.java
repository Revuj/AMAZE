package com.grupo73.proj1.ViewTests;

import com.googlecode.lanterna.TextColor;
import com.grupo73.proj1.View.Game.BackGroundColorFactory;
import com.grupo73.proj1.View.Game.ForeGroundColorFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorFactoryTest {

    @Test
    public void BackGroundColorFactoryTest() {
        BackGroundColorFactory bgFactory = new BackGroundColorFactory();
        assertEquals(bgFactory.getColor("✈"), new TextColor.RGB(0, 0, 128));
        assertEquals(bgFactory.getColor(" "),  new TextColor.RGB(255, 255, 255));
        assertEquals(bgFactory.getColor("✘"), new TextColor.RGB(115, 181, 183));
        assertEquals(bgFactory.getColor("Score:"), new TextColor.RGB(255, 255, 255));
        assertEquals(bgFactory.getColor("o"), new TextColor.RGB(255, 255, 0));
        assertEquals(bgFactory.getColor("≎"), new TextColor.RGB(52, 102, 153));
    }

    @Test
    public void ForeGroundColorFactoryTest() {
        ForeGroundColorFactory fgFactory = new ForeGroundColorFactory();
        assertEquals(fgFactory.getColor("✈"), new TextColor.RGB(128, 128, 0));
        assertEquals(fgFactory.getColor(" "),  null);
        assertEquals(fgFactory.getColor("✘"), new TextColor.RGB(255, 255, 0));
        assertEquals(fgFactory.getColor("Score:"), new TextColor.RGB(0, 0, 0));
        assertEquals(fgFactory.getColor("o"),  new TextColor.RGB(52, 102, 153));
        assertEquals(fgFactory.getColor("≎"), new TextColor.RGB(255, 0, 255));
    }

}
