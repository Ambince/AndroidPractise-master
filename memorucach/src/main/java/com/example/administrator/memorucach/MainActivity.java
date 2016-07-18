package com.example.administrator.memorucach;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageView;
    private MyBitmapUtils myBitmapUtils;
    String MDSString = "谢雄伟是SB";
    private String encode;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image_netWork);
        myBitmapUtils = new MyBitmapUtils();
        myBitmapUtils.disPlay(imageView, "http://img.d.cn/be/image/1605/hm2d2iodpb8gl.png");
        encode = MD5Encoder.encode(MDSString);
        Log.v("Amence", encode + "   MD5加密后");
        string = MD5Encoder.convertMD5(MDSString);
        Log.v("Amence", MD5Encoder.convertMD5(string) + "    convertMD5后");



    }

}
