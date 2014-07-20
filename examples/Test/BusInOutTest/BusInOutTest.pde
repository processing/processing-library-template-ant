import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
BusInOut a=new BusInOut(mcu,PinName.P0_21,PinName.P0_22);
void setup()
{
  a.mode(PinMode.OpenDrain);
  a.output();
  a.write(2);
  a.input();
  println(a.read());
}
void draw()
{
}

