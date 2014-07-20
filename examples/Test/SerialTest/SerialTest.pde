import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");
Serial uart=new Serial(mcu,PinName.p9,PinName.p10);
void setup()
{
  uart.baud(115200);
  uart.send_break();
  uart.format(8,Serial.None,1);
  uart.readable();
  uart.writeable();
  uart.putc(32);
  uart.getc();
  uart.puts("1234");
  println(uart.gets(5));
}
void draw()
{
}

