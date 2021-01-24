package gwel.game.anim;


import com.badlogic.gdx.math.MathUtils;

public abstract class TimeFunction {
    public static final int STOPPED = 0;
    public static final int RUNNING = 1;
    public static final int DELAY = 2;
    public static final int REVERSE = 3;

    protected TFParam<Object>[] params;
    protected float time = 0.f;
    protected float value;
    protected int state = RUNNING;


    public TFParam<Object> getParam(String paramName) {
        for (TFParam<Object> param : params) {
            if (param.name.equals(paramName))
                return param;
        }
        return null;
    }

    public TFParam<Object>[] getParams() { return params; }

    public TFParam<Object>[] getParamsCopy() {
        TFParam<Object>[] paramsCopy = new TFParam[params.length];
        int i=0;
        for (TFParam<Object> param : params) {
            paramsCopy[i++] = param.copy();
        }
        return paramsCopy;
    }

    // Because of ControlP5's limitation this function will only get float payloads
    public void setParam(String name, float value) {
        for (TFParam<Object> param : params) {
            if (param.name.equals(name)) {
                if (param.value instanceof Integer) {
                    // Convert from float to integer value
                    param.setValue(MathUtils.floor((float) value));
                } else if (param.value instanceof Boolean) {
                    // Convert from float to boolean value
                    param.setValue(value > 0.5f);
                } else {
                    param.setValue(value);
                }
                break;
            }
        }
        reset();
    }

    public void setParam(String name, String value) {
        for (TFParam<Object> param : params) {
            if (param.name.equals(name)) {
                param.setValue(value);
                break;
            }
        }
        reset();
    }

    // setParams should be used only to make copies of the same TimeFunction
    public void setParams(TFParam<Object>[] otherParams) {
        params = otherParams;
        reset();
    }


    public int getState() { return state; }

    public void update(float dtime) {
        time += dtime;
    }


    public void reset() {
        time = 0;
    }


    public float getValue() { return value; };
}
