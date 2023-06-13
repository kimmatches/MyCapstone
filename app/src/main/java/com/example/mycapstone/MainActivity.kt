package com.example.mycapstone

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.InputDevice
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.mycapstone.databinding.ActivityMainBinding
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // 정보창 및 표시될 사진, 지명, 접근성
    private lateinit var info: FrameLayout
    private lateinit var infoText1: TextView

    var ratio = 0F

    private lateinit var imageView : MapView
    private lateinit var innerMapDB: InnerMapDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var qrCodeScan = QRCodeScan(this)
//        var mainActivity2 = MainActivity2()
        /** Click */
        binding.tvQrScan.setOnClickListener {
            qrCodeScan.startQRScan()
        }

        //Button을 클릭했을 떄 MainActivity2로 이동
        val mapbutton = findViewById<View>(R.id.qrButton)
        mapbutton.setOnClickListener {   //qrButton을 클릭했을 떄 MainActivity2로 이동
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        // mapView 띄우기(지도)
        imageView = findViewById(R.id.imageView)
        imageView.maxScale = 1f
        imageView?.setImage(ImageSource.resource(R.drawable.map_8))
        ratio = imageView.getResources().getDisplayMetrics().density.toFloat()

        // 정보창
        info = findViewById(R.id.info)
        infoText1 = findViewById(R.id.text1)

        // 점찍기
        imageView.setDotPosition(300f, 400f)

        // 텍스트(강의실)
        imageView.classroom("801A", 1230f, 595f)
        imageView.classroom("801B", 1115f, 595f)
        imageView.classroom("802호", 1015f, 595f)
        imageView.classroom("휴게실", 910f, 595f)
        imageView.classroom("804호", 755f, 595f)
        imageView.classroom("805호", 550f, 595f)
        imageView.classroom("스", 430f, 595f)
        imageView.classroom("김", 375f, 595f)
        imageView.classroom("박", 320f, 595f)
        imageView.classroom("장", 270f, 595f)
        imageView.classroom("이", 220f, 595f)
        imageView.classroom("정", 165f, 595f)
        imageView.classroom("안", 165f, 330f)
        imageView.classroom("서", 220f, 330f)
        imageView.classroom("최", 275f, 330f)
        imageView.classroom("신", 325f, 330f)
        imageView.classroom("김", 375f, 330f)
        imageView.classroom("경", 430f, 330f)
        imageView.classroom("820호", 550f, 330f)
        imageView.classroom("821호", 1170f, 330f)
        imageView.classroom("화장실", 745f, 380f)
        imageView.classroom("홀", 985f, 415f)
        imageView.classroom("짝", 984f, 365f)
        imageView.classroom("전층", 855f, 400f)
        imageView.classroom("전층", 855f, 300f)
        imageView.classroom("계단", 1035f, 390f)
        imageView.classroom("계단", 1010f, 305f)

        imageView.addPin(PointF(1230f, 560f))

        innerMapDB = InnerMapDB(this, "cap.db")

        imageView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    val clickedClassroom = getClickedClassroom(event.x, event.y)
                    if (clickedClassroom != null) {
                        Toast.makeText(this, clickedClassroom, Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> false
            }
        }

    }

    private fun getClickedClassroom(x:Float, y: Float): String? {
        return imageView.getClickedClassroom(x, y)
    }

}



