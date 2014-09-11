import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

ACM1602NI a=new ACM1602NI(mcu,PinName.p28,PinName.p27,0x50);

void setup()
{
}
void draw()
{
  a.puts("Hello mbedJS!");
}



