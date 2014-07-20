import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
AnalogIn a=new AnalogIn(mcu,PinName.A0);
void setup()
{
    println(a.read());
    println(a.read_u16());
}
void draw()
{
}

