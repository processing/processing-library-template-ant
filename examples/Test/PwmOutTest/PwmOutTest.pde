import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
PwmOut a=new PwmOut(mcu,PinName.p21);
void setup()
{
  a.period(0.5f);
  a.period_ms(100);
  a.period_us(100);
  a.pulsewidth(0.5f);
  a.pulsewidth_ms(100);
  a.pulsewidth_us(100);
  a.write(0.5f);
  float p=a.read();

}
void draw()
{
}

