package gwel.game.anim;

import com.badlogic.gdx.math.Interpolation;


public class TFEaseFromTo extends TimeFunction {
    private Interpolation interpolation;
    float distance;

    public TFEaseFromTo() {
        this(0.0f, 1.0f, 0f, 0.4f, "linear", false, false);
    }

    public TFEaseFromTo(float from, float to, float delay, float easeDuration, String easing, boolean backforth, boolean loop) {
        params = new TFParam[] {
                new TFParam<>("from", TFParam.SLIDER, "", -1.f, 1.f, from),
                new TFParam<>("to", TFParam.SLIDER, "", -1.f, 1.f, to),
                new TFParam<>("delay", TFParam.NUMBERBOX, "sec", 0.0f, 10.0f, delay),
                new TFParam<>("easeduration", TFParam.NUMBERBOX, "sec", 0.0f, 10.0f, easeDuration),
                new TFParam<>("easing", TFParam.EASING, "", 0, Animation.interpolationNames.length-1, easing),
                new TFParam<>("backforth", TFParam.CHECKBOX, "", 0, 1, backforth),
                new TFParam<>("loop", TFParam.CHECKBOX, "", 0, 1, loop)
        };
        reset();
    }


    @Override
    public void update(float dtime) {
        time += dtime;
        switch (state) {
            case DELAY:
                if (time > (float) params[2].getValue()) { // Time>delay ?
                    state = RUNNING;
                    time -= (float) params[2].getValue();
                }
                break;
            case RUNNING:
                if (time > (float) params[3].getValue()) { // time > easeduration ?
                    if ((boolean) params[6].getValue()) {  // Loop ?
                        time -= (float) params[3].getValue();
                        state = (float) params[2].getValue() > 0f ? DELAY : RUNNING;
                    } else {
                        state = STOPPED;
                    }
                    // Back to initial position
                    value = (boolean) params[5].getValue() ? (float) params[0].getValue() : (float) params[1].getValue();
                    break;
                }

                float t = time / (float) params[3].getValue();
                if ((boolean) params[5].getValue()) { // Backforth ?
                    if (t < 0.5)
                        t *= 2;
                    else
                        t = 2 - 2*t;
                }
                value = (float) params[0].getValue() + distance * interpolation.apply(t);
                break;
        }
    }


    @Override
    public void reset() {
        super.reset();
        state = RUNNING;
        distance = (float) params[1].getValue() - (float) params[0].getValue();
        interpolation = Animation.getInterpolation((String) params[4].getValue());
        value = (float) params[0].getValue();
    }
}
