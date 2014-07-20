import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
I2CSlave i2c=new I2CSlave(mcu,PinName.p28,PinName.p27);
void setup()
{
  i2c.frequency(100000);
  i2c.address(10);
  i2c.receive();
  i2c.write(1);
  i2c.write(new byte[]{1,2,3});
  i2c.read();
  I2C.ReadResult t=i2c.read(3);
  i2c.stop();
}
void draw()
{
}

