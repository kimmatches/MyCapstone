package com.example.mycapstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView



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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //DB
        db = InnerMapDB(this, "innermap.db")

        floorsInner = db.getFloorsInner()
        nodesPlace = db.getNodesPlace()

        //layout 연결
        setContentView(R.layout.activity_main2)

        //지도 창
        map = findViewById(R.id.map)


    }

}