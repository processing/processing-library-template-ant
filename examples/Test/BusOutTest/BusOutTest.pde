import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
BusOut a=new BusOut(mcu,PinName.LED1,PinName.LED2);
void setup()
{
  a.write(2);
  println(a.read());
}
void draw()
{
}

