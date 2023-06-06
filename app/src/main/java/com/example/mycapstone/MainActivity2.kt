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

    // DB
    private lateinit var db: InnerMapDB

    var ratio = 0F

    private lateinit var imageView : MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //DB
        db = InnerMapDB(this, "innermap.db")
        //layout 연결
        setContentView(R.layout.activity_main2)

        imageView = findViewById(R.id.customImageView)
        imageView.maxScale = 1f
        imageView?.setImage(ImageSource.resource(R.drawable.lnner_8))
        ratio = imageView.getResources().getDisplayMetrics().density.toFloat()




    }
}


