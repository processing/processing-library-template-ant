import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
AnalogOut a=new AnalogOut(mcu,PinName.p18);
void setup()
{
  a.write(0);
  a.write_u16(1);
}
void draw()
{
}

