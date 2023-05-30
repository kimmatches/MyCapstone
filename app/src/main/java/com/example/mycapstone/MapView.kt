package com.example.mycapstone

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable //?
import android.media.Image
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
/**
 * TODO: document your custom view class.
 */
class MapView @JvmOverloads constructor(context: Context?, attr: AttributeSet? = null) :
    SubsamplingScaleImageView(context, attr) {

    data class Map(
        var id: String,
        var point: PointF,
        var fixed: Int,
        var imageId: Int,
        var width: Float,
        var height: Float,
        var text: String
    )
    data class Line(var id: String, var point: PointF, var color: Int)

    var w: Float? = null
    var h: Float? = null

    //뷰의 초기화 과정
//    private fun initialise() {invalidate()}

//    private var _exampleString: String? = null // TODO: use a default from R.string...
//    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
//    private var _exampleDimension: Float = 0f // TODO: use a default from R.dimen...
//
//    private lateinit var textPaint: TextPaint
//    private var textWidth: Float = 0f
//    private var textHeight: Float = 0f
//
//    /**
//     * The text to draw
//     */
//    var exampleString: String?
//        get() = _exampleString
//        set(value) {
//            _exampleString = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * The font color
//     */
//    var exampleColor: Int
//        get() = _exampleColor
//        set(value) {
//            _exampleColor = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * In the example view, this dimension is the font size.
//     */
//    var exampleDimension: Float
//        get() = _exampleDimension
//        set(value) {
//            _exampleDimension = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * In the example view, this drawable is drawn above the text.
//     */
//    var exampleDrawable: Drawable? = null
//
//    constructor(context: Context) : super(context) {
//        init(null, 0)
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        init(attrs, 0)
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
//        context,
//        attrs,
//        defStyle
//    ) {
//        init(attrs, defStyle)
//    }
//
//    private fun init(attrs: AttributeSet?, defStyle: Int) {
//        // Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.MapView, defStyle, 0
//        )
//
//        _exampleString = a.getString(
//            R.styleable.MapView_exampleString
//        )
//        _exampleColor = a.getColor(
//            R.styleable.MapView_exampleColor,
//            exampleColor
//        )
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        _exampleDimension = a.getDimension(
//            R.styleable.MapView_exampleDimension,
//            exampleDimension
//        )
//
//        if (a.hasValue(R.styleable.MapView_exampleDrawable)) {
//            exampleDrawable = a.getDrawable(
//                R.styleable.MapView_exampleDrawable
//            )
//            exampleDrawable?.callback = this
//        }
//
//        a.recycle()
//
//        // Set up a default TextPaint object
//        textPaint = TextPaint().apply {
//            flags = Paint.ANTI_ALIAS_FLAG
//            textAlign = Paint.Align.LEFT
//        }
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements()
//    }
//
//    private fun invalidateTextPaintAndMeasurements() {
//        textPaint.let {
//            it.textSize = exampleDimension
//            it.color = exampleColor
//            textWidth = it.measureText(exampleString)
//            textHeight = it.fontMetrics.bottom
//        }
//    }

//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//    }
//    init {
//        initialise()
//    }

}