package com.qin.scrollerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;


//带有可以切换屏的Activity
public class MainActivity extends Activity {

    public static final String TAG = "CustomGroupView";
    public static int screenWidth;  // 屏幕宽度
    public static int screenHeight;  //屏幕高度
    private EditText editTextX;
    private EditText editTextY;
    private CustomViewGroup customViewGroup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //获得屏幕分辨率大小
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;

        setContentView(R.layout.custom_view_group);

        //获取自定义视图的空间引用
        customViewGroup = (CustomViewGroup) findViewById(R.id.customViewGroup);
        editTextX = (EditText) findViewById(R.id.inputX_et);
        editTextY = (EditText) findViewById(R.id.inputY_et);

    }


    public void scrollTo(View view) {
        int inputX = Integer.valueOf(editTextX.getText().toString());
        int inputY = Integer.valueOf(editTextY.getText().toString());
        Log.i(TAG, "X = " + inputX + "; Y = " + inputY);
        customViewGroup.scrollTo(inputX, inputY);
        showScrollXY();

    }

    private void showScrollXY() {
        String msg = "getScrollX() = " + customViewGroup.getScrollX()
                + " ;getScrollY() = " + customViewGroup.getScrollY();
        Log.i(TAG, msg);
        Toast.makeText(this, msg,
                Toast.LENGTH_SHORT).show();

    }


    public void scrollBy(View view) {
        int inputX = Integer.valueOf(editTextX.getText().toString());
        int inputY = Integer.valueOf(editTextY.getText().toString());
        Log.i(TAG, "X = " + inputX + "; Y = " + inputY);
        customViewGroup.scrollBy(inputX, inputY);
        showScrollXY();
    }

    public void reset(View view) {
        customViewGroup.scrollTo(0, 0);
        showScrollXY();
    }
}
