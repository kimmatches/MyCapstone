package com.example.mycapstone


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
//import androidx.compose.ui.res.painterResource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
/**
 * TODO: document your custom view class.
 */
class MapView @JvmOverloads constructor(context: Context?, attr: AttributeSet? = null) :
    SubsamplingScaleImageView(context, attr) {

    private val textPaint: Paint = Paint()

    //방금추가
    private var imageBitmap: Bitmap? = null
    private var dotX: Float = 0f
    private var dotY: Float = 0f
    private val paint = Paint()
    private var classX: Float = 0f
    private var classY: Float = 0f
    private var classS: String = String()

    private var pinArray = ArrayList<PointF>()
    private var fixedArray = ArrayList<Int>()
    private var imageArray = ArrayList<Int>()
    private val classrooms: ArrayList<Classroom> = ArrayList()

    val customMatrix = Matrix()

    private var scaleFactor = 1.0f

    fun addPin(nPin: PointF?, fix: Int?, imageID: Int?) {
        nPin?.let { pin ->
            val scaledPin = PointF(pin.x * scaleFactor, pin.y * scaleFactor)
            pinArray.add(scaledPin)
            fixedArray.add(fix!!)
            imageArray.add(imageID!!)
            invalidate()
        }
    }

    fun setDotPosition(x: Float, y: Float) {
        dotX = x
        dotY = y
        invalidate()
    }

    fun classroom(text: String, x: Float, y: Float){
        classrooms.add(Classroom(text, x, y))
        invalidate()
    }

    fun setScaleFactor(factor: Float) {
        scaleFactor = factor
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.BLUE
        paint.strokeWidth = 20f
        canvas.drawPoint(1230f, 560f, paint)
        canvas.drawPoint(375f, 350f, paint)
        canvas.drawPoint(270f, 350f, paint)
        canvas.drawPoint(780f, 350f, paint)


        // 이미지 그리기
        imageBitmap?.let {
            canvas?.drawBitmap(it, 0f, 0f, null)
        }

        // 점 그리기
        canvas?.drawCircle(dotX, dotY, 10f, Paint().apply {
            color = Color.BLACK
        })
        for (i in pinArray.indices) {
            val pin = pinArray[i]
            val scaledX = pin.x / scaleFactor
            val scaledY = pin.y / scaleFactor
            canvas.drawCircle(scaledX, scaledY, 10f, paint)
        }

        // 마커 텍스트
        canvas?.drawText(classS,classX,classY,Paint().apply{
            color = Color.BLACK
            textSize = 30f
            textAlign = Paint.Align.CENTER
        })
        for (classroom in classrooms) {
            canvas.drawText(classroom.text, classroom.x, classroom.y, Paint().apply {
                color = Color.BLACK
                textSize = 30f
                textAlign = Paint.Align.CENTER
            })
        }

    }
    private data class Classroom(val text: String, val x: Float, val y: Float)


}

