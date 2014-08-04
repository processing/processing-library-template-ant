mbedJS-Processing-API
===============


mbedJSのProcessingインタフェイスです。ネットワーク接続したmbedをProcessingから制御することができます。

mbedJSはこちらからダウンロードしてください。  
mbedJS(mbed.org) https://mbed.org/users/nyatla/code/mbedJS/

mbedJS-Processing-APIはMiMic Projectで開発しています。  
MiMic Project http://nyatla.jp/mimic/wp/


特徴
---------------
- mbedJSの動作するmbedシリーズ、LPCXpressoシリーズのMCUをLAN経由で制御することができます。
- mbedSDKとほぼ同一なクラスベースAPIを提供します。
- 最大1000Hz程度のRPCコールができます。

使いかた
---------------
- mbedJSをセットアップしたmbed、又はLPCXpressoを準備してください。
- processing2を準備してください。
- ここから最新のzipファイルをダウンロードしてください。https://github.com/nyatla/mbedJS-Processing-API/tree/master/release
- ライブラリを展開してスケッチブックのディレクトリに展開してください。
- mbedJSのexampleにLEDBlinkのサンプルがあります。LEDをチカチカさせてみましょう。


サンプル
---------------
Lチカのサンプルコードは以下のように書けます。

    import jp.nyatla.mimic.mbedjs.psgapi.*;
    
    Mcu mcu=new Mcu(this,"192.168.128.39");
    DigitalOut a=new DigitalOut(mcu,PinName.LED1);
    int c=1;
    void setup()
    {
    }
    void draw()
    {
      a.write((c/10)%2);
      c++;
    }



