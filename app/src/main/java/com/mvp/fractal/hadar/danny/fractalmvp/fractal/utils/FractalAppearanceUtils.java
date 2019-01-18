package com.mvp.fractal.hadar.danny.fractalmvp.fractal.utils;

import android.graphics.Color;
import android.graphics.Paint;

public class FractalAppearanceUtils {
    public static int RESOLUTION_MAX = 3;
    public static int RESOLUTION_HIGH = 8;
    public static int RESOLUTION_MEDIUM = 16;
    public static int RESOLUTION_LOW = 24;

    public static int getColor(String colorString) {
        switch (colorString) {
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            case "Red":
                return Color.RED;
            default:
            case "Black":
                return Color.BLACK;
        }
    }

    public static Paint.Style getFillStyle(boolean fillType) {
        return fillType ? Paint.Style.FILL : Paint.Style.STROKE;
    }

    public static int getResolution(float resRating) {
        int resolution;
        switch ((int) resRating) {
            case 0:
                resolution = RESOLUTION_LOW;
                break;
            case 1:
                resolution = RESOLUTION_MEDIUM;
                break;
            case 2:
                resolution = RESOLUTION_HIGH;
                break;
            default:
            case 3:
                resolution = RESOLUTION_MAX;
                break;
        }
        return resolution;
    }
}
