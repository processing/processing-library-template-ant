import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

SCP1000 a=new SCP1000(mcu, PinName.p11, PinName.p12, PinName.p13, PinName.p14, PinName.p15);
      
void setup()
{
}
void draw()
{
  println("Temperature: " + a.readTemperature()+"degree");
  println("Pressure: " + a.readPressure() + "hPa");
}

