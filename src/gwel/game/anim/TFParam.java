package gwel.game.anim;

public class TFParam<T> {
    public String name;
    public int type;
    public String unit;
    public T value;
    public float min, max;
    static public final int SLIDER = 0;
    static public final int TOGGLE = 1;
    static public final int NUMBERBOX = 2;
    static public final int CHECKBOX = 3;
    static public final int EASING = 4;
    //static public final int TABLE = 5;


    public TFParam(String name, int type, String unit, float min, float max, T value) {
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.min = min;
        this.max = max;
        this.value = value;
    }


    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }


    public TFParam<T> copy() {
        return new TFParam(name, type, unit, min, max, value);
    };
}