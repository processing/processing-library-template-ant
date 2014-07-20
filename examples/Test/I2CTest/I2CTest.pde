import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
I2C i2c=new I2C(mcu,PinName.p28,PinName.p27);
void setup()
{
  i2c.frequency(100000);
  i2c.start();
  i2c.write(1);
  i2c.write(0,new byte[]{1,2,3},false);
  i2c.read(1);
  I2C.ReadResult t=i2c.read(1,10,false);
  i2c.stop();

}
void draw()
{
}

