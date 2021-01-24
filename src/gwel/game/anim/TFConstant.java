package gwel.game.anim;


public class TFConstant extends TimeFunction {

    public TFConstant() { this(0.0f); }

    public TFConstant(float value) {
        params = new TFParam[] {
            new TFParam<Float>("value", TFParam.NUMBERBOX, "", -1.f, 1.f, value),
        };
        state = STOPPED;
    }

    @Override
    public void update(float dtime) {}

    @Override
    public float getValue() {
        return (float) params[0].getValue();
    }
}
