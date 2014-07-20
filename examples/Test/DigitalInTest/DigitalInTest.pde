import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
DigitalIn a=new DigitalIn(mcu,PinName.LED1);
void setup()
{
    a.mode(PinMode.OpenDrain);
    a.read();
}
void draw()
{
}

