import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
DigitalOut a=new DigitalOut(mcu,PinName.LED1);
void setup()
{
  a.write(0);
  println(a.read());
}
void draw()
{
}

