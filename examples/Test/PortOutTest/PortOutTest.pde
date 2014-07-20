import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
PortOut a=new PortOut(mcu,PortName.Port0,0xffffffff);
void setup()
{
  a.write(0xf00);
  println(Integer.toHexString(a.read()));
}
void draw()
{
}

