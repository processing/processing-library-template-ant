package gwel.game.anim;

import com.badlogic.gdx.math.MathUtils;

// Used for eyes blinking animations
public class TFRandomBlink extends TimeFunction {
    private int stateHigh;

    public TFRandomBlink() {
        this(0.2f, 0.1f, 1.0f, 0.0f);
    }
    public TFRandomBlink(float duration, float prob, float mult, float offset) {
        params = new TFParam[] {
                new TFParam<Float>("duration", TFParam.NUMBERBOX, "sec", 0.f, 10.f, duration),
                new TFParam<Float>("prob", TFParam.SLIDER, "", 0.f, 1.f, prob),
                new TFParam<Float>("mult", TFParam.NUMBERBOX, "", 0.f, 1.f, mult),
                new TFParam<Float>("offset", TFParam.NUMBERBOX, "", -1.f, 1.f, offset),
                //new TFParam("start", TFParam.TOGGLE, "", 0.f, 1.f, init)
        };
        reset();
    }

    @Override
    public void update(float dtime) {
        super.update(dtime);

        if (time > (float) params[0].getValue()) {
            time -= (float) params[0].getValue();
            if (stateHigh == 0) {
                stateHigh = 1;
            } else if (MathUtils.random() < (float) params[1].getValue()) {
                stateHigh = 0;
            }
        }
        value = (float) params[3].getValue() + (float) params[2].getValue() * (float) stateHigh;
    }

    @Override
    public void reset() {
        super.reset();
        stateHigh = 0; //(int) params[4].value;
    }
}