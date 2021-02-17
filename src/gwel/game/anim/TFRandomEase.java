package gwel.game.anim;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;


public class TFRandomEase extends TimeFunction {
    private boolean moving = false;
    private Interpolation interpolation;
    private float easeTime;
    private float prevValue;
    private float targetValue;

    public TFRandomEase() {
        this(0.5f, 0.5f, 1.0f, 0.0f, "linear", 0.5f);
    }
    public TFRandomEase(float duration, float prob, float mult, float offset, String easing, float easeDuration) {
        params = new TFParam[] {
                new TFParam<>("duration", TFParam.NUMBERBOX, "sec", 0.f, 10.f, duration),
                new TFParam<>("prob", TFParam.SLIDER, "", 0.f, 1.f, prob),
                new TFParam<>("mult", TFParam.SLIDER, "", 0, 1.f, mult),
                new TFParam<>("offset", TFParam.SLIDER, "", -1.f, 1.f, offset),
                new TFParam<>("easing", TFParam.EASING, "", 0, 0, easing),
                new TFParam<>("easeduration", TFParam.NUMBERBOX, "sec", 0.f, 10.f, easeDuration)
        };
        interpolation = Animation.getInterpolation(easing);
        reset();
    }


    @Override
    public void update(float dtime) {
        super.update(dtime);

        // dtime is in millis
        easeTime += dtime;
        if (easeTime >= (float) params[5].getValue()) {  // end of ease time
            easeTime = (float) params[5].getValue();
            moving = false;
            prevValue = targetValue;
        }

        if (time > (float) params[0].getValue()) {
            time -= (float) params[0].getValue();
            if (MathUtils.random() < (float) params[1].getValue()) {
                prevValue = getValue();
                targetValue = (float) params[3].getValue() + MathUtils.random((float) params[2].getValue());
                moving = true;
                easeTime = 0f;
            }
        }

        if (moving) {
            value = interpolation.apply(prevValue, targetValue, easeTime/(float) params[5].getValue());
        } else {
            value = targetValue;
        }
    }

    @Override
    public void reset() {
        super.reset();
        moving = true;
        easeTime = 0f;
        prevValue = 0f;
        targetValue = (float) params[3].getValue() + MathUtils.random((float) params[2].getValue());
        interpolation = Animation.getInterpolation((String) params[4].getValue());
    }
}

