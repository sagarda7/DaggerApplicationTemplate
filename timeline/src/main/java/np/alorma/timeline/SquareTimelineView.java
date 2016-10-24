package np.alorma.timeline;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;

public class SquareTimelineView extends TimelineView {
    private RectF rectF;

    public SquareTimelineView(Context context) {
        this(context, null);
    }

    public SquareTimelineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareTimelineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        rectF = new RectF();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareTimelineView(Context context, AttributeSet attrs, int defStyleAttr,
        int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void drawIndicator(Canvas canvas, Paint paintStart, float centerX, float centerY,
        float size) {
        drawSquare(canvas, centerX, centerY, size, paintStart);
    }

    @Override
    protected void drawInternal(Canvas canvas, Paint paintInternal, float centerX, float centerY,
        float size) {
        drawSquare(canvas, centerX, centerY, size, paintInternal);
    }

    @Override protected void drawBitmap(Canvas canvas, float left, float top, int size) {
        if (internalBitmap != null) {
            if (internalBitmapCache == null) {
                internalBitmapCache = transform(internalBitmap, size);
            }

            if (internalBitmapCache != null) {
                canvas.drawBitmap(internalBitmapCache, left, top, null);
            }
        }
    }

    private void drawSquare(Canvas canvas, float centerX, float centerY, float size, Paint paint) {
        if (canvas != null) {
            rectF.left = centerX - size;
            rectF.top = centerY - size;
            rectF.right = centerX + size;
            rectF.bottom = centerY + size;
            canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.bottom, paint);
        }
    }

    private Bitmap transform(Bitmap source, int size) {
        if (source != null) {
            Bitmap output = Bitmap.createScaledBitmap(source, size, size, false);
            if (source != output) {
                source.recycle();
            }
            return output;
        }
        return null;
    }
}
