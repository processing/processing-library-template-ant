import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
TMP102 a=new TMP102(mcu,PinName.p28,PinName.p27,0x90);
      
void setup()
{
}
void draw()
{
  println(a.read());
}


