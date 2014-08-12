import jp.nyatla.mimic.mbedjs.psgapi.driver.*;
import jp.nyatla.mimic.mbedjs.psgapi.*;

Mcu mcu=new Mcu(this,"192.168.128.39");

TextLCD lcd=new TextLCD(mcu , PinName.p24, PinName.p26,
        PinName.p27, PinName.p28, PinName.p29, PinName.p30,TextLCD.LCD16x2);

void setup()
{
  println(lcd.rows());
  println(lcd.columns());
    lcd.cls();
    lcd.putc('T');
    lcd.putc('E');
    lcd.putc('S');
    lcd.putc('T');
    lcd.puts("STRING");
    //lcd.locate(1,1);
}
void draw()
{
}

