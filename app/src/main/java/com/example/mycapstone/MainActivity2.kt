package com.example.mycapstone


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.davemorrissey.labs.subscaleview.ImageSource
import java.lang.Math.abs

//import kotlin.math.abs




class MainActivity2 : AppCompatActivity(), SensorEventListener {

    // DB
    private lateinit var db: InnerMapDB

    private var scaleFactor = 1.0f

    private lateinit var imageView: MapView

    private var fixedDotX = 400f
    private var fixedDotY = 500f
    private var dotX = fixedDotX
    private var dotY = fixedDotY

    //스텝수
    private var sensorManager: SensorManager? = null
    private var isRunning = false
    private var prevStep = 0
    private var stepCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //layout 연결
        setContentView(R.layout.activity_main2)

        imageView = findViewById(R.id.customImageView)
        imageView?.setImage(ImageSource.resource(R.drawable.lnner_83))
        imageView.setDotPosition(270f, 460f)


        //mapview를 270도로 돌림
        imageView.rotation = 270F


        //센서
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager


        // 센서 관련 초기화
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 1001)
            }
        }





        //화면 고정
        val scaleGestureListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor
                imageView.setScaleFactor(scaleFactor)
                imageView.invalidate()
                return true
            }
        }
        imageView.setOnTouchListener { _, event ->
            // 이벤트 처리 내용
            true
        }
        sensorManager = getSystemService(SensorManager::class.java)
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
        val sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        sensor?.let {
            sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null) {
            val isIncrementStep = abs(event.values[0].toInt() - prevStep)
            prevStep = event.values?.get(0)?.toInt()!!
            log("abs(event.values[0].toInt() - totalStep): $isIncrementStep :: ${isIncrementStep == 1}")

            if (isRunning && isIncrementStep == 1) {
                stepCount++
                val newX = 270f + (stepCount * 10)
                imageView.setDotPosition(newX, 460f)

                if (newX == 1035f ) {
                    runOnUiThread {

                        val builder = AlertDialog.Builder(this)
                        builder.setTitle("도착")
                        builder.setMessage("탈출구에 도착하셨습니다. 1층까지 계단으로 내려가서 탈출 해 주세요")

                        // 확인 버튼 설정
                        builder.setPositiveButton("확인") { dialog, which ->
                            // 확인 버튼을 눌렀을 때의 동작 처리
                            // 예를 들어, 특정 작업을 수행하거나 추가적인 처리를 할 수 있습니다.
                        }

                        // 팝업 창 생성 및 표시
                        val dialog = builder.create()
                        dialog.show()
                    }
                }

            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun log(msg: String) {
        Log.e(this.javaClass.simpleName, msg)
    }
}



