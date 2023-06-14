package com.example.mycapstone

import android.content.Intent
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.mycapstone.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var spinner: Spinner

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
        imageView.minScale = 1f
        imageView?.setImage(ImageSource.resource(R.drawable.map_8))
        ratio = imageView.getResources().getDisplayMetrics().density.toFloat()



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

        innerMapDB = InnerMapDB(this, "cap.db")

        val gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val clickedClassroom = getClickedClassroom(e.x, e.y)
                if (clickedClassroom != null) {
                    Toast.makeText(this@MainActivity, clickedClassroom, Toast.LENGTH_SHORT).show()
                }
                return true
            }
        })

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
                            "화장실" -> "화장실입니다."
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
                        builder.setTitle("강의실 정보")
                        builder.setMessage(message)

                        // 이미지 추가
                        val imageView = ImageView(this)
                        //imageView.setImageResource(R.drawable.toilet) // 이미지 리소스 설정
                        when (clickedClassroom) {
                            "스" -> imageView.setImageResource(R.drawable.toilet)
                            "801A" -> imageView.setImageResource(R.drawable.toilet)
                            "화장실" -> imageView.setImageResource(R.drawable.toilet)
                            "801B" -> imageView.setImageResource(R.drawable.toilet)
                            "802호" -> imageView.setImageResource(R.drawable.toilet)
                            "휴게실" -> imageView.setImageResource(R.drawable.pin)
                            "804호" -> imageView.setImageResource(R.drawable.toilet)
                            "805호" -> imageView.setImageResource(R.drawable.toilet)
                            "스" -> imageView.setImageResource(R.drawable.toilet)
                            "김" -> imageView.setImageResource(R.drawable.toilet)
                            "박" -> imageView.setImageResource(R.drawable.toilet)
                            "장" -> imageView.setImageResource(R.drawable.toilet)
                            "이" -> imageView.setImageResource(R.drawable.toilet)
                            "정" -> imageView.setImageResource(R.drawable.toilet)
                            "안" -> imageView.setImageResource(R.drawable.toilet)
                            "서" -> imageView.setImageResource(R.drawable.toilet)
                            "최" -> imageView.setImageResource(R.drawable.toilet)
                            "신" -> imageView.setImageResource(R.drawable.toilet)
                            "김" -> imageView.setImageResource(R.drawable.toilet)
                            "경" -> imageView.setImageResource(R.drawable.toilet)
                            "820호" -> imageView.setImageResource(R.drawable.toilet)
                            "821호" -> imageView.setImageResource(R.drawable.toilet)
                            "계단" -> imageView.setImageResource(R.drawable.toilet)

                            else -> imageView.setImageResource(R.drawable.pin)
                        }

                        builder.setView(imageView)

                        // 확인 버튼 설정
                        builder.setPositiveButton("확인") { dialog, which ->
                            // 확인 버튼을 눌렀을 때의 동작 처리
                            // 예를 들어, 특정 작업을 수행하거나 추가적인 처리를 할 수 있습니다.
                        }

                        // 팝업 창 생성 및 표시
                        val dialog = builder.create()
                        dialog.show()
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



