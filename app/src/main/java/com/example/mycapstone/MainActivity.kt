package com.example.mycapstone

import MainActivity2
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
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        val spinner = findViewById<Spinner>(R.id.spinner)

        val itemList = listOf("8층", "6층", "1층")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

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

        // 점찍기
        //imageView.setDotPosition(300f, 400f)

        innerMapDB = InnerMapDB(this, "cap.db")
        imageView.setInnerMapDB(innerMapDB)
        //imageView.addPinFromDB(1230f, 560f)

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
                        val message: String = when (clickedClassroom) {

                            "801A" -> "MIS,ERP 실습실"
                            "801B" -> "금융공학 실습실"
                            "802호" -> "CIM 실험실"
                            "휴게실" -> "휴게실입니다."
                            "804호" -> "스정통 1학년 설계실"
                            "805호" -> "스정통 2학년 설계실"
                            "스" -> "스마트정보통신공학과 사무실"
                            "김" -> "김기봉교수 연구실"
                            "박" -> "박현주교수 연구실"
                            "장" -> "장영범교수 연구실"
                            "이" -> "이시우교수 연구실"
                            "정" -> "정성균교수 연구실"
                            "안" -> "안범준교수 연구실"
                            "서" -> "서광규교수 연구실"
                            "최" -> "최성훈교수 연구실"
                            "신" -> "신현준교수 연구실"
                            "김" -> "김길환교수 연구실"
                            "경" -> "경영공학과 사무실"
                            "820호" -> "IT 실습실"
                            "821호" -> "경영공학과 주강의실"
                            "계단" -> "비상 계단입니다."

                            else -> "알 수 없는 강의실입니다."
                        }
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("도착")
                        builder.setMessage(message)

//                 이미지 추가
                        val imageView = ImageView(this)
                        imageView.setImageResource(R.drawable.pin) // 이미지 리소스 설정
                        builder.setView(imageView)

                        // 확인 버튼 설정
                        builder.setPositiveButton("확인") { dialog, which ->
                            // 확인 버튼을 눌렀을 때의 동작 처리
                            // 예를 들어, 특정 작업을 수행하거나 추가적인 처리를 할 수 있습니다.
                        }

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