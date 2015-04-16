package com.qin.scrollerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//自定义ViewGroup ， 包含了三个LinearLayout控件，存放在不同的布局位置，通过scrollBy或者scrollTo方法切换
public class CustomViewGroup extends ViewGroup {

    private Context context;
    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private LinearLayout thirdLayout;


    public CustomViewGroup(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    private void initView() {
        firstLayout = (LinearLayout) getChildAt(0);
        secondLayout = (LinearLayout) getChildAt(1);
        thirdLayout = (LinearLayout) getChildAt(2);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        initView();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        firstLayout.measure(widthMeasureSpec, heightMeasureSpec);
        secondLayout.measure(widthMeasureSpec, heightMeasureSpec);
        thirdLayout.measure(widthMeasureSpec, heightMeasureSpec);
    }

    // layout过程
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startLeft = -MainActivity.screenWidth; // 每个子视图的起始布局坐标
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(startLeft, 0,
                    startLeft + MainActivity.screenWidth,
                    MainActivity.screenHeight);
            startLeft += MainActivity.screenWidth; //校准每个子View的起始布局位置
        }
    }

}
