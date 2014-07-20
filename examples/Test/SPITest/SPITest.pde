import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
SPI spi=new SPI(mcu,PinName.p5,PinName.p6,PinName.p7);
void setup()
{
  spi.frequency(1000000);
  spi.format(8,3);
  println(spi.write(39));
}
void draw()
{
}

