import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

LM75B a=new LM75B(mcu,PinName.p28,PinName.p27,0x90);
int c=1;
void setup()
{
}
void draw()
{
  println(a.read());
}

