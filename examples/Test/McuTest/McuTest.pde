import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

void setup()
{
  Mcu.GetInfoResult r=mcu.getInfo();
  println(r.mcu_eth);
  println(r.mcu_name);
  println(r.platform);
  println(r.version);
  
}
void draw()
{
}

