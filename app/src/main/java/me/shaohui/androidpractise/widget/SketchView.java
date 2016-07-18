package me.shaohui.androidpractise.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import me.shaohui.androidpractise.R;

/**
 * Created by shaohui on 16/7/8.
 */
public class SketchView extends View{

    //定义属性实例
    private int custom_size;
    private int custom_background;

    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private float scale = 1f;

    private final int SIZE = 15;
    private final int DEFAULT_COLOR = Color.BLUE;

    public SketchView(Context context) {
        this(context, null);
    }

    public SketchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SketchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SketchView, defStyleAttr, R.style.AppTheme);

        custom_size = a.getDimensionPixelSize(R.styleable.SketchView_size, SIZE);
        custom_background = a.getColor(R.styleable.SketchView_background_color, DEFAULT_COLOR);

        //实例化后要记得回收
        a.recycle();

        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(custom_background);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置样式
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v("Amence","onMeasure");

        //获取mode 前两位
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        //获取大小  后30位
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredHeight, measuredWidth;

        if (widthMode == MeasureSpec.EXACTLY) {
            //match_parent 有特定的宽高
            measuredWidth = widthSize;
        } else {
            measuredWidth = SIZE;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            //match_parent 有特定的宽高
            measuredHeight = heightSize;
        } else {
            measuredHeight = SIZE;
        }

        //
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    /**
     * 如果没有父布局不用调用onLayout方法，放位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.v("Amence","onLayout");
        mHeight = getHeight();
        mWidth = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v("Amence","onDraw");
        canvas.drawCircle(mWidth/2, mHeight/2, custom_size * scale, mPaint);

    }

    private ValueAnimator mAnimator;

    public void startAnimation() {
        mAnimator = ValueAnimator.ofFloat(1, 2);

        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        // 重复次数 -1表示无限循环
        mAnimator.setRepeatCount(-1);

        // 重复模式, RESTART: 重新开始 REVERSE:恢复初始状态再开始
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // 关闭动画
        mAnimator.end();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
