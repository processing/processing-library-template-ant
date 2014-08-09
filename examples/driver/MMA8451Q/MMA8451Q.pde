import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

MMA8451Q a=new MMA8451Q(mcu,PinName.p28,PinName.p27,0x98);

void setup()
{
  println(a.getWhoAmI());
  println(a.getAccX());
  println(a.getAccY());
  println(a.getAccZ());
  println(a.getAccAllAxis());
}
void draw()
{
}

