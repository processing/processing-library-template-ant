import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
SPISlave spi=new SPISlave(mcu,PinName.p5,PinName.p6,PinName.p7,PinName.p8);
void setup()
{
  spi.frequency(1000000);
  spi.format(8,3);
  println(Integer.toString(spi.read()));
  println(Integer.toString(spi.receive()));
  spi.reply(1);
}
void draw()
{
}

