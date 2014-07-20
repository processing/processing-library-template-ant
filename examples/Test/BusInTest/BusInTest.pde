import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
BusIn a=new BusIn(mcu,PinName.P0_21,PinName.P0_22);
void setup()
{
    a.mode(PinMode.OpenDrain);
    println(a.read());
}
void draw()
{
}

