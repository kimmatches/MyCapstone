package com.example.mycapstone


import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable //?
import android.media.Image
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
//import androidx.compose.ui.res.painterResource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
/**
 * TODO: document your custom view class.
 */
class MapView @JvmOverloads constructor(context: Context?, attr: AttributeSet? = null) :
    SubsamplingScaleImageView(context, attr) {
    //방금추가
    private var imageBitmap: Bitmap? = null
    private var dotX: Float = 0f
    private var dotY: Float = 0f
    //
    private val paint = Paint()
    private val vPin = PointF()
    private var sPin: PointF? = null
    private var iPin: Bitmap? = null

    private var pinArray = ArrayList<PointF>()
    private var fixedArray = ArrayList<Int>()
    private var imageArray = ArrayList<Int>()
    private var lineArray = ArrayList<PointF>()
    private var lineColorArray = ArrayList<Int>()

    val customMatrix = Matrix()


    var w: Float? = null
    var h: Float? = null

    fun setPin(sPin: PointF?) {
        pinArray = arrayListOf()
        fixedArray = arrayListOf()
        imageArray = arrayListOf()
        pinArray.add(sPin!!)
        fixedArray.add(1)
        imageArray.add(R.drawable.pin)
        this.sPin = sPin

        invalidate()
    }

    /**
     * 지도(기본 이미지)위에 표시할 Pin을 추가합니다.
     * @param nPin Pin 이 표시될 좌표값입니다.
     * @param fix Pin 이미지가 지도와 함께 축소, 확대될지 결정합니다. 0:적용 1:적용안함
     * @param imageID Pin 이미지의 ID 값입니다. (ex: R.drawable.image_name)
     */

    // addPin()을 이용하여 핀을 추가하면서 핀의 좌표, 핀 이미지, 핀이 지도와 함께 크기 조정되는지 나타내는 플래그
    fun addPin(nPin: PointF?, fix: Int?, imageID: Int?) {
        pinArray.add(nPin!!)
        fixedArray.add(fix!!)
        imageArray.add(imageID!!)
        invalidate()
    }

    // 두 개의 좌표를 입력하면 두 좌표 사이에 선을 그림. 라인의 색상은 파라미터로 전달된 값에 따라 달라짐.
    fun addLine(point: PointF?, color: Int?) {
        lineArray.add(point!!)
        lineColorArray.add(color!!)
        invalidate()
    }

    //지도 위에 표시된 Pin을 모두 제거
    fun clearPin() {
        pinArray = arrayListOf()
        fixedArray = arrayListOf()
        imageArray = arrayListOf()
        lineArray = arrayListOf()
        lineColorArray = arrayListOf()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        val point = PointF(200.0f, 300.0f)

        val density = resources.displayMetrics.densityDpi.toFloat()
        iPin = BitmapFactory.decodeResource(this.resources, R.drawable.lnner_8)
        w = density / 420f * iPin!!.getWidth()
        h = density / 420f * iPin!!.getHeight()
        iPin = Bitmap.createScaledBitmap(iPin!!, w!!.toInt(), h!!.toInt(), true)

        paint.color= Color.BLUE
        paint.strokeWidth = 20f
        canvas.drawPoint(1230f, 560f, paint)
        canvas.drawPoint(375f, 350f, paint)
        canvas.drawPoint(270f, 350f, paint)
        canvas.drawPoint(780f, 350f, paint)
        canvas.drawPoint(855f, 400f, paint)


        // 이미지 그리기
        imageBitmap?.let {
            canvas?.drawBitmap(it, 0f, 0f, null)
        }

        // 점 그리기
        canvas?.drawCircle(dotX, dotY, 10f, Paint().apply {
            color = Color.BLACK
        })
    }

    fun setDotPosition(x: Float, y: Float) {
        dotX = x
        dotY = y
        invalidate()
    }


    }
//    init {initialize()}
//}