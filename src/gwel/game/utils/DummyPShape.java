package gwel.game.utils;

import processing.core.PMatrix;
import processing.core.PShape;

public class DummyPShape extends PShape {
    // This class exists only to access PShape private matrix variable
    public DummyPShape(PShape parent) {
        super();
        copyMatrix(parent, this);
    }

    public PMatrix getMatrix() {
        return matrix;
    }
}
