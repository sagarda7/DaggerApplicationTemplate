package np.alorma.timeline;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class TimelineView extends ImageView {

    public static final int TYPE_HIDDEN = -1;

    public static final int TYPE_START = 0;

    public static final int TYPE_MIDDLE = 1;

    public static final int TYPE_LINE = 2;

    public static final int TYPE_END = 3;

    public static final int TYPE_DEFAULT = TYPE_MIDDLE;

    public static final int ALIGNMENT_START = -1;

    public static final int ALIGNMENT_MIDDLE = 0;

    public static final int ALIGNMENT_END = 1;

    public static final int ALIGNMENT_DEFAULT = ALIGNMENT_MIDDLE;

    public static final int STYLE_DASHED = -1;

    public static final int STYLE_LINEAR = 0;

    public static final int STYLE_DEFAULT = STYLE_LINEAR;

    Bitmap internalBitmap;

    Bitmap internalBitmapCache;

    private int lineStyle;

    private float indicatorSize;

    private float internalPadding;

    private boolean drawInternal;

    private int timelineType;

    private int timelineAlignment;

    private Paint paintLine;

    private Paint paintIndicator;

    private Paint paintInternal;

    private Rect rect;

    private float[] dashEffect;

    private int lineColor;

    private float lineWidth;

    private int indicatorColor;

    private int internalColor;

    public TimelineView(Context context) {
        this(context, null);
    }

    public TimelineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimelineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TimelineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    @SuppressWarnings("deprecation")
    private void init(Context context, AttributeSet attrs, int defStyle) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Resources res = getResources();

        TypedArray typedArray = getTypedArray(context, attrs, defStyle);

        initProperties(context, res, typedArray);
        initDrawable(typedArray);

        typedArray.recycle();

        initPaints();

        initRect();
    }

    private void initPaints() {
        initPaintLine();
        initPaintIndicator();
        initPaintInternal();
    }

    private void initProperties(Context context, Resources res, TypedArray typedArray) {
        initLineStyle(typedArray);
        initIndicatorSize(res, typedArray);
        initDrawInternal(res, typedArray);
        initInternalPadding(res, typedArray);
        initTimelineType(typedArray);
        initTimelineAlignment(typedArray);
        initLineWidth(res, typedArray);

        initLineColor(context, res, typedArray);
        initIndicatorColor(context, res, typedArray);
        initInternalColor(context, typedArray);
    }

    private void initInternalColor(Context context, TypedArray typedArray) {
        internalColor = getInternalColor(context, typedArray);
    }

    private void initIndicatorColor(Context context, Resources res, TypedArray typedArray) {
        indicatorColor = getIndicatorColor(context, res, typedArray);
    }

    private void initLineWidth(Resources res, TypedArray typedArray) {
        lineWidth = getLineWidth(res, typedArray);
    }

    private void initLineColor(Context context, Resources res, TypedArray typedArray) {
        lineColor = getLineColor(context, res, typedArray);
    }

    private float getLineWidth(Resources res, TypedArray typedArray) {
        return typedArray.getDimension(R.styleable.TimelineView_timeline_lineWidth,
                res.getDimensionPixelOffset(R.dimen.default_lineWidth));
    }

    private int getLineColor(Context context, Resources res, TypedArray typedArray) {
        return typedArray.getColor(R.styleable.TimelineView_timeline_lineColor,
                AttributesUtils.colorPrimary(context, res.getColor(R.color.colorPrimary)));
    }

    private TypedArray getTypedArray(Context context, AttributeSet attrs, int defStyle) {
        return context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.TimelineView, defStyle, 0);
    }

    private void initDrawable(TypedArray typedArray) {
        if (!isInEditMode()) {
            setImageDrawable(
                    typedArray.getDrawable(R.styleable.TimelineView_timeline_internalDrawable));
        }
    }

    private boolean isLineDashed() {
        return lineStyle == STYLE_DASHED;
    }

    private void initRect() {
        rect = new Rect();
    }

    private void initPaintInternal() {
        paintInternal = new Paint();
        paintInternal.setFlags(Paint.ANTI_ALIAS_FLAG);
        paintInternal.setColor(internalColor);
        paintInternal.setStyle(Paint.Style.FILL);
    }

    private void initPaintIndicator() {
        paintIndicator = new Paint();
        paintIndicator.setFlags(Paint.ANTI_ALIAS_FLAG);
        paintIndicator.setColor(indicatorColor);
        paintIndicator.setStyle(Paint.Style.FILL);
    }

    private void initPaintLine() {
        paintLine = new Paint();
        paintLine.setFlags(Paint.ANTI_ALIAS_FLAG);
        paintLine.setColor(lineColor);
        paintLine.setStrokeWidth(lineWidth);
        paintLine.setStyle(Paint.Style.STROKE);

        initDefaultDash();
        if (isLineDashed()) {
            paintLine.setPathEffect(createDashEffect());
        }
    }

    private void initDefaultDash() {
        dashEffect = new float[]{25, 20};
    }

    private int getInternalColor(Context context, TypedArray typedArray) {
        return typedArray.getColor(R.styleable.TimelineView_timeline_internalColor,
                AttributesUtils.windowBackground(context, Color.WHITE));
    }

    private int getIndicatorColor(Context context, Resources res, TypedArray typedArray) {
        return typedArray.getColor(R.styleable.TimelineView_timeline_indicatorColor,
                AttributesUtils.colorAccent(context, res.getColor(R.color.colorAccent)));
    }

    private void initTimelineAlignment(TypedArray typedArray) {
        timelineAlignment = getTimelineAlignment(
                typedArray.getInt(R.styleable.TimelineView_timeline_alignment, ALIGNMENT_DEFAULT));
    }

    private void initTimelineType(TypedArray typedArray) {
        timelineType = getTimelineType(
                typedArray.getInt(R.styleable.TimelineView_timeline_type, TYPE_DEFAULT));
    }

    private void initInternalPadding(Resources res, TypedArray typedArray) {
        internalPadding = typedArray.getDimension(R.styleable.TimelineView_timeline_internalPadding,
                res.getDimensionPixelOffset(R.dimen.default_internalPadding));
    }

    private void initDrawInternal(Resources res, TypedArray typedArray) {
        drawInternal = typedArray.getBoolean(R.styleable.TimelineView_timeline_drawInternal,
                res.getBoolean(R.bool.default_drawInternal));
    }

    private void initIndicatorSize(Resources res, TypedArray typedArray) {
        indicatorSize = typedArray.getDimension(R.styleable.TimelineView_timeline_indicatorSize,
                res.getDimensionPixelOffset(R.dimen.default_itemSize));
    }

    private void initLineStyle(TypedArray typedArray) {
        lineStyle = getTimelineStyle(
                typedArray.getInt(R.styleable.TimelineView_timeline_lineStyle, STYLE_DEFAULT));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.getClipBounds(rect);

        drawByType(canvas);

        super.onDraw(canvas);
    }

    private void drawByType(Canvas canvas) {
        switch (timelineType) {
            case TYPE_START:
                drawTypeStart(canvas);
                break;

            case TYPE_MIDDLE:
                drawTypeMiddle(canvas);
                break;

            case TYPE_END:
                drawTypeEnd(canvas);
                break;

            case TYPE_HIDDEN:
                if (hasInternalBitmap()) {
                    drawBitmap(canvas, (rect.centerX() - indicatorSize) + internalPadding,
                        (rect.centerY() - indicatorSize) + internalPadding,
                        (int) ((indicatorSize - internalPadding) * 2));
                }
                break;

            default:
                drawLine(canvas, rect.top, rect.bottom);
        }
    }

    private void drawTypeEnd(Canvas canvas) {
        drawLine(canvas, rect.top, rect.centerY());
        drawIndicator(canvas, paintIndicator, rect.centerX(), rect.centerY(), indicatorSize);
        if (isDrawInternal()) {
            drawInternal(canvas);
        }
        if (hasInternalBitmap()) {
            drawBitmap(canvas, (rect.centerX() - indicatorSize) + internalPadding,
                    (rect.centerY() - indicatorSize) + internalPadding,
                    (int) ((indicatorSize - internalPadding) * 2));
        }
    }

    private void drawLine(Canvas canvas, int startY, int stopY) {
        canvas.drawLine(getRect().centerX(), startY, getRect().centerX(), stopY, getPaintLine());
    }

    private void drawTypeMiddle(Canvas canvas) {
        drawLine(canvas, rect.top, rect.bottom);

        int centerY = rect.centerY();

        if (timelineAlignment == ALIGNMENT_START) {
            centerY = (int) (rect.top + indicatorSize);
        } else if (timelineAlignment == ALIGNMENT_END) {
            centerY = (int) (rect.bottom - indicatorSize);
        }

        drawIndicator(canvas, paintIndicator, rect.centerX(), centerY, indicatorSize);
        if (isDrawInternal()) {
            drawInternal(canvas, paintInternal, rect.centerX(), centerY,
                    indicatorSize - internalPadding);
        }
        if (hasInternalBitmap()) {
            drawBitmap(canvas, (rect.centerX() - indicatorSize) + internalPadding,
                    (centerY - indicatorSize) + internalPadding,
                    (int) ((indicatorSize - internalPadding) * 2));
        }
    }

    private boolean hasInternalBitmap() {
        return internalBitmap != null;
    }

    private void drawTypeStart(Canvas canvas) {
        drawLine(canvas, rect.centerY(), rect.bottom);
        drawIndicator(canvas, paintIndicator, rect.centerX(), rect.centerY(), indicatorSize);

        if (isDrawInternal()) {
            drawInternal(canvas);
        }

        if (hasInternalBitmap()) {
            drawBitmap(canvas, (rect.centerX() - indicatorSize) + internalPadding,
                    (rect.centerY() - indicatorSize) + internalPadding,
                    (int) ((indicatorSize - internalPadding) * 2));
        }
    }

    private void drawInternal(Canvas canvas) {
        drawInternal(canvas, paintInternal, rect.centerX(), rect.centerY(),
                indicatorSize - internalPadding);
    }

    private @TimelineType int getTimelineType(int value) {
        return value;
    }

    private @TimelineAlignment int getTimelineAlignment(int value) {
        return value;
    }

    private @TimelineStyle int getTimelineStyle(int value) {
        return value;
    }

    public int getLineColor() {
        return paintLine.getColor();
    }

    public void setLineColor(@ColorInt int lineColor) {
        paintLine.setColor(lineColor);
        invalidate();
    }

    public float getLineWidth() {
        return paintLine.getStrokeWidth();
    }

    public void setLineWidth(float lineWidth) {
        paintLine.setStrokeWidth(lineWidth);
        invalidate();
    }

    public int getIndicatorColor() {
        return paintIndicator.getColor();
    }

    public void setIndicatorColor(@ColorInt int indicatorColor) {
        paintIndicator.setColor(indicatorColor);
        invalidate();
    }

    public float getIndicatorSize() {
        return indicatorSize;
    }

    public void setIndicatorSize(float indicatorSize) {
        this.indicatorSize = indicatorSize;
        invalidate();
        requestLayout();
    }

    public int getInternalColor() {
        return paintInternal.getColor();
    }

    public void setInternalColor(@ColorInt int internalColor) {
        paintInternal.setColor(internalColor);
        invalidate();
    }

    public float getInternalPadding() {
        return internalPadding;
    }

    public void setInternalPadding(float internalPadding) {
        this.internalPadding = internalPadding;
        invalidate();
    }

    public boolean isDrawInternal() {
        return drawInternal;
    }

    public void setDrawInternal(boolean drawInternal) {
        this.drawInternal = drawInternal;
        invalidate();
    }

    public float[] getDashEffect() {
        return dashEffect;
    }

    public void setDashEffect(@Size(2) float[] dashEffect) {
        this.dashEffect = dashEffect;
        if (isLineDashed()) {
            paintLine.setPathEffect(createDashEffect());
        }
        invalidate();
    }

    private PathEffect createDashEffect() {
        return new DashPathEffect(dashEffect, 1);
    }

    public Paint getPaintLine() {
        return paintLine;
    }

    public Paint getPaintIndicator() {
        return paintIndicator;
    }

    public Paint getPaintInternal() {
        return paintInternal;
    }

    public void setTimelineStyle(@TimelineStyle int timelineStyle) {
        if (timelineStyle == STYLE_DASHED) {
            paintLine.setPathEffect(createDashEffect());
        } else {
            paintLine.setPathEffect(null);
        }
        invalidate();
    }

    public @TimelineStyle int getLineStyle() {
        return lineStyle;
    }

    public @TimelineType int getTimelineType() {
        return timelineType;
    }

    public void setTimelineType(@TimelineType int timelineType) {
        this.timelineType = timelineType;
        invalidate();
    }

    public void determineTimelineType(int position, int totalCount) {
        if(position == 0){
            this.timelineType = TYPE_START;
        } else if(position == totalCount-1){
            this.timelineType = TYPE_END;
        } else {
            this.timelineType = TYPE_MIDDLE;
        }
        invalidate();
    }

    public @TimelineAlignment int getTimelineAlignment() {
        return timelineAlignment;
    }

    public void setTimelineAlignment(@TimelineAlignment int timelineAlignment) {
        this.timelineAlignment = timelineAlignment;
        invalidate();
    }

    @Override
    public void setImageDrawable(Drawable internalDrawable) {
        this.internalBitmap = drawableToBitmap(internalDrawable);
        if (internalBitmapCache != null) {
            internalBitmapCache.recycle();
            internalBitmapCache = null;
        }
        invalidate();
    }

    @Override
    public void setImageBitmap(Bitmap internalBitmap) {
        this.internalBitmap = internalBitmap;
        if (internalBitmapCache != null) {
            internalBitmapCache.recycle();
            internalBitmapCache = null;
        }
        invalidate();
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap
                .createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private Rect getRect(){
        return rect;
    }

    protected abstract void drawIndicator(Canvas canvas, Paint paintStart, float centerX,
        float centerY, float size);

    protected abstract void drawInternal(Canvas canvas, Paint paintInternal, float centerX,
        float centerY, float size);

    protected abstract void drawBitmap(Canvas canvas, float left, float top, int size);

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_HIDDEN, TYPE_START, TYPE_MIDDLE, TYPE_LINE, TYPE_END})
    public @interface TimelineType {

    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ALIGNMENT_START, ALIGNMENT_MIDDLE, ALIGNMENT_END})
    public @interface TimelineAlignment {

    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STYLE_DASHED, STYLE_LINEAR})
    public @interface TimelineStyle {

    }
}