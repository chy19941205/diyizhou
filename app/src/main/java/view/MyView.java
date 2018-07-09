package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.chuliangliang20180709.R;

public class MyView extends View {
    private Bitmap bgBackground;
    private Bitmap bgForusground;
    private Canvas mCanvas;
    private Paint mpaint;
    private Path mpath;
    private Paint contentpaint;
    private String content = "刮刮看";
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mpaint = new Paint();
        mpaint.setAlpha(0);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(50);
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeCap(Paint.Cap.ROUND);

        mpath = new Path();
        bgBackground = BitmapFactory.decodeResource(getResources(), R.drawable.text1);
        bgForusground = Bitmap.createBitmap(bgBackground.getWidth(),bgBackground.getHeight(),Bitmap.Config.ARGB_8888);
        
        mCanvas = new Canvas(bgForusground);
        contentpaint = new Paint();
        contentpaint.setColor(Color.WHITE);
        contentpaint.setTextSize(100);
        contentpaint.setStrokeWidth(20);
        mCanvas.drawColor(Color.GRAY);
        mCanvas.drawText(content,mCanvas.getWidth()/4,mCanvas.getHeight()/2,contentpaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mpath.reset();
                mpath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mpath.lineTo(event.getX(),event.getY());
                break;
        }
        mCanvas.drawPath(mpath,mpaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bgBackground,0,0,null);
        canvas.drawBitmap(bgForusground,0,0,null);
    }
}
