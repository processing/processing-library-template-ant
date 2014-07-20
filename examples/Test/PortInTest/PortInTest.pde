import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
PortIn a=new PortIn(mcu,PortName.Port0,0xffffffff);
void setup()
{
  PortIn a=new PortIn(mcu,PortName.Port0,0xffffffff);
  println(a.read());
}
void draw()
{
}

