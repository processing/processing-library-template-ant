package gwel.game.anim;


public class TFSpin extends TimeFunction {
    float cycle;

    public TFSpin() { this(0.f, 1.f, 1.f); }

    public TFSpin(float start, float duration, float mult) {
        params = new TFParam[] {
                new TFParam<Float>("start", TFParam.SLIDER, "deg", -180.f, 180.f, start),
                new TFParam<Float>("duration", TFParam.NUMBERBOX, "s", 0.0f, 20, duration),
                new TFParam<Float>("mult", TFParam.NUMBERBOX, "", 0.1f, 60.f, mult),
        };
        reset();
    }

    @Override
    public void update(float dtime) {
        super.update(dtime);
        if (time > cycle)
            time -= cycle;
        value = (float) params[0].getValue() / 360.f + time / cycle;
    }

    @Override
    public void reset() {
        super.reset();
        cycle = (float) params[1].getValue() * (float) params[2].getValue();
    }
}
