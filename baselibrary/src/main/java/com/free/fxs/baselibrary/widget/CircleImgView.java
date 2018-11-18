package com.free.fxs.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.free.fxs.baselibrary.R;

/**
 * @author fxs
 */
public class CircleImgView extends AppCompatImageView {
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static final int DEFAULT_CIRCLE_BACKGROUND_COLOR = Color.TRANSPARENT;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;


    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mCircleBackgroundColor = DEFAULT_CIRCLE_BACKGROUND_COLOR;

    private boolean mBorderOverlay = false;

    public CircleImgView(Context context) {
        super(context);
    }

    public CircleImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImgView, defStyleAttr, 0);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImgView_civ_border_width, 0);
        mBorderColor = a.getColor(R.styleable.CircleImgView_civ_border_color, DEFAULT_BORDER_COLOR);
        mBorderOverlay = a.getBoolean(R.styleable.CircleImgView_civ_border_overlay, DEFAULT_BORDER_OVERLAY);

        // Look for deprecated civ_fill_color if civ_circle_background_color is not set
        if (a.hasValue(R.styleable.CircleImgView_civ_circle_background_color)) {
            mCircleBackgroundColor = a.getColor(R.styleable.CircleImgView_civ_circle_background_color,
                    DEFAULT_CIRCLE_BACKGROUND_COLOR);
        } else if (a.hasValue(R.styleable.CircleImgView_civ_fill_color)) {
            mCircleBackgroundColor = a.getColor(R.styleable.CircleImgView_civ_fill_color,
                    DEFAULT_CIRCLE_BACKGROUND_COLOR);
        }
        a.recycle();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
