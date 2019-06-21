package com.grupo73.proj1.View.Game;

import com.googlecode.lanterna.TextColor;

import java.util.HashMap;
import java.util.Map;

public class BackGroundColorFactory {
    private static Map<String, TextColor.RGB>  cache = new HashMap<>();

    public static TextColor.RGB getColor (String symbol){

        if (!cache.containsKey(symbol)) {
            if (symbol.equals("✈"))
                cache.put(symbol, new TextColor.RGB(0, 0, 128));
            else if (symbol.equals(" "))
                cache.put(symbol, new TextColor.RGB(255, 255, 255));
            else if (symbol.equals("✘"))
                cache.put(symbol, new TextColor.RGB(115, 181, 183));
            else if (symbol.equals("Score:"))
                cache.put(symbol, new TextColor.RGB(255, 255, 255));
            else if (symbol.equals("o"))
                cache.put(symbol, new TextColor.RGB(255, 255, 0));
            else if (symbol.equals("≎"))
                cache.put(symbol, new TextColor.RGB(52, 102, 153));
        }
        return cache.get(symbol);
    }

}
