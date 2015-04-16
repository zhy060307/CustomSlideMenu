package com.zhy.customslidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class CustomSlideMenu extends ViewGroup {

    private View menuView;
    private View mainView;
    private Scroller scroller;


    public CustomSlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //初始化菜单和主界面的宽高
        initView(widthMeasureSpec, heightMeasureSpec);
    }


    private void initView(int widthMeasureSpec, int heightMeasureSpec) {

        //获取菜单对象
        menuView = getChildAt(0);
        //高度为
        menuView.measure(menuView.getLayoutParams().width, heightMeasureSpec);

        //获取主界面对象
        mainView = getChildAt(1);
        //高度为
        mainView.measure(widthMeasureSpec, heightMeasureSpec);


    }

    /**
     * 排版布局
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取菜单对象
        menuView.layout(-menuView.getMeasuredWidth(), 0, 0, b);

        //获取主界面对象
        mainView.layout(l, t, r, b);
    }

    private int lastX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int curX = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = curX;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = lastX - curX;
                //getScrollX()是当前view的左上角相对于母视图的左上角的X轴偏移量
                //
                int scrollX = getScrollX() + moveX;
                if (scrollX < -menuView.getMeasuredWidth()) {
                    scrollTo(-menuView.getMeasuredWidth(), 0);
                } else if (scrollX > 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(moveX, 0);
                }
                lastX = curX;
                break;
            case MotionEvent.ACTION_UP:
                //判断滑动结束后的状态
                int startX = getScrollX();
                int dx = 0;
                if (startX > -menuView.getMeasuredWidth() / 2) {
                    dx = -startX;
                    scrollTo(0, 0);
                } else {
                    scrollTo(-menuView.getMeasuredWidth(), 0);
                    dx = -menuView.getMeasuredWidth() - startX;
                }

                scroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 5);
                invalidate();
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {

        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }

        super.computeScroll();
    }
}
