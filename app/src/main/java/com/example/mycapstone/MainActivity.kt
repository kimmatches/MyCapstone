package com.example.mycapstone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val array = arrayOf(100, 100)
    var test_count = 0
    var ratio = 0F

    private lateinit var imageView : MapView
    var gestureDetector : GestureDetector? = null

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
        setContentView(R.layout.activity_main)
        val mapbutton = findViewById<View>(R.id.qrButton)
        mapbutton.setOnClickListener {   //qrButton을 클릭했을 떄 MainActivity2로 이동
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
            imageView = findViewById(R.id.imageView)
            imageView.maxScale = 1f
            imageView?.setImage(ImageSource.resource(R.drawable.map_8))
            ratio = imageView.getResources().getDisplayMetrics().density.toFloat()

            gestureDetector =
                GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                        var pointt = imageView?.viewToSourceCoord(e.x, e.y)

                        if (test_count == 0) {
                            test_count = 1
                            imageView?.setPin(pointt)

                            var s = (imageView?.minScale!! + imageView?.maxScale!!) / 3
                            imageView?.setScaleAndCenter(s!!, pointt)
                        } else {
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

