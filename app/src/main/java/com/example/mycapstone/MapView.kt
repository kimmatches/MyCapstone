package com.example.mycapstone

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable //?
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
    private fun initialise() {invalidate()}

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        // TODO: consider storing these as member variables to reduce
//        // allocations per draw cycle.
//        val paddingLeft = paddingLeft
//        val paddingTop = paddingTop
//        val paddingRight = paddingRight
//        val paddingBottom = paddingBottom
//
//        val contentWidth = width - paddingLeft - paddingRight
//        val contentHeight = height - paddingTop - paddingBottom
//
//        exampleString?.let {
//            // Draw the text.
//            canvas.drawText(
//                it,
//                paddingLeft + (contentWidth - textWidth) / 2,
//                paddingTop + (contentHeight + textHeight) / 2,
//                textPaint
//            )
//        }
//
//        // Draw the example drawable on top of the text.
//        exampleDrawable?.let {
//            it.setBounds(
//                paddingLeft, paddingTop,
//                paddingLeft + contentWidth, paddingTop + contentHeight
//            )
//            it.draw(canvas)
//        }
//        for(i in pinArray)
//        {
//            var pin = i.point
//            var fix = i.fixed
//            var imageId = i.imageId
//
//            sourceToViewCoord(pin, vPin)
//            var image = BitmapFactory.decodeResource(this.resources, imageId, options)
//            w = density / 420f * image!!.getWidth()
//            h = density / 420f * image!!.getHeight()
//
//            if(fix == 1) // 확대 축소에 따라 크기가 변하지 않음
//            {
//                image = Bitmap.createScaledBitmap(image!!, (w!!).toInt(), (h!!).toInt(), true)
//                myPaint.textSize = 50.0f
//            }
//            else // 확대 축소에 따라 크기가 변함
//            {
//                image= Bitmap.createScaledBitmap(image!!, (w!!*s).toInt(), (h!!*s).toInt(), true)
//                myPaint.textSize = 50.0f * s
//            }
//            val vX = vPin.x - image!!.width / i.width //(/2가 없는 경우 해당 좌표기준 좌측 위로 이미지가 생성됨)
//            val vY = vPin.y - image!!.height / i.height
//            canvas.drawBitmap(image!!, vX, vY, paint)
//            myPaint.setColor(Color.BLUE)
//            canvas.drawText(i.text, vPin.x, vPin.y+myPaint.textSize+20, myPaint)
//        }
    }
    init {
        initialise()
    }
}