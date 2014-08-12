import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

MMA7660 a=new MMA7660(mcu,PinName.p28,PinName.p27,0x98);

void setup()
{
  a.testConnection();
  a.setActive(true);
  println(a.readData_int());
  println(a.readData());
  println(a.x());
  println(a.y());
  println(a.z());
  a.setSampleRate(2);
  println(a.getSide());
  println(a.getOrientation());
  println(a.Down);
}
void draw()
{
}

