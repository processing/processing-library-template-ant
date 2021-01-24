package gwel.game.anim;

import static com.badlogic.gdx.math.MathUtils.PI2;
import static com.badlogic.gdx.math.MathUtils.sin;


public class TFSin extends TimeFunction {

    public TFSin() {
        this(1.0f, 0.5f, 0.0f, 0.0f);
    }

    public TFSin(float duration, float mult, float offset, float phase) {
        params = new TFParam[] {
                new TFParam<>("duration", TFParam.NUMBERBOX, "sec", 0.f, 20f, duration),
                new TFParam<>("mult", TFParam.SLIDER, "", 0.f, 1.f, mult),
                new TFParam<>("offset", TFParam.SLIDER, "", -1.f, 1.f, offset),
                new TFParam<>("phase", TFParam.SLIDER, "deg", -180, 180, phase)
        };
        reset();
    }

    public void update(float dtime) {
        super.update(dtime);
        // offset + mult * sin(time*freq + phase);
        value = (float) params[2].getValue() +
                (float) params[1].getValue() *
                        sin(PI2 * (time * 1f/((float) params[0].getValue()) +
                                (float) params[3].getValue()/360.0f) );
    }
}