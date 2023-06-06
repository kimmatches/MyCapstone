package com.example.mycapstone

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.davemorrissey.labs.subscaleview.ImageSource
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity2 : AppCompatActivity() {

    //지도
    private lateinit var map: MapView
    // DB
    private lateinit var db: InnerMapDB

    private lateinit var floorsInner: List<InnerMapDB.InnerFloor>
    private lateinit var nodesPlace: List<InnerMapDB.PlaceNode>


    // 정보창 및 표시될 사진, 지명, 접근성
    private lateinit var info: FrameLayout
    private lateinit var infoPic1: ImageView
    private lateinit var infoPic2: ImageView
    private lateinit var infoText1: TextView
    private lateinit var infoText2: TextView

    // 건물 정보 기본 set
    private var placeid: Int = 1
    private var floorid: Int = 8

    // 지도 좌표 비율
    var ratio = 0F
//map
    val array = arrayOf(100, 100)
    var test_count = 0

    private lateinit var imageView : MapView
    var gestureDetector : GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DB
        db = InnerMapDB(this, "innermap.db")

        //layout 연결
        setContentView(R.layout.activity_main2)
        imageView = findViewById(R.id.imageView)
        imageView.maxScale = 1.5f
        imageView?.setImage(ImageSource.resource(R.drawable.map_8))

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                var pointt = imageView?.viewToSourceCoord(e.x, e.y)

                if(test_count == 0) {
                    test_count = 1
                    imageView?.setPin(pointt)

                    var s = (imageView?.minScale!! + imageView?.maxScale!!)/3
                    imageView?.setScaleAndCenter(s!!, pointt)
                }
                else {
                    imageView?.addLine(pointt, Color.BLUE)
                }
                check_area(pointt!!.x, pointt.y)
                return true
            }
        })
        imageView?.setOnTouchListener(View.OnTouchListener { view, motionEvent -> // OnTouchListner로 터치 이벤트 감지
            gestureDetector!!.onTouchEvent( // gestureDectector로 터치 이벤트 처리
                motionEvent
            )
        })

    }

    private fun check_area(x:Float, y: Float) {
        if (x <array[0] && y<array[1]) {
            imageView?.clearPin()
            val msg = "x: " + x + "y: " + y
            Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }
}


