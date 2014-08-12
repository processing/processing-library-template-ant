import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

MPL115A2 a=new MPL115A2(mcu,PinName.p28,PinName.p27,(0x60<<1));

void setup()
{
  println(a.readRaw());
  println(a.read());
  println(a.getTemperature());
  println(a.getPressure());
}
void draw()
{
}

