package com.example.mycapstone

import android.content.Intent
import android.graphics.Color
import android.graphics.PointF
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
import com.example.mycapstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var ratio = 0F

    private lateinit var imageView : MapView
    private lateinit var info: FrameLayout
    private lateinit var infoPic1: ImageView
    private lateinit var infoPic2: ImageView
    private lateinit var infoText1: TextView
    private lateinit var infoText2: TextView
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
        val mapbutton = findViewById<View>(R.id.qrButton)
        mapbutton.setOnClickListener {   //qrButton을 클릭했을 떄 MainActivity2로 이동
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.imageView)
        imageView.maxScale = 1f
        imageView?.setImage(ImageSource.resource(R.drawable.map_8))
        ratio = imageView.getResources().getDisplayMetrics().density.toFloat()
        imageView.classroom("801호",1230f, 595f,)
        imageView.setDotPosition(300f, 400f)

    }

    }


