package com.example.mycapstone


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
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
    private val originalPinArray = ArrayList<PointF>()
    private var fixedArray = ArrayList<Int>()
    private var imageArray = ArrayList<Int>()
    private val classrooms: ArrayList<Classroom> = ArrayList()


    private var scaleFactor = 1.0f

    fun addPin(nPin: PointF?) {
        nPin?.let { pin ->
            val scaledPin = PointF(pin.x * scaleFactor, pin.y * scaleFactor)
            pinArray.add(scaledPin)
            originalPinArray.add(pin)
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

    fun getClickedClassroom(x: Float, y: Float): String? {
        for (classroom in classrooms) {
            val scaledX = classroom.x / scaleFactor
            val scaledY = classroom.y / scaleFactor

            val threshold = 50f
            if (x >= scaledX - threshold && x <= scaledX + threshold &&
                y >= scaledY - threshold && y <= scaledY + threshold
            ) {
                return classroom.text
            }
        }
        return null
    }
}

