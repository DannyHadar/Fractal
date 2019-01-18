package com.mvp.fractal.hadar.danny.fractalmvp.fractal.painters;

import android.graphics.Paint;

import java.util.HashMap;

public class FractalPainterFactory {
    private static final String FRACTAL_TYPE_SQUARE = "Square";
    private static final String FRACTAL_TYPE_CIRCLE = "Circle";
    private static final String FRACTAL_TYPE_TRIANGLE = "Triangle";

    private static final CircleFractal sCircleFractal;
    private static final SquareFractal sSquareFractal;
    private static final TriangleFractal sTriangleFractal;

    static {
        sCircleFractal = new CircleFractal();
        sSquareFractal = new SquareFractal();
        sTriangleFractal = new TriangleFractal();
    }

    public static FractalPainter getFractal(String shapeType, int resolution, Paint.Style fill, int color) {
      return getFractal(FractalCache.getFractal(shapeType), resolution, fill, color);
    }

    private static FractalPainter getFractal(FractalPainter fractal, int resolution, Paint.Style fill, int color) {
        fractal.setResolution(resolution);
        fractal.setFill(fill);
        fractal.setColor(color);

        return fractal;
    }

    private static class FractalCache {
        /**
         * This stupid cache stores as a Key the String representation of the fractal and as a value the
         * actual fractal that will be set while painting
         */
        private static HashMap<String, FractalPainter> CACHE;

        static {
            CACHE = new HashMap<>();

            CACHE.put(FRACTAL_TYPE_SQUARE, sSquareFractal);
            CACHE.put(FRACTAL_TYPE_CIRCLE, sCircleFractal);
            CACHE.put(FRACTAL_TYPE_TRIANGLE, sTriangleFractal);

        }

        private static FractalPainter getFractal(String type) {
            return CACHE.get(type);
        }
    }
}
