import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

StepperMotor sm = new StepperMotor(mcu,PinName.p22,PinName.p23,
        PinName.p21,PinName.p25,PinName.p24,PinName.p26);

void setup()
{
}

void draw()
{
  sm.rotate(200, 20);
  sm.close();
  mcu.close();
  println("done");
}


