package com.example.mycapstone

import android.app.Activity
import android.content.Intent
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.*
import com.davemorrissey.labs.subscaleview.ImageSource
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity2 : AppCompatActivity() {

    // DB
    private lateinit var db: InnerMapDB

    private var scaleFactor = 1.0f

    private lateinit var imageView : MapView

    private var fixedDotX = 300f
    private var fixedDotY = 400f
    private var dotX = fixedDotX
    private var dotY = fixedDotY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //DB
        db = InnerMapDB(this, "innermap.db")
        //layout 연결
        setContentView(R.layout.activity_main2)

        imageView = findViewById(R.id.customImageView)
        imageView?.setImage(ImageSource.resource(R.drawable.lnner_83))
        imageView.setDotPosition(300f, 400f)


        //mapview를 270도로 돌려줘
        imageView.rotation = 270F


        val scaleGestureListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                imageView.setScaleFactor(scaleFactor)
                return true
            }
        }
        imageView.setOnTouchListener { _, event ->
            // 이벤트 처리 내용
            true
        }


    }
}



