package space.highwayto.doodlz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

/**
 * Created by hvosdt on 18/02/16.
 */
public class DoodleView extends View {

    private static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private final Paint paintScreen;
    private final Paint paintLine;
    private final Map<Integer, Path> pathMap = new HashMap<Integer, Path>();
    private final Map<Integer, Point> previousPointMap = new HashMap<Integer, Point>();
    private GestureDetector singleTapDetector;

    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);

        paintScreen = new Paint();
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLACK);
        paintLine.setStrokeWidth(5);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeCap(Paint.Cap.ROUND);

        singleTapDetector = new GestureDetector(getContext(), singleTapListener);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH){
        bitmap = new Bitmap.createBitmap(getWidth(), getHandler(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }

    public void clear(){
        pathMap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    public void setDrawingColor(int color){
        paintLine.setColor(color);
    }

    public int getDrawingColor(){
        return paintLine.getColor();
    }

    public void setLineWidth(int width){
        paintLine.setStrokeWidth(width);
    }

    public int getLineWidth(){
        return (int) paintLine.getStrokeWidth();
    }

    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap,0,0,paintScreen);
        for (Integer key : pathMap.keySet())
            canvas.drawPath(pathMap.get(key), paintLine);
    }

    public void hideSystemBars(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_IMMERSIVE
            );
        }
    }

    public void showSystemBars(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }

    private SimpleOnGestureListener singleTapListener = new Se

    public void saveImage(){

    }

    public void printImage(){

    }
}
