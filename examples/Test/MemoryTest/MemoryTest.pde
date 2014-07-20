import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
Memory a=new Memory(mcu);
void setup()
{
  a.write(0x20080000,new byte[]{0x01,0x02,0x03,0x04});
  a.write32(0x20080004,new int[]{0xf0f1f2f3,0xf9f8f7f6});
  int[] i=a.read32(0x20080000,16);
  byte[] b=a.read(0x20080000,16);
  println(i);
  println(b);
}
void draw()
{
}

