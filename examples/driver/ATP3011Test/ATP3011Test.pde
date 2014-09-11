import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
ATP3011 talk = new ATP3011(mcu,PinName.P0_10 , PinName.P0_11,ATP3011.I2C_ADDRESS<<1);

void setup()
{
}

void draw()
{
    for(int n=1 ; ; n++)
    {
      String str = String.format("<NUMK VAL={0}>.", n);
      byte[] msg = new byte[32];
      msg = str.getBytes();
      talk.synthe(msg);
    }
}

